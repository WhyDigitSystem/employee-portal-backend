package com.whydigit.efit.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whydigit.efit.dto.EmployeeAttendanceActivityDTO;
import com.whydigit.efit.entity.EmployeeDetailsVO;
import com.whydigit.efit.entity.HolidayVO;
import com.whydigit.efit.exception.ApplicationException;
import com.whydigit.efit.repo.EmployeeDailyStatusRepo;
import com.whydigit.efit.repo.EmployeeDetailsRepo;
import com.whydigit.efit.repo.HolidayRepo;
import com.whydigit.efit.repo.LeaveRequestRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	public static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	HolidayRepo holidayRepo;

	@Autowired
	LeaveRequestRepo leaveRequestRepo;

	@Autowired
	EmployeeDetailsRepo employeeDetailsRepo;

	@Autowired
	EmployeeDailyStatusRepo employeeDailyStatusRepo;

	@Override
	public List<EmployeeAttendanceActivityDTO> getEmployeeAttendanceActivity(LocalDate startDate, LocalDate endDate,
			long orgId, String empId) throws ApplicationException {
		List<EmployeeDetailsVO> employeeDetailsVO = new ArrayList<>();
		long sunDay = startDate.datesUntil(endDate.plusDays(1)).filter(date -> date.getDayOfWeek() == DayOfWeek.SUNDAY)
				.count();
		long totalDays = ChronoUnit.DAYS.between(startDate, endDate.plusDays(1));
		if (orgId == 0) {
			throw new ApplicationException("Invalid request. Please try again.");
		}
		long commonHolidays = holidayRepo.getNoOfHolidaysByDepAndCompanyCode("ALL", orgId, startDate, endDate);
		if (StringUtils.isNotBlank(empId)) {
			List<Long> empIds = Arrays.stream(empId.split(",")).map(Long::valueOf).collect(Collectors.toList());
			employeeDetailsVO = employeeDetailsRepo.findAllById(empIds);
		} else {
			employeeDetailsVO = employeeDetailsRepo.findAllByOrgId(orgId);
		}
		List<EmployeeAttendanceActivityDTO> employeeAttendanceActivityDTO = employeeDetailsVO.stream().map(empd -> {
			try {
				long depHolidays = holidayRepo.getNoOfHolidaysByDepAndCompanyCode(empd.getDepartment(), orgId,
						startDate, endDate);
				EmployeeAttendanceActivityDTO empAct = new EmployeeAttendanceActivityDTO();
				long totalHolidays = commonHolidays + depHolidays;
				empAct.setEmpCode(empd.getEmpcode());
				empAct.setEmpName(empd.getEmpname());
				empAct.setNoOfCommonHolidays(commonHolidays);
				empAct.setNoOfDepHolidays(depHolidays);
				empAct.setTotalHolidays(totalHolidays);
				empAct.setNoOfWeekendDays(sunDay);
				empAct.setTotalDays(totalDays);
				empAct.setNumberOfLeaveDays(leaveRequestRepo.findNoOfLeave(startDate, empd.getEmpcode()));
				empAct.setPresentDays(employeeDailyStatusRepo.getPresentDays(startDate, endDate, empd.getEmpcode()));
				empAct.setWorkingDays(totalDays - totalHolidays - sunDay);
				return empAct;
			} catch (Exception e) {
				LOGGER.error("Failed to get EmployeeAttendanceActivity empId : {}", empd.getId());
			}
			return null;
		}).collect(Collectors.toList());
		return employeeAttendanceActivityDTO;
	}

}
