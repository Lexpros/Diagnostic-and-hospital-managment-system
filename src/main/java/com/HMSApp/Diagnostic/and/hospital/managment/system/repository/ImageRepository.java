package com.HMSApp.Diagnostic.and.hospital.managment.system.repository;

import com.HMSApp.Diagnostic.and.hospital.managment.system.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
    public void deleteById(Long id);
}
