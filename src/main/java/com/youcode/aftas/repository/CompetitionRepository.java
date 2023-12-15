package com.youcode.aftas.repository;


import com.youcode.aftas.entities.Competition;

import com.youcode.aftas.entities.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {

    boolean existsByDate(LocalDate date);
    Optional<Competition> findByCode(String code);

    @Query("SELECT c FROM Competition c WHERE c.date = :today ")
    Competition findCompetitionByDate(@Param("today") LocalDate today);

    @Query("SELECT c FROM Competition c WHERE c.date >= :currentDate ")
    List<Competition> findAvailableCompetitions(@Param("currentDate") LocalDate currentDate);


}
