package com.cct.microcct.com.cct.service;

import com.cct.microcct.com.cct.model.Entité.FileDB;
import com.cct.microcct.com.cct.model.Entité.FileDBJoueur;
import com.cct.microcct.com.cct.model.Entité.Joueur;
import com.cct.microcct.com.cct.repository.FileDBJoueurRepository;
import com.cct.microcct.com.cct.repository.FileDBRepository;
import com.cct.microcct.com.cct.repository.JoueurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class FileStorageService {

    @Autowired
    private FileDBRepository fileDBRepository;

    @Autowired
    private FileDBJoueurRepository fileDBJoueurRepository;

    @Autowired
    private JoueurRepository joueurRepository;

    public FileDB store(MultipartFile file, int id) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB FileDB = new FileDB(file.getContentType(), file.getBytes(),fileName);
        FileDB fileSaved = fileDBRepository.save(FileDB);
        fileDBJoueurRepository.insertImage(fileSaved.getId(),id);
        return fileSaved;
    }

    public FileDB getFile(int id) {
        return fileDBRepository.findById(id).get();
    }

    public void updateFile(int id, MultipartFile image) throws IOException {
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        FileDB fileDB = new FileDB(image.getContentType(), image.getBytes(),fileName);
        fileDBRepository.updateImageJoueur(id,image.getBytes());
    }

    public FileDB getFileByJoueur(int id) {
        Joueur joueur = joueurRepository.findById(id).get();
        FileDBJoueur idImage = fileDBJoueurRepository.selectImageByJoueur(joueur);
        return getFile(idImage.getId().getId_image());
    }

    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }
}