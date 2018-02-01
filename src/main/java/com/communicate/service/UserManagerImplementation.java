package com.communicate.service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.communicate.dao.MediaLibraryRepository;
import com.communicate.dao.RolesRepository;
import com.communicate.dao.UserRepository;
import com.communicate.model.CustomUserDetails;
import com.communicate.model.MediaKey;
import com.communicate.model.MediaLibrary;
import com.communicate.model.MediaType;
import com.communicate.model.Role;
import com.communicate.model.Roles;
import com.communicate.model.User;
import com.communicate.utils.ImageUtils;
import com.communicate.utils.Utils;
import com.google.common.collect.Sets;

@Service
public class UserManagerImplementation implements UserManager {

	private static final Logger logger = Logger.getLogger( UserManagerImplementation.class );
	
	@Autowired
	UserRepository userDao;
	
	@Autowired
	MediaLibraryRepository albumDao;
	
	@Autowired
	RolesRepository roleRepository;
	
	@Value("${Image.directory.url}") 
	private Path rootLocation;
	
    
    @Autowired
	ImageStorageService imageUploadService;
	 
	@Override
	public User createUser(RegistrationForm regForm) throws Exception {
		User user = new User();
		user.setEmail(regForm.getEmail());
		user.setName(regForm.getFirstName()+" "+regForm.getLastName());
		user.setPassword(regForm.getPassword());
		user.setMobileNumber(regForm.getMobileNumber());
		user.setGender(regForm.getGender());
		user.setSexualInterest(regForm.getSexualInterest());
		user.setBirthDate(Utils.convertDateToEpochMillis(regForm.getBirthDate()));
		user.setRoles(Sets.newHashSet(roleRepository.findByRoleName(regForm.getRoles())));
		
		logger.info("storing object to database" + user.toString() );
		userDao.save(user);
		// TODO Auto-generated method stub
		return user;
	}

	
	public User storeImage( String userId, MultipartFile image, boolean profilePic ) {
		// Validate File
		Optional<User> optionalUser = userDao.findById(userId);
		optionalUser.
		orElseThrow(() -> new UsernameNotFoundException( "User Name not found"));
		User user = optionalUser.get(); 
		// if albumId doesn't exists 
		// Create one
		// Create image id
		if( user.getAlbumId() == null) {
			user.setAlbumId( ImageUtils.getId( userId ) );
		}
		
	
		String fileName = StringUtils.cleanPath( image.getOriginalFilename());
		String imageId = ImageUtils.getId() + user.getAlbumId(); 
		imageId = imageId + fileName; 
		
		MediaLibrary mediaLibrary = new MediaLibrary();
		MediaKey mediaKey = new MediaKey();
		mediaKey.setAlbumId( user.getAlbumId() );
		mediaKey.setMediaId(imageId);
		mediaKey.setUserId(user.getId());
		mediaLibrary.setMediaKey( mediaKey );
		mediaLibrary.setType(MediaType.IMAGE);
		mediaLibrary.setOriginalMediaName( fileName );
		// Store Album Id and ProfilePic id in User Table
		logger.info("Internal File name "+ imageId );
		Path albumPath  = Paths.get(this.rootLocation+"/"+mediaKey.getUserId()+"/" + mediaKey.getAlbumId());
		if( !Utils.existsDirectory(albumPath)) {
			logger.info( "Creating Media Directory "+ albumPath );
			
			// Check if directory is  created or not
			if( !Utils.createDirectory(albumPath) ) {
				logger.error("Not able to create album directory" );
				user.setAlbumId(null);
				return user;
				
			};
		}
		
		
		imageUploadService.store( image, albumPath, mediaKey.getMediaId().toString() );
		albumDao.save(mediaLibrary);
		
		if( profilePic == true ) {
			user.setProflePicId(imageId);
		}
		
		userDao.save(user);
		return user;
		
	}
	
	
	
	public Resource getFile( String userId, String albumId, String imageId  ) {
		
		Path filePath  = Paths.get(this.rootLocation+"/"+userId +"/"+ albumId + "/" + imageId );
		Resource file = null;
		if( Utils.existsDirectory( filePath )) {
			file = imageUploadService.loadAsResource( filePath );
		}
		return file;
		
	}

	@Override
	public UserDetails loadUserByUsername(String userName ) throws UsernameNotFoundException {
		
		Optional<User> optionalUser = null;
		if( Utils.isValidEmailAddress(userName) ) {
			optionalUser = userDao.findByEmailIgnoreCase(userName);
		}
		else {
			optionalUser = userDao.findByMobileNumber(userName);
		}
		optionalUser.
		orElseThrow(() -> new UsernameNotFoundException( "User Name not found"));
		return optionalUser.map( CustomUserDetails::new).get(); 
	}
	
	
}
