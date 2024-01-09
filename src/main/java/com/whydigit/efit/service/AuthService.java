package com.whydigit.efit.service;

import org.springframework.stereotype.Service;

import com.whydigit.efit.dto.ChangePasswordFormDTO;
import com.whydigit.efit.dto.LoginFormDTO;
import com.whydigit.efit.dto.RefreshTokenDTO;
import com.whydigit.efit.dto.ResetPasswordFormDTO;
import com.whydigit.efit.dto.UserResponseDTO;
import com.whydigit.efit.exception.ApplicationException;

@Service
public interface AuthService {

	public UserResponseDTO login(LoginFormDTO loginRequest);
	public void logout(String userName);
	public void changePassword(ChangePasswordFormDTO changePasswordRequest);
	public void resetPassword(ResetPasswordFormDTO resetPasswordRequest);
	public void removeUser(String userName);
	public RefreshTokenDTO getRefreshToken(String userName, String tokenId) throws ApplicationException;

}
