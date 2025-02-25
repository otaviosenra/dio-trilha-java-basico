package com.project.board.controllers;

import com.project.board.exceptions.CardNotFoundException;
import com.project.board.exceptions.ColunaNotFoundException;
import com.project.board.exceptions.DatabaseException;
import com.project.board.exceptions.ErrorResponse;
import com.project.board.models.Card;
import com.project.board.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping
    public ResponseEntity<?> createCard(@RequestBody Card card) {
        try {
            Card createdCard = cardService.createCard(card);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Card criado com sucesso.");
            response.put("data", createdCard);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (DatabaseException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro no banco de dados",
                e.getMessage(),
                "/cards"
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno no servidor",
                e.getMessage(),
                "/cards"
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PutMapping("/{cardId}/move")
    public ResponseEntity<?> moveCard(@PathVariable Long cardId, @RequestParam Long newColumnId) {
        try {
            Card movedCard = cardService.moveCard(cardId, newColumnId);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Card movido com sucesso.");
            response.put("data", movedCard);
            return ResponseEntity.ok(response);
        } catch (CardNotFoundException | ColunaNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Recurso não encontrado",
                e.getMessage(),
                "/cards/" + cardId + "/move"
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (DatabaseException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro no banco de dados",
                e.getMessage(),
                "/cards/" + cardId + "/move"
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno no servidor",
                e.getMessage(),
                "/cards/" + cardId + "/move"
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllCards() {
        try {
            List<Card> cards = cardService.getAllCards();
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Cards recuperados com sucesso.");
            response.put("data", cards);
            return ResponseEntity.ok(response);
        } catch (DatabaseException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro no banco de dados",
                e.getMessage(),
                "/cards"
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno no servidor",
                e.getMessage(),
                "/cards"
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCardById(@PathVariable Long id) {
        try {
            Optional<Card> card = cardService.getCardById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Card encontrado com sucesso.");
            response.put("data", card.get());
            return ResponseEntity.ok(response);
        } catch (CardNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Recurso não encontrado",
                e.getMessage(),
                "/cards/" + id
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (DatabaseException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro no banco de dados",
                e.getMessage(),
                "/cards/" + id
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno no servidor",
                e.getMessage(),
                "/cards/" + id
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCard(@PathVariable Long id) {
        try {
            cardService.deleteCard(id);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Card marcado como deletado com sucesso.");
            return ResponseEntity.ok(response);
        } catch (CardNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Recurso não encontrado",
                e.getMessage(),
                "/cards/" + id
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (DatabaseException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro no banco de dados",
                e.getMessage(),
                "/cards/" + id
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno no servidor",
                e.getMessage(),
                "/cards/" + id
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}