package com.communicate.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.communicate.Exception.StorageException;
import com.communicate.Exception.StorageFileNotFoundException;
import com.communicate.utils.Utils;

@Service
public class ImageStorageService implements StorageService {

	private static final Logger logger = Logger.getLogger( ImageStorageService.class );
	
	@Value("${Image.directory.url}") 
	private Path rootLocation;
	
	@Override
	public void init() {
		Utils.createDirectory( rootLocation );
	}

	@Override
	public void store(MultipartFile file, Path directory, String fileName ) {
		// TODO Auto-generated method stub
		String orignalFilename = StringUtils.cleanPath( file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + fileName );
            }
            if (orignalFilename.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + orignalFilename);
            }
            
            
            Files.copy(file.getInputStream(), directory.resolve(fileName),
                    StandardCopyOption.REPLACE_EXISTING );
        }
        catch (IOException e) {
        	logger.error("Failed to store image " + orignalFilename, e);
            throw new StorageException("Failed to store file " + orignalFilename, e);
        }


	}

	@Override
	public Stream<Path> loadAll() {
		
 
		return null;
	}

	@Override
	public Path load( Path fileName ) {
		// TODO Auto-generated method stub
		//return null;
		logger.info( "get image from " + fileName );
		return rootLocation.resolve( fileName );
	}

	@Override
	public Resource loadAsResource( Path fileName ) {
		// TODO Auto-generated method stub
  		try {
            Path file = load(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + fileName );

            }
        }
        catch (MalformedURLException e) {
        	logger.error("Could not read file: " + fileName, e);
            throw new StorageFileNotFoundException("Could not read file: " + fileName, e);
        }


	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

}
