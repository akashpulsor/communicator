package com.communicate.dao;

import org.springframework.data.repository.Repository;

import com.communicate.model.MediaKey;
import com.communicate.model.MediaLibrary;

public interface MediaLibraryRepository extends  Repository<MediaLibrary, Long>  {

	MediaLibrary  save( MediaLibrary mediaLibrary );
	
	MediaLibrary findByMediaKey( MediaKey mediaKey );
	
	
}
