package com.application.diskspacesimulator.controller;

import com.application.diskspacesimulator.service.DiskSpaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/disk-space")
public class DiskSpaceController {

    private static final Logger logger = LoggerFactory.getLogger(DiskSpaceController.class);

    @Autowired
    private DiskSpaceService diskSpaceService;
//POST METHOD http://localhost:9090/disk-space/create
  
    @PostMapping("/create")
    public String createFile() {
        try {
            diskSpaceService.createFile();
            return "File created successfully";
        } catch (Exception e) {
            logger.error("Failed to create file: {}", e.getMessage(), e);
            return "Failed to create file: " + e.getMessage();
        }
    }
 
 //DELETE METHOD   http://localhost:8080/disk-space/delete
    @DeleteMapping("/delete")
    public String deleteFiles() {
        try {
            diskSpaceService.deleteFiles();
            return "Files deleted successfully";
        } catch (Exception e) {
            logger.error("Failed to delete files: {}", e.getMessage(), e);
            return "Failed to delete files: " + e.getMessage();
        }
    }
}
