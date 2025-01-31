package com.whydigit.efit.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.whydigit.efit.dto.ExcelUploadResultDTO;
import com.whydigit.efit.dto.MonthlyAttendanceDTO;
import com.whydigit.efit.entity.MonthlyAttendanceVO;
import com.whydigit.efit.entity.SalaryMasterVO;
import com.whydigit.efit.exception.ApplicationException;
import com.whydigit.efit.repo.MonthlyAttendanceRepo;
import com.whydigit.efit.repo.SalaryMasterRepo;

import io.jsonwebtoken.io.IOException;

@Service
public class SalaryMasterServiceImpl implements SalaryMasterService {

	@Autowired
	SalaryMasterRepo salaryMasterRepo;

	@Autowired
	MonthlyAttendanceRepo monthlyAttendanceRepo;

	@Override
	public List<SalaryMasterVO> getAllSalaryMaster() {
		return salaryMasterRepo.findAll();
	}

	@Override
	public SalaryMasterVO getAllSalaryMasterById(Long id) {
		return salaryMasterRepo.findById(id).orElse(null);
	}

	@Override
	public SalaryMasterVO createSalaryMaster(SalaryMasterVO salaryMasterVO) {
		return salaryMasterRepo.save(salaryMasterVO);
	}

	@Override
	public SalaryMasterVO updateSalaryMaster(SalaryMasterVO salaryMasterVO, Long id) {
		// Fetch the existing salaryMaster record from the database
		SalaryMasterVO existingSalaryMaster = salaryMasterRepo.findById(id).orElse(null);

		// Update fields in the existing record
		existingSalaryMaster.setHeadings(salaryMasterVO.getHeadings());
		existingSalaryMaster.setCode(salaryMasterVO.getCode());
		existingSalaryMaster.setCategory(salaryMasterVO.getCategory());
		existingSalaryMaster.setType(salaryMasterVO.getType());
		existingSalaryMaster.setAmount(salaryMasterVO.getAmount());
		existingSalaryMaster.setActive(salaryMasterVO.isActive());
		existingSalaryMaster.setOrgid(salaryMasterVO.getOrgid());

		return salaryMasterRepo.save(existingSalaryMaster);
	}

	@Override
	public Map<String, Object> createMonthlyAttendance(List<MonthlyAttendanceDTO> monthlyAttendanceDTOList) {
		Map<String, Object> response = new HashMap<>();

		// Convert DTO to VO
		List<MonthlyAttendanceVO> monthlyAttendanceVOList = monthlyAttendanceDTOList.stream().map(dto -> {
			MonthlyAttendanceVO vo = new MonthlyAttendanceVO();
			vo.setOrgId(dto.getOrgId());
			vo.setBranch(dto.getBranch());
			vo.setBranchCode(dto.getBranchCode());
			vo.setEmpCode(dto.getEmpCode());
			vo.setEmpName(dto.getEmpName());
			vo.setSalaryMonth(dto.getSalaryMonth());
			vo.setTotalDays(dto.getTotalDays());
			vo.setTotalLeaves(dto.getTotalLeaves());
			vo.setPresentDays(dto.getPresentDays()); // Ensure the DTO field is correct
			vo.setLop(dto.getLop());
			vo.setYear(dto.getYear());
			return vo;
		}).collect(Collectors.toList());
		monthlyAttendanceRepo.saveAll(monthlyAttendanceVOList);
		// Add to response
		response.put("message", "Monthly Attendance created successfully");
		response.put("monthlyAttendanceDetails", monthlyAttendanceVOList);

		return response;
	}

