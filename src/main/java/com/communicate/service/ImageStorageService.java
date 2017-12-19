package com.communicate.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.communicate.Exception.StorageException;
import com.communicate.Exception.StorageFileNotFoundException;

@Service
public class ImageStorageService implements StorageService {

	
	@Value("${Image.directory.url}") 
	private Path rootLocation;
	
	@Override
	public void init() {
		try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }// TODO Auto-generated method stub

	}

	@Override
	public void store(MultipartFile file) {
		// TODO Auto-generated method stub
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            Files.copy(file.getInputStream(), this.rootLocation.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }


	}

	@Override
	public Stream<Path> loadAll() {
		
 
		return null;
	}

	@Override
	public Path load(String filename) {
		// TODO Auto-generated method stub
		//return null;
		return rootLocation.resolve(filename);
	}

	@Override
	public Resource loadAsResource(String filename) {
		// TODO Auto-generated method stub
  		try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }


	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

}
