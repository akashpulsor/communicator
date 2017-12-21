package com.communicate.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import com.communicate.dao.UserRepository;
import com.communicate.model.User;

@Service
public class UserManagerImplementation implements UserManager{

	@Autowired
	UserRepository userDao;
	
    //@PersistenceContext
    //private EntityManager entityManager;
    
    //private  StorageService storageService;
    @Autowired
	ImageStorageService imageUploadService;
	 
	@Override
	public User createUser(RegistrationForm regForm) throws Exception {
		User user = new User();
		user.setEmail(regForm.getEmail());
		user.setName(regForm.getName());
		user.setPassword(regForm.getPassword());
		user.setMobileNumber(regForm.getMobileNumber());
		user.setGender(regForm.getGender());
		user.setSexualInterest(regForm.getSexualInterest());
		user.setBirthDate(regForm.getBirthDate());
		userDao.save(user);
		// TODO Auto-generated method stub
		return user;
	}

	@SuppressWarnings("deprecation")
	@Override
	public User authenticateUser( String userName, String password ) throws Exception {
		// TODO Auto-generated method stub
		User user = userDao.findByEmailIgnoreCase(userName);
		Assert.notNull(user);
		if( user.getPassword().equals(password) ){
			return user;
		}
		throw new Exception();
	}
	
	public boolean storeImage( long userId, MultipartFile image ) {
		// Validate File
		// if albumId doesn't exists 
		// Create one
		// Create image id
		// Store in Album Table
		// Store Album Id and ProfilePic id in User Table
		// If Image upload successful then return true other wise return false
		// Use Assert function
		imageUploadService.store(image);
		return true;
		
	}
	
	public Resource getImage( long imageId, long albumId  ) {
		// Create file path using image Id,User Id, Album Id
		//imageUploadService.load(image);
		return null;
		
	}
	
	
	private DashBoard createDashBoard( User user ) {
		DashBoard dashboard = new DashBoard(user);
		return dashboard;
	}
	
	
}
