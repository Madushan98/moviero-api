package com.webproject.api.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.webproject.api.imageFileManager.ImageFileData;
import com.webproject.api.imageFileManager.ImageFileStorageService;
import com.webproject.api.imageFileManager.UploadResponse;


@RestController
@RequestMapping("file")
public class ImageFileController {

    private static final Logger logger = LoggerFactory.getLogger(ImageFileController.class);

    @Autowired
    private ImageFileStorageService dbFileStorageService;

    @PostMapping("/imageUpload")
    public UploadResponse uploadImageFile(@RequestParam("file") MultipartFile file) {
        ImageFileData dbFile = dbFileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("file/download/")
                .path(dbFile.getId())
                .toUriString();

        return new UploadResponse(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }
    
    @PostMapping("/uploadMultipleImageFiles")
    public List<UploadResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadImageFile(file))
                .collect(Collectors.toList());
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
        // Load file from database
        ImageFileData dbFile = dbFileStorageService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }
    
    



}