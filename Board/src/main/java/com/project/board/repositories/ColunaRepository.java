package com.project.board.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.board.models.Coluna;

@Repository
public interface ColunaRepository extends JpaRepository<Coluna, Long> {
    List<Coluna> findByDeletedFalse();
    Optional<Coluna> findByIdAndDeletedFalse(Long id);
}
