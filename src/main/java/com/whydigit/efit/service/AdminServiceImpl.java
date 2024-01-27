package com.whydigit.efit.service;

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
import com.whydigit.efit.dto.CreateOrganizationFormDTO;
import com.whydigit.efit.dto.CreateUserFormDTO;
import com.whydigit.efit.dto.OrganizationDTO;
import com.whydigit.efit.dto.Role;
import com.whydigit.efit.entity.OrganizationVO;
import com.whydigit.efit.entity.UserVO;
import com.whydigit.efit.exception.ApplicationException;
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

	@Override
	public void createUser(CreateUserFormDTO createUserFormDTO) throws ApplicationException {
		String methodName = "createUser()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		if (ObjectUtils.isEmpty(createUserFormDTO) || StringUtils.isBlank(createUserFormDTO.getEmail())) {
			throw new ApplicationContextException(EmployeePortalConstants.ERRROR_MSG_INVALID_USER_REGISTER_INFORMATION);
		} else if (userRepo.existsByEmail(createUserFormDTO.getEmail())) {
			throw new ApplicationContextException(EmployeePortalConstants.ERRROR_MSG_USER_INFORMATION_ALREADY_REGISTERED);
		}
		UserVO userVO = getUserVOFromCreateUserFormDTO(createUserFormDTO);
		userVO.setOrganizationVO(organizationRepo.findById(createUserFormDTO.getOrgId())
				.orElseThrow(() -> new ApplicationException("No orginaization found.")));
		userRepo.save(userVO);
		userService.createUserAction(userVO.getEmail(), userVO.getUserId(), EmployeePortalConstants.USER_ACTION_ADD_ACCOUNT);
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
	}

	private UserVO getUserVOFromCreateUserFormDTO(CreateUserFormDTO createUserFormDTO) {
		UserVO userVO = new UserVO();
		userVO.setEmpcode(createUserFormDTO.getEmpCode());
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
		String methodName = "createUser()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		if (ObjectUtils.isEmpty(createOrganizationFormDTO) || StringUtils.isBlank(createOrganizationFormDTO.getEmail())
				|| StringUtils.isBlank(createOrganizationFormDTO.getOrganizationDTO().getName())) {
			throw new ApplicationContextException(EmployeePortalConstants.ERRROR_MSG_INVALID_ORGANIZATION_REGISTER_INFORMATION);
		} else if (userRepo.existsByEmail(createOrganizationFormDTO.getEmail())) {
			throw new ApplicationContextException(
					EmployeePortalConstants.ERRROR_MSG_ORGANIZATION_USER_INFORMATION_ALREADY_REGISTERED);
		} else if (organizationRepo.existsByName(createOrganizationFormDTO.getOrganizationDTO().getName())) {
			throw new ApplicationContextException(EmployeePortalConstants.ERRROR_MSG_ORGANIZATION_INFORMATION_ALREADY_REGISTERED);
		}
		UserVO userVO = getUserVOFromCreateOrganizationFormDTO(createOrganizationFormDTO);
		userVO.setOrganizationVO(
				organizationRepo.save(getOrganizationVOFromCreateOrganizationFormDTO(createOrganizationFormDTO)));
		userRepo.save(userVO);
		userService.createUserAction(userVO.getEmail(), userVO.getUserId(), EmployeePortalConstants.USER_ACTION_ADD_ACCOUNT);
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
	}

	private OrganizationVO getOrganizationVOFromCreateOrganizationFormDTO(
			CreateOrganizationFormDTO createOrganizationFormDTO) {
		OrganizationVO organizationVO = new OrganizationVO();
		OrganizationDTO organizationDTO = createOrganizationFormDTO.getOrganizationDTO();
		organizationVO.setName(organizationDTO.getName());
		organizationVO.setCity(organizationDTO.getCity());
		organizationVO.setCountry(organizationDTO.getCountry());
		organizationVO.setOrgLogo(organizationDTO.getOrgLogo());
		organizationVO.setActive(true);
		organizationVO.setPhoneNumber(organizationDTO.getPhoneNumber());
		organizationVO.setPostalCode(organizationDTO.getPostalCode());
		organizationVO.setState(organizationDTO.getState());
		organizationVO.setStreet(organizationDTO.getStreet());
		return organizationVO;
	}

	private UserVO getUserVOFromCreateOrganizationFormDTO(CreateOrganizationFormDTO createOrganizationFormDTO) {
		UserVO userVO = new UserVO();
		userVO.setEmpcode(createOrganizationFormDTO.getEmpCode());
		userVO.setEmpname(createOrganizationFormDTO.getEmpName());
		userVO.setEmail(createOrganizationFormDTO.getEmail());
		try {
			userVO.setPassword(encoder.encode(CryptoUtils.getDecrypt(createOrganizationFormDTO.getPassword())));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new ApplicationContextException(EmployeePortalConstants.ERRROR_MSG_UNABLE_TO_ENCODE_USER_PASSWORD);
		}
		userVO.setRole(Role.valueOf(createOrganizationFormDTO.getRole().name()));
		userVO.setActive(true);
		return userVO;
	}

}
