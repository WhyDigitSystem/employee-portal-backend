package com.whydigit.efit.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.whydigit.efit.dto.ExcelUploadResultDTO;
import com.whydigit.efit.dto.MonthlyAttendanceDTO;
import com.whydigit.efit.entity.SalaryMasterVO;
import com.whydigit.efit.exception.ApplicationException;

@Service
public interface SalaryMasterService {

	public List<SalaryMasterVO> getAllSalaryMaster();

	public SalaryMasterVO getAllSalaryMasterById(Long id);

	public SalaryMasterVO createSalaryMaster(SalaryMasterVO earningsVO);

	public SalaryMasterVO updateSalaryMaster(SalaryMasterVO earningsVO, Long id);

	public Map<String, Object> createMonthlyAttendance(List<MonthlyAttendanceDTO> monthlyAttendanceDTO);

	public ExcelUploadResultDTO excelUploadForMonthlyAttendance(MultipartFile[] files, Long orgId) throws ApplicationException, IOException;

}
