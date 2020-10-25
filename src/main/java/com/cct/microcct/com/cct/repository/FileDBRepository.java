package com.cct.microcct.com.cct.repository;

import com.cct.microcct.com.cct.model.Entité.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, Integer>{

    @Modifying
    @Query("update FileDB set data = :image where id_image = :id")
    void updateImageJoueur(int id, byte[] image);
}
