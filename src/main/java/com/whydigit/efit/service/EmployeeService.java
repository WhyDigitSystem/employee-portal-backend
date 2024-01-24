package com.whydigit.efit.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.whydigit.efit.dto.EmployeeAttendanceActivityDTO;
import com.whydigit.efit.exception.ApplicationException;

@Service
public interface EmployeeService {

	List<EmployeeAttendanceActivityDTO> getEmployeeAttendanceActivity(LocalDate startDate, LocalDate endDate,
			long orgId, String empId) throws ApplicationException;

}
