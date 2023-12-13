package com.youcode.aftas.service.impl;


import com.youcode.aftas.entities.Competition;
import com.youcode.aftas.entities.Member;
import com.youcode.aftas.entities.Ranking;
import com.youcode.aftas.repository.RankingRepository;
import com.youcode.aftas.service.RankingService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RankingServiceImpl implements RankingService {

    final private RankingRepository rankingRepository;

    public RankingServiceImpl(RankingRepository rankingRepository) {
        this.rankingRepository = rankingRepository;
    }

    @Override
    public Ranking create(Ranking member) {
        return null;
    }

    @Override
    public Ranking update(Ranking member) {
        return null;
    }

    @Override
    public void delete(Ranking member) {

    }

    @Override
    public Optional<Ranking> findById(Ranking member) {
        return Optional.empty();
    }

    @Override
    public List<Ranking> findAll() {
        return null;
    }

    @Override
    public Ranking findByMemberAndCompetition(Member member, Competition competition) {
        return null;
    }

    @Override
    public List<Ranking> sortMemberWithPoints() {
        return null;
    }
}
