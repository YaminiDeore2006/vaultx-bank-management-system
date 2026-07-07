package com.bank.bank_management.service;

import com.bank.bank_management.dto.CardDTO;
import com.bank.bank_management.entity.Card;
import com.bank.bank_management.entity.Customer;
import com.bank.bank_management.repository.CardRepository;
import com.bank.bank_management.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // Apply Card
    // Apply Card
    public Card applyCard(CardDTO dto) {

        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Check if customer already has a pending card request
        if (cardRepository.existsByCustomer_IdAndStatus(dto.getCustomerId(), "PENDING")) {
            throw new RuntimeException("Customer already has a pending card request.");
        }

        Card card = new Card();

        card.setCustomer(customer);
        card.setCardType(dto.getCardType());

        // Generate 16-digit Card Number
        String cardNumber = String.valueOf(
                1000000000000000L + new Random().nextLong(900000000000000L)
        );

        card.setCardNumber(cardNumber);

        // Default Status
        card.setStatus("PENDING");

        return cardRepository.save(card);
    }

    // Get All Cards
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    // Get Pending Cards
    public List<Card> getPendingCards() {
        return cardRepository.findByStatus("PENDING");
    }

    // Approve Card
    public Card approveCard(Long id) {

        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Card not found"));

        card.setStatus("APPROVED");

        return cardRepository.save(card);
    }

    // Reject Card
    public Card rejectCard(Long id) {

        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Card not found"));

        card.setStatus("REJECTED");

        return cardRepository.save(card);
    }

    // Block Card
    public Card blockCard(Long id) {

        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Card not found"));

        card.setStatus("BLOCKED");

        return cardRepository.save(card);
    }

    // Unblock Card
    public Card unblockCard(Long id) {

        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Card not found"));

        card.setStatus("APPROVED");

        return cardRepository.save(card);
    }

    // DTO Mapping
    public CardDTO mapToDTO(Card card) {

        return new CardDTO(
                card.getId(),
                card.getCustomer().getId(),
                card.getCardType(),
                card.getCardNumber(),
                card.getStatus()
        );
    }
}