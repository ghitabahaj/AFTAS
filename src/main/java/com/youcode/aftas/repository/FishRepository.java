package com.youcode.aftas.repository;

import com.youcode.aftas.entities.Fish;
import com.youcode.aftas.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FishRepository extends JpaRepository<Fish, Long> {

    Fish findByName(String name);

    boolean existsByName(String name);

}
