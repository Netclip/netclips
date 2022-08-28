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
@RequestMapping("/api/files")
public class S3Controller {

    private final Logger log = LoggerFactory.getLogger(S3Controller.class);

    @Autowired
    private S3Service s3Service;

    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @GetMapping("/")
    @ResponseBody
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public List<String> listAllFiles() {
        return s3Service.getAllFileKeys();
    }
    //    @PostMapping("/upload")
    //    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    //    public ResponseEntity<String> upload(@RequestPart(value = "file") MultipartFile file, @AuthenticationPrincipal User user) {
    //        try {
    //            s3Service.uploadFile(file);
    //
    //        } catch (Exception e) {
    //            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    //        }
    //    }
    //    @PostMapping("/upload/video")
    //    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    //    public

}
