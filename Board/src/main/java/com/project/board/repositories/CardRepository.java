package com.project.board.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.board.models.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByDeletedFalse(); 
    Optional<Card> findByIdAndDeletedFalse(Long id); 
}