package com.altimetrik.bcp.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.altimetrik.bcp.config.FileStorageProperties;
import com.altimetrik.bcp.exception.FileStorageException;

import java.io.*;

@Service
public class FileUploadService {
	
	String attendanceDir;
	
	private final Path fileStorageLocation;
	
	public FileUploadService() {
		attendanceDir = "attendance";
		this.fileStorageLocation = Paths.get(attendanceDir)
                .toAbsolutePath().normalize();
		try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
	}

	public String storeFile(MultipartFile uploadfile) throws Exception{
		 String fileName = StringUtils.cleanPath(uploadfile.getOriginalFilename());
		 try{
		 Path targetLocation = this.fileStorageLocation.resolve(fileName);
         Files.copy(uploadfile.getInputStream(), targetLocation);
		 }catch(IOException ex){
			 throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		 }
		return fileName;
	}
}
