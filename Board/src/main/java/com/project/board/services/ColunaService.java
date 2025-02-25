package com.project.board.services;

import com.project.board.exceptions.ColunaNotFoundException;
import com.project.board.exceptions.DatabaseException;
import com.project.board.models.Coluna;
import com.project.board.repositories.ColunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColunaService {

    @Autowired
    private ColunaRepository columnRepository;

    public List<Coluna> getAllColunas() {
        try {
            return columnRepository.findByDeletedFalse();
        } catch (DataAccessException e) {
            throw new DatabaseException("Erro ao acessar o banco de dados.", e);
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao recuperar colunas.", e);
        }
    }

    public Optional<Coluna> getColunaById(Long id) {
        try {
            Optional<Coluna> column = columnRepository.findByIdAndDeletedFalse(id);
            if (column.isEmpty()) {
                throw new ColunaNotFoundException("Coluna não encontrada ou já deletada.");
            }
            return column;
        } catch (ColunaNotFoundException e) {
            throw e;
        } catch (DataAccessException e) {
            throw new DatabaseException("Erro ao acessar o banco de dados.", e);
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao recuperar a coluna.", e);
        }
    }

    public void deleteColuna(Long id) {
        try {
            Optional<Coluna> columnOptional = columnRepository.findById(id);
            if (columnOptional.isPresent()) {
                Coluna column = columnOptional.get();
                column.setDeleted(true);
                columnRepository.save(column);
            } else {
                throw new ColunaNotFoundException("Coluna não encontrada.");
            }
        } catch (ColunaNotFoundException e) {
            throw e;
        } catch (DataAccessException e) {
            throw new DatabaseException("Erro ao atualizar a coluna no banco de dados.", e);
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao deletar a coluna.", e);
        }
    }
}