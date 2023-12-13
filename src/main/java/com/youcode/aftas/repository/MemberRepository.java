package com.youcode.aftas.repository;


import com.youcode.aftas.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository< Member, Long> {
    List<Member> findByName(String name);

    boolean existsByIdentityNumber(String identity);

     Optional<Member>  findByIdentityNumber(String identity);

    List<Member> findByIdentityNumberOrNameOrFamilyName(String identityNumber,String name,String familyName);
}
