package com.whydigit.efit.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.whydigit.efit.dto.EmployeeAttendanceActivityDTO;
import com.whydigit.efit.dto.EmployeeDailyStatusDTO;
import com.whydigit.efit.dto.EmployeeInOutActionDTO;
import com.whydigit.efit.entity.EmployeeCheckInTimeVO;
import com.whydigit.efit.entity.EmployeeDailyStatusVO;
import com.whydigit.efit.exception.ApplicationException;

@Service
public interface EmployeeService {

	List<EmployeeAttendanceActivityDTO> getEmployeeAttendanceActivity(LocalDate startDate, LocalDate endDate,
			long orgId, String empId) throws ApplicationException;

	EmployeeDailyStatusVO employeeInOutAction(EmployeeInOutActionDTO employeeInOutActionDTO);

	List<EmployeeDailyStatusDTO> getEmployeeStatus(Long orgId, Long empId, LocalDate date) throws ApplicationException;
	
	Set<Object[]>getAllEmployeeMonthlyAttendanceDetails(String fromdt,String todt,String branch);
	
}
