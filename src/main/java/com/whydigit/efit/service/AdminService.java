package com.whydigit.efit.service;

import org.springframework.stereotype.Service;

import com.whydigit.efit.dto.CreateOrganizationFormDTO;
import com.whydigit.efit.dto.CreateUserFormDTO;
import com.whydigit.efit.exception.ApplicationException;

@Service
public interface AdminService {

	public void createUser(CreateUserFormDTO createUserFormDTO) throws ApplicationException;

	void createOrganization(CreateOrganizationFormDTO createOrganizationFormDTO);
}
