package com.codeone.service.job;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

@Service
public class JobfileService {

	 public Resource loadFileAsResource(String filePath) throws Exception {
	        try {
	            Path path = Paths.get(filePath);
	            Resource resource = new UrlResource(path.toUri());
	            if (resource.exists()) {
	                return resource;
	            } else {
	                throw new Exception("File not found " + filePath);
	            }
	        } catch (Exception e) {
	            throw new Exception("File not found " + filePath, e);
	        }
	    }
	}
