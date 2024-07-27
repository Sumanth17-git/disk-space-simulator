package com.application.diskspacesimulator.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

@Service
public class DiskSpaceService {

    private static final Logger logger = LoggerFactory.getLogger(DiskSpaceService.class);
    private static final String BASE_DIR = "target/";

    public void createFile() {
        String fileName = "disk_space_simulator_" + System.currentTimeMillis() + ".txt";
        File file = new File(BASE_DIR + fileName);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            byte[] data = new byte[40 * 1024 * 1024]; // 40MB
            new Random().nextBytes(data); // Fill the array with random bytes
            fileOutputStream.write(data);
            logger.info("Created file: {}", fileName);
        } catch (IOException e) {
            logger.error("Failed to create file: {}", fileName, e);
            simulateApplicationError();
        }
    }

    public void deleteFiles() {
        File dir = new File(BASE_DIR);
        File[] files = dir.listFiles((d, name) -> name.startsWith("disk_space_simulator_"));
        if (files != null) {
            for (File file : files) {
                if (file.delete()) {
                    logger.info("Deleted file: {}", file.getName());
                } else {
                    logger.error("Failed to delete file: {}", file.getName());
                }
            }
        } else {
            logger.warn("No files found to delete.");
        }
    }

    private void simulateApplicationError() {
        try {
            throw new RuntimeException("Simulated application error for stack trace generation");
        } catch (RuntimeException e) {
            logger.error("Simulated application error occurred: {}", e.getMessage(), e);
        }
    }
}
