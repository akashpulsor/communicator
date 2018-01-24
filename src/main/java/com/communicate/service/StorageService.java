package com.communicate.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
	
	void init();

    void store(MultipartFile file, Path rootLocation, String FileName );

    Stream<Path> loadAll();

    Path load(  Path fileName );

    Resource loadAsResource( Path fileName );

    void deleteAll();



}
