package com.project.board.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.board.models.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByDeletedFalse(); 
    Optional<Board> findByIdAndDeletedFalse(Long id);
}