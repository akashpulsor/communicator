package com.communicate.service;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.communicate.dao.MediaLibraryRepository;
import com.communicate.dao.UserRepository;
import com.communicate.model.MediaKey;
import com.communicate.model.MediaLibrary;
import com.communicate.model.MediaType;
import com.communicate.model.User;
import com.communicate.utils.ImageUtils;
import com.communicate.utils.Utils;

@Service
public class UserManagerImplementation implements UserManager{

	private static final Logger logger = Logger.getLogger( UserManagerImplementation.class );
	
	@Autowired
	UserRepository userDao;
	
	@Autowired
	MediaLibraryRepository albumDao;
	
	@Value("${Image.directory.url}") 
	private Path rootLocation;
	
    
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
		
		logger.info("storing object to database" + user.toString() );
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
	
	public User storeImage( String userId, MultipartFile image, boolean profilePic ) {
		// Validate File
		User user = userDao.findById(userId);
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
			logger.info("Creating Media Directory "+ albumPath );
			Utils.createDirectory(albumPath);
		}
		
		// Check if directory is  created or not
		if( !Utils.existsDirectory(albumPath)) {
			logger.error("Not able to create album directory" );
			user.setAlbumId(null);
			return user;
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
		// Create file path using image Id,User Id, Album Id
		//
		return file;
		
	}
	
	
}
