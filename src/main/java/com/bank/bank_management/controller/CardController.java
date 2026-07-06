package com.bank.bank_management.controller;

import com.bank.bank_management.dto.CardDTO;
import com.bank.bank_management.entity.Card;
import com.bank.bank_management.response.ApiResponse;
import com.bank.bank_management.service.CardService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    // =========================
    // APPLY CARD
    // =========================
    @PostMapping
    public ApiResponse applyCard(@Valid @RequestBody CardDTO dto) {

        Card card = cardService.applyCard(dto);

        return new ApiResponse(
                "Card applied successfully",
                card,
                200
        );
    }

    // =========================
    // GET ALL CARDS
    // =========================
    @GetMapping
    public ApiResponse getAllCards() {

        List<CardDTO> cards = cardService.getAllCards()
                .stream()
                .map(cardService::mapToDTO)
                .toList();

        return new ApiResponse(
                "Cards fetched successfully",
                cards,
                200
        );
    }

    // =========================
    // GET PENDING CARDS
    // =========================
    @GetMapping("/status/PENDING")
    public ApiResponse getPendingCards() {

        List<CardDTO> cards = cardService.getPendingCards()
                .stream()
                .map(cardService::mapToDTO)
                .toList();

        return new ApiResponse(
                "Pending cards fetched",
                cards,
                200
        );
    }

    // =========================
    // APPROVE CARD
    // =========================
    @PutMapping("/{id}/approve")
    public ApiResponse approveCard(@PathVariable Long id) {

        Card card = cardService.approveCard(id);

        return new ApiResponse(
                "Card approved successfully",
                card,
                200
        );
    }

    // =========================
    // REJECT CARD
    // =========================
    @PutMapping("/{id}/reject")
    public ApiResponse rejectCard(@PathVariable Long id) {

        Card card = cardService.rejectCard(id);

        return new ApiResponse(
                "Card rejected successfully",
                card,
                200
        );
    }

    // =========================
    // BLOCK CARD
    // =========================
    @PutMapping("/{id}/block")
    public ApiResponse blockCard(@PathVariable Long id) {

        Card card = cardService.blockCard(id);

        return new ApiResponse(
                "Card blocked successfully",
                card,
                200
        );
    }

    // =========================
    // UNBLOCK CARD
    // =========================
    @PutMapping("/{id}/unblock")
    public ApiResponse unblockCard(@PathVariable Long id) {

        Card card = cardService.unblockCard(id);

        return new ApiResponse(
                "Card unblocked successfully",
                card,
                200
        );
    }
}