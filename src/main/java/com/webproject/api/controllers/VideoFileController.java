package com.webproject.api.controllers;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.webproject.api.imageFileManager.ImageFileData;
import com.webproject.api.imageFileManager.ImageFileStorageService;
import com.webproject.api.imageFileManager.UploadResponse;


import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("video")
public class VideoFileController {

    private static final Logger logger = LoggerFactory.getLogger(VideoFileController.class);

    @Autowired
    private ImageFileStorageService dbFileStorageService;

    @PostMapping("/upload")
    public UploadResponse uploadFile(@RequestParam("file") MultipartFile file) {
        ImageFileData dbFile = dbFileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("images/downloadFile/")
                .path(dbFile.getId())
                .toUriString();

        return new UploadResponse(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List<UploadResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
        // Load file from database
        ImageFileData dbFile = dbFileStorageService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }

}