package com.youcode.aftas.repository;



import com.youcode.aftas.entities.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LevelRepository  extends JpaRepository<Level, Long> {
    Optional<Level> findLevelById(Long id);
    Optional<Level> findLevelByPoints(int points);
}
