package com.youcode.aftas.service;


import com.youcode.aftas.entities.Competition;
import com.youcode.aftas.entities.Member;
import com.youcode.aftas.entities.Ranking;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RankingService {
    Ranking create(Ranking member);

    Ranking update(Ranking member);

    void delete(Ranking member);

    Optional<Ranking> findById(Ranking member);

    List<Ranking> findAll();
    Ranking findByMemberAndCompetition(Member member, Competition competition);

    List<Ranking> sortMemberWithPoints();

}
