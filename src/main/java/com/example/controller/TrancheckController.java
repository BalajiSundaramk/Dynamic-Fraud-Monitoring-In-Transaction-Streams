package com.example.controller;

import com.example.model.TrancheckEntry;
import com.example.service.TrancheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
public class TrancheckController {

    @Autowired
    private TrancheckService trancheckService;

    @GetMapping("/trancheck")
    public String showForm() {
        return "trancheck"; // This should match your trancheck.html view name
    }

    @PostMapping("/submit-trancheck")
    public String handleTrancheckForm(
            @RequestParam("transactionId") String transactionId,
            @RequestParam("amount") Double amount,
            @RequestParam("cardHolder") String cardHolder,
            Model model
    ) {
        TrancheckEntry entry = new TrancheckEntry();
        entry.setTransactionId(transactionId);
        entry.setAmount(amount);
        entry.setCardHolder(cardHolder);
        entry.setTimestamp(LocalDateTime.now());

        trancheckService.save(entry);

        model.addAttribute("message", "Transaction submitted successfully!");
        return "trancheck"; // Renders the same page with message
    }
}
