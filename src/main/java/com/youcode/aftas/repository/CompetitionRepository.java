package com.youcode.aftas.repository;


import com.youcode.aftas.entities.Competition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {

    boolean existsByDate(LocalDate date);
}