	@Override
	public ExcelUploadResultDTO excelUploadForMonthlyAttendance(MultipartFile[] files, Long orgId) throws ApplicationException, java.io.IOException {
			ExcelUploadResultDTO result = new ExcelUploadResultDTO(); // Result object
			List<MonthlyAttendanceVO> dataToSave = new ArrayList<>();
			result.setTotalExcelRows(0);
			result.setSuccessfulUploads(0);
			result.setUnsuccessfulUploads(0);

			for (MultipartFile file : files) {
				try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
					Sheet sheet = workbook.getSheetAt(0); // Assuming only one sheet
					Row headerRow = sheet.getRow(0);

					// Validate header
					for (Row row : sheet) {

						if (row.getRowNum() == 0) {
							continue; // Skip this iteration
						}

						result.setTotalExcelRows(result.getTotalExcelRows() + 1); // Increment total rows

						try {
							// Parse cell values
							String year = getStringCellValue(row.getCell(0)); // Account Code is in column 0
							String empcode = getStringCellValue(row.getCell(1)); // Account Name is in column 1
							String empname = getStringCellValue(row.getCell(2));
							String branch = getStringCellValue(row.getCell(3));
							String branchCode = getStringCellValue(row.getCell(4));
							String month = getStringCellValue(row.getCell(5));
							float totalDays = Float.parseFloat(getStringCellValue(row.getCell(6)));
							float totalLeave = Float.parseFloat(getStringCellValue(row.getCell(7)));
							float presentDays = Float.parseFloat(getStringCellValue(row.getCell(8)));
							float lop = Float.parseFloat(getStringCellValue(row.getCell(9)));

							// Create and populate TrailBalanceVO object
							MonthlyAttendanceVO vo = new MonthlyAttendanceVO();
							vo.setOrgId(orgId);
							vo.setBranch(branch);
							vo.setBranchCode(branchCode);
							vo.setEmpCode(empcode);
							vo.setEmpName(empname);
							vo.setSalaryMonth(month);
							vo.setTotalDays(totalDays);
							vo.setTotalLeaves(totalLeave);
							vo.setPresentDays(presentDays); // Ensure the DTO field is correct
							vo.setLop(lop);
							vo.setYear(year);
							result.setSuccessfulUploads(result.getSuccessfulUploads() + 1); // Increment successful uploads
						} catch (Exception e) {
							result.setUnsuccessfulUploads(result.getUnsuccessfulUploads() + 1);
							String error = String.format("Row %d: %s", row.getRowNum() + 1, e.getMessage());

							result.addFailureReason(error); // Capture failure reason
						}
					}
				} catch (IOException | EncryptedDocumentException e) {
					throw new ApplicationException(
							"Failed to process file: " + file.getOriginalFilename() + " - " + e.getMessage());
				}
			}

			// Save all valid rows
			if (!dataToSave.isEmpty()) {
				monthlyAttendanceRepo.saveAll(dataToSave);
			}

			return result; // Return the result summary
		}

		private BigDecimal parseBigDecimal(String value) throws ApplicationException {
			if (value == null || value.trim().isEmpty()) {
				return BigDecimal.ZERO; // Return 0.00 for empty or null cells
			}
			try {
				BigDecimal parsedValue = new BigDecimal(value.trim());
				return parsedValue.compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ZERO : parsedValue; // Ensure zero is set as
																									// 0.00
			} catch (NumberFormatException e) {
				throw new ApplicationException("Invalid number format: " + value, e);
			}
		}

		private String getStringCellValue(Cell cell) {
			if (cell == null) {
				return "";
			}

			switch (cell.getCellType()) {
			case STRING:
				return cell.getStringCellValue().trim();
			case NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					return new SimpleDateFormat("dd-MM-yyyy").format(cell.getDateCellValue());
				} else {
					double numericValue = cell.getNumericCellValue();
					if (numericValue == (int) numericValue) {
						return String.valueOf((int) numericValue);
					} else {
						return BigDecimal.valueOf(numericValue).toPlainString();
					}
				}
			case BOOLEAN:
				return String.valueOf(cell.getBooleanCellValue());
			case FORMULA:
				return cell.getCellFormula();
			default:
				return "";
			}
		}

		private boolean isRowEmpty(Row row) {
			for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
				Cell cell = row.getCell(cellNum);
				if (cell != null && cell.getCellType() != CellType.BLANK) {
					return false;
				}
			}
			return true;
		}
}
