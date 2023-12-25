package com.youcode.aftas.repository;


import com.youcode.aftas.entities.Competition;
import com.youcode.aftas.entities.Fish;
import com.youcode.aftas.entities.Hunting;
import com.youcode.aftas.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HuntingRepository extends JpaRepository<Hunting, Long> {

    List<Hunting> findByCompetitionAndMember(Competition competition, Member member);


    Optional<Hunting> findHuntingByFishAndMemberAndCompetition(Fish fish, Member member, Competition competition);


}
