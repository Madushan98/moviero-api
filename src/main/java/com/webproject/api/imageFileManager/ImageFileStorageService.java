package com.webproject.api.imageFileManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.webproject.api.entity.ImageFileData;
import com.webproject.api.exceptions.FileNotFoundException;
import com.webproject.api.exceptions.FileStorageException;
import com.webproject.api.repository.ImageFileRepository;

import java.io.IOException;

@Service
public class ImageFileStorageService {

    @Autowired
    private ImageFileRepository imageFileRepository;

    public ImageFileData storeFile(MultipartFile file) {
      
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
       
        
        
        String filetype = file.getContentType() ;
        
      
        
        try {
          
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            ImageFileData dbFile = new ImageFileData(fileName, file.getContentType(), file.getBytes());

            return imageFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public ImageFileData getFile(String fileId) {
        return imageFileRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found with id " + fileId));
    }
}