package com.youcode.aftas.repository;


import com.youcode.aftas.entities.Competition;
import com.youcode.aftas.entities.Fish;
import com.youcode.aftas.entities.Hunting;
import com.youcode.aftas.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HuntingRepository extends JpaRepository<Hunting, Long> {

    List<Hunting> findByCompetitionAndMemberAndFish(Competition competition, Member member);

    Hunting findHuntingByFishAndMemberAndCompetition(Fish fish, Member member,Competition competition);
}
