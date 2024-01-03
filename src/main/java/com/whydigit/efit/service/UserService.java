package com.whydigit.efit.service;

import org.springframework.stereotype.Service;

import com.whydigit.efit.controller.RefreshTokenDTO;
import com.whydigit.efit.dto.ChangePasswordFormDTO;
import com.whydigit.efit.dto.LoginFormDTO;
import com.whydigit.efit.dto.ResetPasswordFormDTO;
import com.whydigit.efit.dto.SignUpFormDTO;
import com.whydigit.efit.dto.UserResponseDTO;
import com.whydigit.efit.entity.UserVO;
import com.whydigit.efit.exception.ApplicationException;

@Service
public interface UserService {

	public void signup(SignUpFormDTO signUpRequest);
	public UserResponseDTO login(LoginFormDTO loginRequest);
	public void logout(String userName);
	public void changePassword(ChangePasswordFormDTO changePasswordRequest);
	public void resetPassword(ResetPasswordFormDTO resetPasswordRequest);
	public UserVO getUserById(Long userId);
	public UserVO getUserByUserName(String userName);
	public void createUserAction(String userName, long userId, String actionType);
	public void removeUser(String userName);
	public RefreshTokenDTO getRefreshToken(String userName, String tokenId) throws ApplicationException;

}
