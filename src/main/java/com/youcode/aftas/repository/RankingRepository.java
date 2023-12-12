package com.youcode.aftas.repository;


import com.youcode.aftas.entities.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankingRepository   extends JpaRepository<Ranking, Long> {
}
