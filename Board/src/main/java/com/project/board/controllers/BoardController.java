package com.project.board.controllers;

import com.project.board.exceptions.BoardNotFoundException;
import com.project.board.exceptions.DatabaseException;
import com.project.board.exceptions.ErrorResponse;
import com.project.board.models.Board;
import com.project.board.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping
    public ResponseEntity<?> getAllBoards() {
        try {
            List<Board> boards = boardService.getAllBoards();
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Boards recuperados com sucesso.");
            response.put("data", boards);
            return ResponseEntity.ok(response);
        } catch (DatabaseException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro no banco de dados",
                e.getMessage(),
                "/boards"
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno no servidor",
                e.getMessage(),
                "/boards"
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBoardById(@PathVariable Long id) {
        try {
            Optional<Board> board = boardService.getBoardById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Board encontrado com sucesso.");
            response.put("data", board.get());
            return ResponseEntity.ok(response);
        } catch (BoardNotFoundException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (DatabaseException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro no banco de dados",
                e.getMessage(),
                "/boards/" + id
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno no servidor",
                e.getMessage(),
                "/boards/" + id
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping
    public ResponseEntity<?> createBoard(@RequestBody Board board) {
        try {
            Board createdBoard = boardService.createBoard(board);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Board criado com sucesso.");
            response.put("data", createdBoard);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (DatabaseException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro no banco de dados",
                e.getMessage(),
                "/boards"
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno no servidor",
                e.getMessage(),
                "/boards"
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long id) {
        try {
            boardService.deleteBoard(id);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Board marcado como deletado com sucesso.");
            return ResponseEntity.ok(response);
        } catch (BoardNotFoundException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (DatabaseException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro no banco de dados",
                e.getMessage(),
                "/boards/" + id
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno no servidor",
                e.getMessage(),
                "/boards/" + id
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}