package com.cct.microcct.com.cct.controller;

import com.cct.microcct.com.cct.model.Entité.FileDB;
import com.cct.microcct.com.cct.service.FileStorageService;
import com.cct.microcct.com.cct.service.ResponseFile;
import com.cct.microcct.com.cct.service.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FileController {

    @Autowired
    private FileStorageService storageService;

    @Transactional
    @PostMapping("/upload/{id}")
    public ResponseEntity<ResponseMessage> uploadFile(@PathVariable int id ,@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            this.getFileByJoueur(id);
            message = "Une image est déjà présente " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        } catch (Exception e) {
            try {
                storageService.store(file,id);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception ex) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
    }

    @Transactional
    @PostMapping("/update/{id}")
    public ResponseEntity<ResponseMessage> updateFile(@PathVariable int id, @RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            storageService.updateFile(id, file);
            message = "Updated the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("------------------------------------------------------------------------------");
            System.out.println(e.getMessage());
            message = "Could not update the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable int id) {
        FileDB fileDB = storageService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }

    @GetMapping("/filesByJoueur/{id}")
    public ResponseEntity<byte[]> getFileByJoueur(@PathVariable int id) {
        FileDB fileDB = storageService.getFileByJoueur(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }
}
