package com.whydigit.efit.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.whydigit.efit.common.CommonConstant;
import com.whydigit.efit.common.EmployeePortalConstants;
import com.whydigit.efit.controller.BranchDTO;
import com.whydigit.efit.dto.CreateOrganizationFormDTO;
import com.whydigit.efit.dto.CreateUserFormDTO;
import com.whydigit.efit.dto.OrganizationDTO;
import com.whydigit.efit.dto.Role;
import com.whydigit.efit.entity.BranchVO;
import com.whydigit.efit.entity.OrganizationVO;
import com.whydigit.efit.entity.UserVO;
import com.whydigit.efit.exception.ApplicationException;
import com.whydigit.efit.repo.BranchRepo;
import com.whydigit.efit.repo.OrganizationRepo;
import com.whydigit.efit.repo.UserRepo;
import com.whydigit.efit.util.CryptoUtils;

@Service
public class AdminServiceImpl implements AdminService {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Autowired
	UserRepo userRepo;

	@Autowired
	UserService userService;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	OrganizationRepo organizationRepo;
	
	@Autowired
	BranchRepo branchRepo;
	

	@Override
	public void createUser(CreateUserFormDTO createUserFormDTO) throws ApplicationException {
		String methodName = "createUser()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		if (ObjectUtils.isEmpty(createUserFormDTO) || StringUtils.isBlank(createUserFormDTO.getEmail())) {
			throw new ApplicationContextException(EmployeePortalConstants.ERRROR_MSG_INVALID_USER_REGISTER_INFORMATION);
		} else if (userRepo.existsByEmail(createUserFormDTO.getEmail())) {
			throw new ApplicationContextException(
					EmployeePortalConstants.ERRROR_MSG_USER_INFORMATION_ALREADY_REGISTERED);
		}
		UserVO userVO = getUserVOFromCreateUserFormDTO(createUserFormDTO);
		userVO.setOrganizationVO(organizationRepo.findById(createUserFormDTO.getOrgId())
				.orElseThrow(() -> new ApplicationException("No orginaization found.")));
		userRepo.save(userVO);
		userService.createUserAction(userVO.getEmail(), userVO.getUserId(),
				EmployeePortalConstants.USER_ACTION_ADD_ACCOUNT);
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
	}

	private UserVO getUserVOFromCreateUserFormDTO(CreateUserFormDTO createUserFormDTO) {
		UserVO userVO = new UserVO();
		userVO.setEmpcode(createUserFormDTO.getEmpCode());
		userVO.setEmpId(createUserFormDTO.getEmpId());
		userVO.setEmpname(createUserFormDTO.getEmpName());
		userVO.setEmail(createUserFormDTO.getEmail());
		try {
			userVO.setPassword(encoder.encode(CryptoUtils.getDecrypt(createUserFormDTO.getPassword())));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new ApplicationContextException(EmployeePortalConstants.ERRROR_MSG_UNABLE_TO_ENCODE_USER_PASSWORD);
		}
		userVO.setRole(Role.valueOf(createUserFormDTO.getRole().name()));
		userVO.setActive(true);
		return userVO;
	}

	@Transactional
	@Override
	public void createOrganization(CreateOrganizationFormDTO createOrganizationFormDTO) {
		String methodName = "createOrganization()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		if (ObjectUtils.isEmpty(createOrganizationFormDTO) || StringUtils.isBlank(createOrganizationFormDTO.getEmail())
				|| StringUtils.isBlank(createOrganizationFormDTO.getOrgName())) {
			throw new ApplicationContextException(
					EmployeePortalConstants.ERRROR_MSG_INVALID_ORGANIZATION_REGISTER_INFORMATION);
		} else if (userRepo.existsByEmail(createOrganizationFormDTO.getEmail())) {
			throw new ApplicationContextException(
					EmployeePortalConstants.ERRROR_MSG_ORGANIZATION_USER_INFORMATION_ALREADY_REGISTERED);
		} else if (organizationRepo.existsByName(createOrganizationFormDTO.getOrgName())) {
			throw new ApplicationContextException(
					EmployeePortalConstants.ERRROR_MSG_ORGANIZATION_INFORMATION_ALREADY_REGISTERED);
		}
		UserVO userVO = getUserVOFromCreateOrganizationFormDTO(createOrganizationFormDTO);
		userVO.setOrganizationVO(
				organizationRepo.save(getOrganizationVOFromCreateOrganizationFormDTO(createOrganizationFormDTO)));
		userRepo.save(userVO);
		userService.createUserAction(userVO.getEmail(), userVO.getUserId(),
				EmployeePortalConstants.USER_ACTION_ADD_ACCOUNT);
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
	}

	private OrganizationVO getOrganizationVOFromCreateOrganizationFormDTO(
			CreateOrganizationFormDTO createOrganizationFormDTO) {
		OrganizationVO organizationVO = new OrganizationVO();
		organizationVO.setName(createOrganizationFormDTO.getOrgName());
		organizationVO.setActive(true);
		organizationVO.setNoOfLicence(createOrganizationFormDTO.getNoOfLicence());
		return organizationVO;
	}

	private UserVO getUserVOFromCreateOrganizationFormDTO(CreateOrganizationFormDTO createOrganizationFormDTO) {
		UserVO userVO = new UserVO();
		userVO.setEmpname(createOrganizationFormDTO.getOrgName());
		userVO.setEmail(createOrganizationFormDTO.getEmail());
		try {
			userVO.setPassword(encoder.encode(CryptoUtils.getDecrypt(createOrganizationFormDTO.getPassword())));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new ApplicationContextException(EmployeePortalConstants.ERRROR_MSG_UNABLE_TO_ENCODE_USER_PASSWORD);
		}
		userVO.setRole(Role.valueOf(Role.ADMIN.name()));
		userVO.setActive(true);
		return userVO;
	}

	@Override
	public OrganizationVO updateOrginization(OrganizationDTO organizationDTO) throws ApplicationException {
		OrganizationVO organizationVO = organizationRepo.findById(organizationDTO.getId())
				.orElseThrow(() -> new ApplicationException("Organization not found."));
		organizationVO.setName(organizationDTO.getName());
		organizationVO.setOrgCode(organizationDTO.getOrgCode());
		organizationVO.setFounder(organizationDTO.getFounder());
		organizationVO.setCEO(organizationDTO.getCEO());
		organizationVO.setPhoneNumber(organizationDTO.getPhoneNumber());
		organizationVO.setAddress(organizationDTO.getAddress());
		organizationVO.setPAN(organizationDTO.getPAN());
		organizationVO.setGST(organizationDTO.getGST());
		organizationVO.setOrgLogo(organizationDTO.getOrgLogo());
		organizationVO.setActive(organizationDTO.isActive());
		return organizationRepo.save(organizationVO);
	}

	@Override
	public OrganizationVO getOrginizationById(Long orgId) throws ApplicationException {
		if (ObjectUtils.isEmpty(orgId)) {
			throw new ApplicationException("Invalid Organization Input");
		}
		return organizationRepo.findById(orgId).orElseThrow(() -> new ApplicationException("Organization not found."));
	}

	@Override
	public BranchVO craetebranch(BranchDTO branchDTO) throws ApplicationException {
		BranchVO branchVO = new BranchVO();
		if (ObjectUtils.isNotEmpty(branchDTO.getId())) {
			branchVO = branchRepo.findById(branchDTO.getId())
					.orElseThrow(() -> new ApplicationException("Branch Not found"));
			branchVO.setActive(branchDTO.isActive());
		} else {
			branchVO.setActive(true);
		}
		OrganizationVO organizationVO = organizationRepo.findById(branchDTO.getOrgId())
				.orElseThrow(() -> new ApplicationException("Organization Not found"));
		branchVO.setOrganizationVO(organizationVO);
		branchVO.setBranchCode(branchDTO.getBranchCode());
		branchVO.setBranchManager(branchDTO.getBranchManager());
		branchVO.setPhoneNumber(branchDTO.getPhoneNumber());
		branchVO.setAddress(branchDTO.getAddress());
		branchVO.setPAN(branchDTO.getPAN());
		branchVO.setGST(branchDTO.getGST());
		branchVO.setBranchName(branchDTO.getBranchName());
		return branchRepo.save(branchVO);
	}

	@Override
	public BranchVO getBranchById(Long branchId) throws ApplicationException {
		if (ObjectUtils.isEmpty(branchId)) {
			throw new ApplicationException("Invalid Branch Input");
		}
		return branchRepo.findById(branchId).orElseThrow(() -> new ApplicationException("Branch not found."));
	}

	@Override
	public List<OrganizationVO> getAllOrganization() {
		return organizationRepo.findAll();
	}

	@Override
	public List<BranchVO> getBranchByOrgId(Long orgId) {
		List<BranchVO> branchVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received  Branch Information BY OrgId : {}", orgId);
			branchVO = branchRepo.getBranchByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received  Branch Information For All OrgId.");
			branchVO = branchRepo.findAll();
		}
		return branchVO;
	}

	@Override
	public Set<Object[]> getBranchCodeBranchNameByOrgId(Long orgId) {
		
		return branchRepo.findBranchCodeAndBranchByOrgId(orgId);
	}

}
