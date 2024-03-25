package com.whydigit.efit.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whydigit.efit.dto.EmployeeAttendanceActivityDTO;
import com.whydigit.efit.dto.EmployeeDailyStatusDTO;
import com.whydigit.efit.dto.EmployeeInOutActionDTO;
import com.whydigit.efit.entity.EmployeeDailyStatusVO;
import com.whydigit.efit.entity.EmployeeDetailsVO;
import com.whydigit.efit.exception.ApplicationException;
import com.whydigit.efit.repo.EmployeeCheckinTimeRepo;
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
	
	@Autowired
	EmployeeCheckinTimeRepo employeeCheckinTimeRepo;

	@Override
	public List<EmployeeAttendanceActivityDTO> getEmployeeAttendanceActivity(LocalDate startDate, LocalDate endDate,
			long orgId, String empId) throws ApplicationException {
		List<EmployeeDetailsVO> employeeDetailsVO = new ArrayList<>();
		long sunDay = getNoOfSunday(startDate, endDate);
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
				empAct.setPresentDays(employeeDailyStatusRepo.getPresentDays(startDate, endDate, empd.getId()));
				empAct.setWorkingDays(totalDays - totalHolidays - sunDay);
				return empAct;
			} catch (Exception e) {
				LOGGER.error("Failed to get EmployeeAttendanceActivity empId : {}", empd.getId());
			}
			return null;
		}).collect(Collectors.toList());
		return employeeAttendanceActivityDTO;
	}

	public static long getNoOfSunday(LocalDate startDate, LocalDate endDate) {
		long sundayCount = 0;

		while (!startDate.isAfter(endDate)) {
			if (startDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
				sundayCount++;
			}
			startDate = startDate.plusDays(1);
		}
		return sundayCount;
	}

	@Override
	public EmployeeDailyStatusVO employeeInOutAction(EmployeeInOutActionDTO employeeInOutActionDTO) {
		EmployeeDailyStatusVO employeeDailyStatusVO = employeeDailyStatusRepo
				.getLatestSataus(employeeInOutActionDTO.getEmpId());
		if (ObjectUtils.isNotEmpty(employeeDailyStatusVO)
				&& employeeDailyStatusVO.getLoginDate().toLocalDate().isEqual(LocalDate.now())) {
			employeeDailyStatusVO.setActionAt(LocalDateTime.now());
			employeeDailyStatusVO.setCheckIn(employeeInOutActionDTO.isCheckIn());
			if (!employeeInOutActionDTO.isCheckIn()) {
				employeeDailyStatusVO.setLogoutDate(LocalDateTime.now());
			} else {
				employeeDailyStatusVO.setLogoutDate(null);
			}
			return employeeDailyStatusRepo.save(employeeDailyStatusVO);
		} else if (ObjectUtils.isNotEmpty(employeeDailyStatusVO)) {
			employeeDailyStatusVO.setActionAt(LocalDateTime.now());
			employeeDailyStatusVO.setCheckIn(false);
			employeeDailyStatusVO.setLogoutDate(LocalDateTime.now());
			employeeDailyStatusVO = employeeDailyStatusRepo.save(employeeDailyStatusVO);
			if (employeeInOutActionDTO.isCheckIn()) {
				employeeDailyStatusVO = setDailyStatusWithCheckIn(employeeInOutActionDTO);
			}
			return employeeDailyStatusVO;
		} else {
			return setDailyStatusWithCheckIn(employeeInOutActionDTO);
		}
	}

	private EmployeeDailyStatusVO setDailyStatusWithCheckIn(EmployeeInOutActionDTO employeeInOutActionDTO) {
		EmployeeDailyStatusVO employeeDailyStatusVO = new EmployeeDailyStatusVO();
		employeeDailyStatusVO.setActionAt(LocalDateTime.now());
		employeeDailyStatusVO.setCheckIn(true);
		employeeDailyStatusVO.setEmpId(employeeInOutActionDTO.getEmpId());
		employeeDailyStatusVO.setLoginDate(LocalDateTime.now());
		employeeDailyStatusVO.setOrgId(employeeInOutActionDTO.getOrgId());
		return employeeDailyStatusRepo.save(employeeDailyStatusVO);
	}

	@Override
	public List<EmployeeDailyStatusDTO> getEmployeeStatus(Long orgId, Long empId, LocalDate date)
			throws ApplicationException {
		List<EmployeeDailyStatusDTO> employeeDailyStatusDTO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			employeeDailyStatusDTO = employeeDailyStatusRepo.getStatusByOrgIdAndDate(orgId, date);
		} else if (ObjectUtils.isNotEmpty(empId)) {
			EmployeeDetailsVO employeeDetailsVO = employeeDetailsRepo.findById(empId)
					.orElseThrow(() -> new ApplicationException("Invalid Employee."));
			EmployeeDailyStatusVO employeeDailyStatusVO = employeeDailyStatusRepo.getLatestSataus(empId);
			EmployeeDailyStatusDTO employeeDailyStatus = new EmployeeDailyStatusDTO();
			employeeDailyStatus.setEmpId(employeeDetailsVO.getId());
			employeeDailyStatus.setEmpName(employeeDetailsVO.getEmpname());
			employeeDailyStatus.setOrgId(employeeDetailsVO.getOrgId());
			if ((ObjectUtils.isNotEmpty(employeeDailyStatusVO)
					&& employeeDailyStatusVO.getLoginDate().toLocalDate().isEqual(LocalDate.now()))
					|| (ObjectUtils.isNotEmpty(employeeDailyStatusVO) && employeeDailyStatusVO.isCheckIn())) {
				employeeDailyStatus.setCheckIn(employeeDailyStatusVO.isCheckIn());
				employeeDailyStatus.setLoginDate(employeeDailyStatusVO.getLoginDate());
				employeeDailyStatus.setLogoutDate(employeeDailyStatusVO.getLogoutDate());
			} else {
				employeeDailyStatus.setCheckIn(false);
			}
			employeeDailyStatusDTO = Collections.singletonList(employeeDailyStatus);
		} else {
			throw new ApplicationException("Invalid input. Please try again");
		}
		return employeeDailyStatusDTO;
	}

	@Override
	public Set<Object[]> getAllEmployeeMonthlyAttendanceDetails(String fromdt, String todt,String branch) {
		
		return employeeDetailsRepo.findAllAttendanceByFromAndToDate(fromdt,todt,branch);
	}

	
}
