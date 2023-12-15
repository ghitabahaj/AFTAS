package com.youcode.aftas.repository;


import com.youcode.aftas.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository< Member, Long> {
    List<Member> findByName(String name);

    boolean existsByIdentityNumber(String identity);


    @Query("SELECT m FROM Member m WHERE m.name LIKE %:keyword% OR m.identityNumber LIKE %:keyword% OR m.familyName LIKE %:keyword%")
    List<Member> searchMember(String keyword);


    Optional<Member>  findByIdentityNumber(String identity);

    List<Member> findByIdentityNumberOrNameOrFamilyName(String identityNumber,String name,String familyName);
}
