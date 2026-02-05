package com.sems.secure_ems.controller;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sems.secure_ems.entity.User;
import com.sems.secure_ems.service.AuthenticationService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
    
    @Autowired
    private AuthenticationService authenticationService;

    /**
     * Default root path - redirect based on session
     */
    @GetMapping("/")
    public String index(HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        return user != null ? "redirect:/home" : "redirect:/login";
    }

    /**
     * Display the login page
     */
    @GetMapping("/login")
    public String showLoginPage(HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        return user != null ? "redirect:/home" : "auth/auth-login";
    }

    /**
     * Display the home/dashboard page
     */
    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        return "home";
    }

    /**
     * Handle login form submission - authenticate with MySQL database
     */
    @PostMapping("/login")
    public String handleLogin(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "remember", required = false) String remember,
            HttpSession session,
            Model model) {
        
        // Authenticate user against MySQL database
        Optional<User> authenticatedUser = authenticationService.authenticate(username, password);
        
        if (authenticatedUser.isPresent()) {
            User user = authenticatedUser.get();
            // Store user in session
            session.setAttribute("loggedInUser", user);
            session.setAttribute("userId", user.getId());
            session.setAttribute("userRole", user.getRole());
            
            return "redirect:/home";
        }
        
        model.addAttribute("error", "Invalid username or password");
        return "auth/auth-login";
    }

    /**
     * Handle user logout
     */
    @GetMapping("/logout")
    public String handleLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
