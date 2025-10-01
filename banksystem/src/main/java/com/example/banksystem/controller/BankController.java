package com.example.banksystem.controller;

import com.example.banksystem.service.AuthService;
import com.example.banksystem.service.BankService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BankController {

    @Autowired
    private BankService bankService;

    @Autowired
    private AuthService authService;


    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        String pin = (String) session.getAttribute("pin");
        if (pin == null) {
            return "redirect:/login?error=Please login first";
        }
        model.addAttribute("balance", bankService.getBalance(pin));
        return "dashboard";
    }


    @GetMapping("/deposit")
    public String depositPage(HttpSession session) {
        if (session.getAttribute("pin") == null) {
            return "redirect:/login?error=Please login first";
        }
        return "deposit";
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam int amount, HttpSession session, Model model) {
        String pin = (String) session.getAttribute("pin");
        if (pin == null) {
            return "redirect:/login?error=Please login first";
        }
        try {
            bankService.deposit(pin, amount);
            return "redirect:/dashboard";
        } catch (Exception e) {
            model.addAttribute("error", "Deposit failed: " + e.getMessage());
            return "deposit";
        }
    }


    @GetMapping("/withdraw")
    public String withdrawPage(HttpSession session) {
        if (session.getAttribute("pin") == null) {
            return "redirect:/login?error=Please login first";
        }
        return "withdraw";
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam int amount, HttpSession session, Model model) {
        String pin = (String) session.getAttribute("pin");
        if (pin == null) {
            return "redirect:/login?error=Please login first";
        }
        try {
            bankService.withdraw(pin, amount);
            return "redirect:/dashboard";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "withdraw";
        }
    }


    @GetMapping("/balance")
    public String balance(HttpSession session, Model model) {
        String pin = (String) session.getAttribute("pin");
        if (pin == null) {
            return "redirect:/login?error=Please login first";
        }
        model.addAttribute("balance", bankService.getBalance(pin));
        return "balance";
    }


    @GetMapping("/mini")
    public String miniStatement(HttpSession session, Model model) {
        String pin = (String) session.getAttribute("pin");
        if (pin == null) {
            return "redirect:/login?error=Please login first";
        }
        model.addAttribute("transactions", bankService.getMiniStatement(pin));
        return "mini";
    }

    @GetMapping("/recentTransactions")
    public String recentTransactions(HttpSession session, Model model) {
        String pin = (String) session.getAttribute("pin");
        if (pin == null) {
            return "redirect:/login?error=Please login first";
        }
        model.addAttribute("recentTransactions", bankService.getRecentTransactionTypes());
        return "recentTransactions";
    }

    @GetMapping("/gc-demo")
    public String garbageCollectionDemo(HttpSession session, Model model) {
        String pin = (String) session.getAttribute("pin");
        if (pin == null) {
            return "redirect:/login?error=Please login first";
        }
        authService.demonstrateGarbageCollection();
        model.addAttribute("message", "Garbage collection demo completed. Check logs for details.");
        return "gc-demo";
    }
}
