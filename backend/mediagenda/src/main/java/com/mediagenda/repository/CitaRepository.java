package com.mediagenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mediagenda.model.cita;

@Repository
public interface CitaRepository extends JpaRepository<cita, Long> {
}