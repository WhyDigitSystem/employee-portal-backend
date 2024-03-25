package com.whydigit.efit.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.whydigit.efit.dto.AdminAccessRole;
import com.whydigit.efit.dto.CreateUserFormDTO;
import com.whydigit.efit.dto.LeaveApprovalDTO;
import com.whydigit.efit.dto.UserNameDTO;
import com.whydigit.efit.entity.CheckinStatusVO;
import com.whydigit.efit.entity.CheckinVO;
import com.whydigit.efit.entity.EmployeeCheckInTimeVO;
import com.whydigit.efit.entity.EmployeeCheckinDailyStatusVO;
import com.whydigit.efit.entity.EmployeeDetailsVO;
import com.whydigit.efit.entity.HolidayVO;
import com.whydigit.efit.entity.LeaveBalanceVO;
import com.whydigit.efit.entity.LeaveCreditVO;
import com.whydigit.efit.entity.LeaveEligibleVO;
import com.whydigit.efit.entity.LeaveRequestVO;
import com.whydigit.efit.entity.LeaveTypeVO;
import com.whydigit.efit.entity.PermissionRequestVO;
import com.whydigit.efit.exception.ApplicationException;
import com.whydigit.efit.repo.CheckinRepo;
import com.whydigit.efit.repo.CheckinStatusRepo;
import com.whydigit.efit.repo.EmployeeCheckinDailyStatusRepo;
import com.whydigit.efit.repo.EmployeeCheckinTimeRepo;
import com.whydigit.efit.repo.EmployeeDetailsRepo;
import com.whydigit.efit.repo.HolidayRepo;
import com.whydigit.efit.repo.LeaveBalanceRepository;
import com.whydigit.efit.repo.LeaveCreditRepository;
import com.whydigit.efit.repo.LeaveEligibleRepo;
import com.whydigit.efit.repo.LeaveRequestRepo;
import com.whydigit.efit.repo.LeaveTypeRepo;
import com.whydigit.efit.repo.PermissionRequestRepo;

@Service
public class BasicMasterServiceImpl implements BasicMasterService {

	@Autowired
	private EmployeeDetailsRepo employeeRepo;

	@Autowired
	private LeaveTypeRepo leaveTypeRepo;

	@Autowired
	private HolidayRepo holidayRepo;

	@Autowired
	private LeaveRequestRepo newLeaveRequestRepo;

	@Autowired
	private PermissionRequestRepo newPermissionRequestRepo;

	@Autowired
	CheckinRepo checkinRepo;

	@Autowired
	CheckinStatusRepo chkstatusrepo;

	@Autowired
	EmployeeCheckinTimeRepo checkinTimeRepo;

	@Autowired
	EmployeeCheckinDailyStatusRepo dailyStatus;

	@Autowired
	LeaveCreditRepository leaveCreditRepo;

	@Autowired
	LeaveBalanceRepository leaveBalanceRepo;

	@Autowired
	AdminService adminService;

	@Autowired
	LeaveEligibleRepo leaveEligibleRepo;

//	employee

	@Override
	public List<EmployeeDetailsVO> getAllgetAllEmployees() {
		return employeeRepo.findAll();
	}

	@Override
	public Optional<EmployeeDetailsVO> getEmployeeById(long id) {
		return employeeRepo.findById(id);
	}

	// Get Employee by Role
	@Override
	public Set<Object[]> getEmployeeByRole(long orgid, String role) {
		return employeeRepo.findAllByRole(orgid, role);
	}

	@Override
	@Transactional
	public EmployeeDetailsVO createEmployee(EmployeeDetailsVO employeeVO) throws ApplicationException {
		employeeVO.setCancel(false);
		employeeVO = employeeRepo.save(employeeVO);
		adminService.createUser(setCreateUserFormDTO(employeeVO));
		return employeeVO;
	}

	private CreateUserFormDTO setCreateUserFormDTO(EmployeeDetailsVO employeeVO) throws ApplicationException {
		CreateUserFormDTO createUserFormDTO = new CreateUserFormDTO();
		createUserFormDTO.setEmail(employeeVO.getEmail());
		createUserFormDTO.setEmpCode(employeeVO.getEmpcode());
		createUserFormDTO.setEmpId(employeeVO.getId());
		createUserFormDTO.setEmpName(employeeVO.getEmpname());
		createUserFormDTO.setOrgId(employeeVO.getOrgId());
		createUserFormDTO.setPassword("3PNaIlEuc7yvkNG9ueZUlhZZBSZZalEYZV9neHql9lA=");
		createUserFormDTO.setRole(AdminAccessRole.USER);
		return createUserFormDTO;
	}

