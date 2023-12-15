package com.youcode.aftas.repository;


import com.youcode.aftas.entities.Competition;
import com.youcode.aftas.entities.Member;
import com.youcode.aftas.entities.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankingRepository   extends JpaRepository<Ranking, Long> {

   Ranking findByCompetitionAndMember(Competition competition, Member member);
   List<Ranking> findByOrderByScoreAsc();
}
