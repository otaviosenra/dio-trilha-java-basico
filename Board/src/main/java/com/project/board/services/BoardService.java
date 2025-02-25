package com.project.board.services;

import com.project.board.exceptions.BoardNotFoundException;
import com.project.board.exceptions.DatabaseException;
import com.project.board.models.Board;
import com.project.board.repositories.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public List<Board> getAllBoards() {
        try {
            return boardRepository.findByDeletedFalse();
        } catch (DataAccessException e) {
            throw new DatabaseException("Erro ao acessar o banco de dados.", e);
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao recuperar boards.", e);
        }
    }

    public Optional<Board> getBoardById(Long id) {
        try {
            Optional<Board> board = boardRepository.findByIdAndDeletedFalse(id);
            if (board.isEmpty()) {
                throw new BoardNotFoundException("Board não encontrado ou já deletado.");
            }
            return board;
        } catch (BoardNotFoundException e) {
            throw e;
        } catch (DataAccessException e) {
            throw new DatabaseException("Erro ao acessar o banco de dados.", e);
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao recuperar o board.", e);
        }
    }

    public Board createBoard(Board board) {
        try {
            board.setDeleted(false);
            return boardRepository.save(board);
        } catch (DataAccessException e) {
            throw new DatabaseException("Erro ao salvar o board no banco de dados.", e);
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao criar o board.", e);
        }
    }

    public void deleteBoard(Long id) {
        try {
            Optional<Board> boardOptional = boardRepository.findById(id);
            if (boardOptional.isPresent()) {
                Board board = boardOptional.get();
                board.setDeleted(true);
                boardRepository.save(board);
            } else {
                throw new BoardNotFoundException("Board não encontrado.");
            }
        } catch (BoardNotFoundException e) {
            throw e;
        } catch (DataAccessException e) {
            throw new DatabaseException("Erro ao atualizar o board no banco de dados.", e);
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao deletar o board.", e);
        }
    }
}