	@Override
	public Optional<EmployeeDetailsVO> updateEmployee(EmployeeDetailsVO employeeVO) {
		if (employeeRepo.existsById(employeeVO.getId())) {
			return Optional.of(employeeRepo.save(employeeVO));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public void deleteEmployee(long id) {
		employeeRepo.deleteById(id);
	}

	// Leave Type

	@Override
	public List<LeaveTypeVO> getAllgetAllLeaveType() {

		return leaveTypeRepo.findAll();
	}

	@Override
	public Set<Object[]> getLeaveTypeNameByOrgId(long orgId) {

		return leaveTypeRepo.findLeaveTypeNameByOrgId(orgId);
	}

	@Override
	public Optional<LeaveTypeVO> getLeavetypeById(int id) {
		// TODO Auto-generated method stub
		return leaveTypeRepo.findById(id);
	}

	@Override
	public LeaveTypeVO createLeaveType(LeaveTypeVO leaveTypeVO) {
		leaveTypeVO.setCancel(false);
		return leaveTypeRepo.save(leaveTypeVO);
	}

	@Override
	public Optional<LeaveTypeVO> updateLeaveType(LeaveTypeVO leaveTypeVO) {
		if (leaveTypeRepo.existsById(leaveTypeVO.getId())) {
			return Optional.of(leaveTypeRepo.save(leaveTypeVO));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public void deleteLeaveType(int id) {
		leaveTypeRepo.deleteById(id);

	}

	// HOLIDAY Type

	@Override
	public List<HolidayVO> getAllHolidayType() {

		return holidayRepo.findAll();
	}

	@Override
	public Optional<HolidayVO> getholidayById(int id) {
		// TODO Auto-generated method stub
		return holidayRepo.findById(id);
	}

	@Override
	public HolidayVO createholidayType(HolidayVO holidayVO) {
		holidayVO.setCancel(false);
		return holidayRepo.save(holidayVO);
	}

	@Override
	public Optional<HolidayVO> updateHolidayType(HolidayVO holidayVO) {
		if (holidayRepo.existsById(holidayVO.getId())) {
			return Optional.of(holidayRepo.save(holidayVO));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public void deleteHolidayType(int id) {
		holidayRepo.deleteById(id);

	}

	// NewLeave

	@Override
	public List<LeaveRequestVO> getAllLeaveRequest() {
		// TODO Auto-generated method stub
		return newLeaveRequestRepo.findAll();
	}

	@Override
	public List<LeaveRequestVO> getAllLeaveRequestBasedOnApproval(Long orgId, String empcode) {
		// TODO Auto-generated method stub
		return newLeaveRequestRepo.findAllRequestBasedOnApproval(orgId, empcode);
	}

	@Override
	public Optional<LeaveRequestVO> getLeaveRequestById(int id) {
		// TODO Auto-generated method stub
		return newLeaveRequestRepo.findById(id);
	}

	@Override
	public List<LeaveRequestVO> getLeaveRequestByEmpcode(@PathVariable String empcode) {
		return newLeaveRequestRepo.findByEmpcode(empcode);
	}

	@Override
	public LeaveRequestVO createLeaveRequest(LeaveRequestVO newLeaveRequestVO) {
		newLeaveRequestVO.setCancel(false);
		// TODO Auto-generated method stub
		return newLeaveRequestRepo.save(newLeaveRequestVO);
	}

	@Override
	public Optional<LeaveRequestVO> updateLeaveRequest(LeaveRequestVO newLeaveRequestVO) {
		if (newLeaveRequestRepo.existsById(newLeaveRequestVO.getId())) {
			return Optional.of(newLeaveRequestRepo.save(newLeaveRequestVO));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<LeaveRequestVO> updateLeaveRequestApproval(LeaveApprovalDTO leaveApprovalDTO, int id) {
		Date currentdate = new Date();
		LeaveRequestVO leaveRequestvo = newLeaveRequestRepo.findById(id).get();
		leaveRequestvo.setStatus(leaveApprovalDTO.getStatus());
		leaveRequestvo.setApprovedby(leaveApprovalDTO.getApprovedby());
		leaveRequestvo.setRemarks(leaveApprovalDTO.getRemarks());
		leaveRequestvo.setApprovedat(currentdate);
		return Optional.of(newLeaveRequestRepo.save(leaveRequestvo));
	}

//	@Override
//	public Optional<LeaveRequestVO> updateLeaveRequestApproval(LeaveRequestVO leaveRequestVO) {
//		Date currentdate=new Date();
//		if (newLeaveRequestRepo.existsById(leaveRequestVO.getId())) {	
//			leaveRequestVO.setApprovedat(currentdate);
//			return Optional.of(newLeaveRequestRepo.save(leaveRequestVO));
//		} else {
//			return Optional.empty();
//		}
//	}

	@Override
	public void deleteLeaveRequest(int id) {
		// TODO Auto-generated method stub
		newLeaveRequestRepo.deleteById(id);
	}

	// permission Request

	@Override
	public List<PermissionRequestVO> getAllPermissionRequest() {
		// TODO Auto-generated method stub
		return newPermissionRequestRepo.findAll();
	}

	@Override
	public Optional<PermissionRequestVO> getPermissionRequestById(int id) {
		// TODO Auto-generated method stub
		return newPermissionRequestRepo.findById(id);
	}

	@Override
	public List<PermissionRequestVO> getPermissionRequestByEmpcode(@PathVariable String empcode) {
		return newPermissionRequestRepo.findByEmpcode(empcode);
	}

	@Override
	public List<PermissionRequestVO> getAllPermissionRequestBasedonApproval(Long orgId, String Empcode) {

		return newPermissionRequestRepo.finAllByapproval(orgId, Empcode);
	}

	@Override
	public PermissionRequestVO createPermissionRequest(PermissionRequestVO newPermissionRequestVO) {
		// TODO Auto-generated method stub
		newPermissionRequestVO.setCancel(false);
		return newPermissionRequestRepo.save(newPermissionRequestVO);
	}

	@Override
	public Optional<PermissionRequestVO> updatePermissionRequest(PermissionRequestVO newPermissionRequestVO) {
		if (newPermissionRequestRepo.existsById(newPermissionRequestVO.getId())) {
			return Optional.of(newPermissionRequestRepo.save(newPermissionRequestVO));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<PermissionRequestVO> updatePermissionRequestApproval(LeaveApprovalDTO leaveApprovalDTO, int id) {

		Date currentdate = new Date();
		PermissionRequestVO permission = newPermissionRequestRepo.findById(id).get();
		permission.setStatus(leaveApprovalDTO.getStatus());
		permission.setApprovedby(leaveApprovalDTO.getApprovedby());
		permission.setRemarks(leaveApprovalDTO.getRemarks());
		permission.setApprovedat(currentdate);
		return Optional.of(newPermissionRequestRepo.save(permission));
	}

	@Override
	public void deletePermissionRequest(int id) {

		newPermissionRequestRepo.deleteById(id);
	}

	public CheckinVO checkIn(UserNameDTO user1) {
		Date current = new Date();
		CheckinVO checkinVO = new CheckinVO();
		checkinVO.setCompanycode("WDS");
		checkinVO.setBranch("BLR");
		checkinVO.setEmpcode(user1.getEmpcode());
		checkinVO.setCheckin_date(current);
		checkinVO.setStatus("In");
		checkinVO.setEntry_time(current);
		checkinRepo.save(checkinVO);

		CheckinStatusVO chk = new CheckinStatusVO();
		chk.setEmpcode(user1.getEmpcode());
		chk.setBranchId(user1.getBranchId());
		chk.setOrgId(user1.getOrgId());
		chk.setStatus(checkinVO.getStatus());
		chkstatusrepo.save(chk);

		return checkinRepo.save(checkinVO);
	}

	public CheckinVO checkOut(UserNameDTO user1) {
		Date current = new Date();
		CheckinVO checkinVO = new CheckinVO();
		checkinVO.setCompanycode("WDS");
		checkinVO.setBranch("BLR");
		checkinVO.setEmpcode(user1.getEmpcode());
		checkinVO.setCheckin_date(current);
		checkinVO.setEntry_time(current);
		checkinVO.setStatus("Out");

		CheckinStatusVO chk = new CheckinStatusVO();
		chk.setEmpcode(user1.getEmpcode());
		chk.setBranchId(user1.getBranchId());
		chk.setOrgId(user1.getOrgId());
		chk.setStatus(checkinVO.getStatus());
		chkstatusrepo.save(chk);

		return checkinRepo.save(checkinVO);
	}

	@Override
	public Optional<CheckinStatusVO> getStatusByEmpcode(String empcode) {

		return chkstatusrepo.findById(empcode);
	}

	@Override
	public List<EmployeeCheckInTimeVO> getAttendanceByEmpcode(String empcode) {
		return checkinTimeRepo.findByEmpcode(empcode);
	}

	@Override
	public List<EmployeeCheckinDailyStatusVO> getAllEmployeesCheckinStatusDaily() {

		return dailyStatus.findAll();
	}

	// Create Leave credit to employees
	@Override
	public LeaveCreditVO createLeaveCredit(LeaveCreditVO leaveCreditVO) {
		// TODO Auto-generated method stub
		leaveCreditVO.setBranch("BLR");
		leaveCreditVO.setCompanycode("WDS");
		leaveCreditVO.setCancel(false);
		return leaveCreditRepo.save(leaveCreditVO);
	}

	@Override
	public List<LeaveBalanceVO> getAllLeaveBalance() {

		return leaveBalanceRepo.findAll();
	}

	@Override
	public List<LeaveBalanceVO> getLeaveBalanceByEmpcode(String empcode) {
		return leaveBalanceRepo.findByEmpcode(empcode);
	}

	@Override
	public Set<Object[]> getAllLeaveTypeForLeaveRequest(Long orgId, Long id) {
		return leaveTypeRepo.findAllType(orgId, id);
	}

	// Leave Eligible

	@Override
	public LeaveEligibleVO createLeaveEligible(LeaveEligibleVO leaveEligibleVO) {
		return leaveEligibleRepo.save(leaveEligibleVO);
	}

}
