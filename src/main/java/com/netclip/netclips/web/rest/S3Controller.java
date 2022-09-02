package com.netclip.netclips.web.rest;

import com.netclip.netclips.domain.User;
import com.netclip.netclips.domain.Video;
import com.netclip.netclips.security.AuthoritiesConstants;
import com.netclip.netclips.service.S3Service;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/s3")
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

    @DeleteMapping("/files/delete")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<String> deleteFile(@RequestParam(name = "fileKey") String fileKey) {
        log.debug("REST request to delete file from S3 {}", fileKey);
        s3Service.deleteFileByFullKey(fileKey);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("get-video-url")
    @ResponseBody
    public String generatePresignedUrl(@RequestParam(name = "fileKey") String fileKey) {
        log.debug("REST request to generate presigned URL on bucket object: {}", fileKey);
        return s3Service.generatePresignedUrl(fileKey);
    }
}
