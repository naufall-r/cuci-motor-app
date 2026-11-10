package com.cucimotor.repository;

import com.cucimotor.model.Antrian;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AntrianRepository extends JpaRepository<Antrian, Long> {
}