package com.youcode.aftas.repository;



import com.youcode.aftas.entities.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository  extends JpaRepository<Level, Long> {
}
