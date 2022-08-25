package com.netclip.netclips.web.rest;

import com.netclip.netclips.domain.Video;
import com.netclip.netclips.security.AuthoritiesConstants;
import com.netclip.netclips.service.S3Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

public class S3Controller {

    private final Logger log = LoggerFactory.getLogger(S3Controller.class);

    @Autowired
    private S3Service s3Service;

    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @GetMapping("/files")
    @ResponseBody
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public List<String> listAllFiles() {
        return s3Service.getAllFileKeys();
    }

    @PostMapping("/upload")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public String upload(@RequestPart(value = "file")MultipartFile file) {
        return s3Service.uploadFile(file);
    }

}
