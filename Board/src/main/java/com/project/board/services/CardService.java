package com.project.board.services;

import com.project.board.exceptions.CardNotFoundException;
import com.project.board.exceptions.ColunaNotFoundException;
import com.project.board.exceptions.DatabaseException;
import com.project.board.models.Card;
import com.project.board.models.Coluna;
import com.project.board.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ColunaService colunaService;

    public Card moveCard(Long cardId, Long newColumnId) {
        try {
            // Busca o card pelo ID
            Optional<Card> cardOptional = cardRepository.findById(cardId);
            if (cardOptional.isEmpty()) {
                throw new CardNotFoundException("Card não encontrado.");
            }

            // Busca a nova coluna pelo ID
            Optional<Coluna> newColumnOptional = colunaService.getColunaById(newColumnId);
            if (newColumnOptional.isEmpty()) {
                throw new ColunaNotFoundException("Coluna de destino não encontrada.");
            }

            // Atualiza a coluna do card
            Card card = cardOptional.get();
            Coluna newColumn = newColumnOptional.get();
            card.setColumn(newColumn);

            // Salva o card atualizado
            return cardRepository.save(card);
        } catch (CardNotFoundException | ColunaNotFoundException e) {
            throw e; // Repassa exceções específicas
        } catch (DataAccessException e) {
            throw new DatabaseException("Erro ao atualizar o card no banco de dados.", e);
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao mover o card.", e);
        }
    }

    public Card createCard(Card card) {
        try {
            card.setDeleted(false);
            return cardRepository.save(card);
        } catch (DataAccessException e) {
            throw new DatabaseException("Erro ao salvar o card no banco de dados.", e);
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao criar o card.", e);
        }
    }

    public List<Card> getAllCards() {
        try {
            return cardRepository.findByDeletedFalse();
        } catch (DataAccessException e) {
            throw new DatabaseException("Erro ao acessar o banco de dados.", e);
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao recuperar cards.", e);
        }
    }

    public Optional<Card> getCardById(Long id) {
        try {
            Optional<Card> card = cardRepository.findByIdAndDeletedFalse(id);
            if (card.isEmpty()) {
                throw new CardNotFoundException("Card não encontrado ou já deletado.");
            }
            return card;
        } catch (CardNotFoundException e) {
            throw e;
        } catch (DataAccessException e) {
            throw new DatabaseException("Erro ao acessar o banco de dados.", e);
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao recuperar o card.", e);
        }
    }

    public void deleteCard(Long id) {
        try {
            Optional<Card> cardOptional = cardRepository.findById(id);
            if (cardOptional.isPresent()) {
                Card card = cardOptional.get();
                card.setDeleted(true);
                cardRepository.save(card);
            } else {
                throw new CardNotFoundException("Card não encontrado.");
            }
        } catch (CardNotFoundException e) {
            throw e;
        } catch (DataAccessException e) {
            throw new DatabaseException("Erro ao atualizar o card no banco de dados.", e);
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao deletar o card.", e);
        }
    }
}