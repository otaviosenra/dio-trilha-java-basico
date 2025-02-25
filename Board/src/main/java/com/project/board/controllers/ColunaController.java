package com.project.board.controllers;

import com.project.board.exceptions.ColunaNotFoundException;
import com.project.board.exceptions.DatabaseException;
import com.project.board.exceptions.ErrorResponse;
import com.project.board.models.Coluna;
import com.project.board.services.ColunaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/colunas")
public class ColunaController {

    @Autowired
    private ColunaService colunaService;

    @GetMapping
    public ResponseEntity<?> getAllColunas() {
        try {
            List<Coluna> colunas = colunaService.getAllColunas();
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Colunas recuperadas com sucesso.");
            response.put("data", colunas);
            return ResponseEntity.ok(response);
        } catch (DatabaseException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro no banco de dados",
                e.getMessage(),
                "/colunas"
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno no servidor",
                e.getMessage(),
                "/colunas"
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getColunaById(@PathVariable Long id) {
        try {
            Optional<Coluna> coluna = colunaService.getColunaById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Coluna encontrada com sucesso.");
            response.put("data", coluna.get());
            return ResponseEntity.ok(response);
        } catch (ColunaNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Recurso não encontrado",
                e.getMessage(),
                "/colunas/" + id
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (DatabaseException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro no banco de dados",
                e.getMessage(),
                "/colunas/" + id
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno no servidor",
                e.getMessage(),
                "/colunas/" + id
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteColuna(@PathVariable Long id) {
        try {
            colunaService.deleteColuna(id);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Coluna marcada como deletada com sucesso.");
            return ResponseEntity.ok(response);
        } catch (ColunaNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Recurso não encontrado",
                e.getMessage(),
                "/colunas/" + id
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (DatabaseException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro no banco de dados",
                e.getMessage(),
                "/colunas/" + id
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno no servidor",
                e.getMessage(),
                "/colunas/" + id
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}