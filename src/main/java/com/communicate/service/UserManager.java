package com.communicate.service;

import org.springframework.core.io.Resource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import com.communicate.model.User;

public interface UserManager  extends UserDetailsService{
	
	public User createUser( RegistrationForm regform ) throws Exception;
	
	public User storeImage( String userId, MultipartFile image, boolean profilePic )
			throws Exception;
	
	public Resource getFile( String userId, String albumId, String imageId  );
	
}
