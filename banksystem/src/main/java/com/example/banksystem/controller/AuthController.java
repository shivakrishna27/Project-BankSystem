package com.example.banksystem.controller;

import com.example.banksystem.model.User;
import com.example.banksystem.service.AuthService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        logger.debug("Handling GET /signup");
        try {
            // Exception Handling
            model.addAttribute("user", new User());
            logger.debug("Added new User to model");
            return "signup";
        } catch (Exception e) {
            logger.error("Error in GET /signup: {}", e.getMessage(), e);
            model.addAttribute("error", "Failed to load signup form: " + e.getMessage());
            return "signup";
        }
    }

    @PostMapping("/signup")
    public String signup(
            @Valid @ModelAttribute("user") User user,
            BindingResult result,
            Model model
    ) {
        logger.debug("Handling POST /signup for user: {}", user.getName());

        if (result.hasErrors()) {
            logger.debug("Validation errors: {}", result.getAllErrors());
            StringBuilder errorMsg = new StringBuilder("Validation errors: ");
            result.getFieldErrors().forEach(error ->
                    errorMsg.append(error.getField())
                            .append(": ")
                            .append(error.getDefaultMessage())
                            .append("; ")
            );
            model.addAttribute("error", errorMsg.toString());
            return "signup";
        }

        try {
            User savedUser = authService.signup(user);
            logger.info("User signed up successfully with formNo: {}, cardNumber: {}",
                    savedUser.getFormNo(), savedUser.getCardNumber());

            return "redirect:/login?success=Signup successful, please login with card number: "
                    + savedUser.getCardNumber() + " and PIN: " + savedUser.getPin();

        } catch (Exception e) {
            logger.error("Error in POST /signup: {}", e.getMessage(), e);
            model.addAttribute("error", "Failed to sign up: " + e.getMessage());
            return "signup";
        }
    }


    @GetMapping("/login")
    public String showLoginForm(Model model) {
        logger.debug("Handling GET /login");
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @ModelAttribute("user") User user,
            HttpSession session,
            Model model
    ) {
        logger.debug("Handling POST /login with cardNumber: {}", user.getCardNumber());
        try {
            User authenticatedUser = authService.login(user.getCardNumber(), user.getPin());

            if (authenticatedUser != null) {
                logger.info("Login successful for cardNumber: {}", user.getCardNumber());
                session.setAttribute("pin", authenticatedUser.getPin());
                return "redirect:/dashboard";
            } else {
                logger.warn("Login failed for cardNumber: {}", user.getCardNumber());
                model.addAttribute("error", "Invalid card number or PIN");
                return "login";
            }

        } catch (Exception e) {
            logger.error("Error in POST /login: {}", e.getMessage(), e);
            model.addAttribute("error", "Login failed: " + e.getMessage());
            return "login";
        }
    }
}
