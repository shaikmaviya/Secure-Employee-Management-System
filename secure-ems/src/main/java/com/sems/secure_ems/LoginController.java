package com.sems.secure_ems;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    /**
     * Default root path - displays home
     */
    @GetMapping("/home")
    public String home() {
        return "home";
    }

    /**
     * Display the login page
     */
    @GetMapping("/")
    public String showLoginPage() {
        return "auth/auth-login";
    }

    /**
     * Display the home/dashboard page
     */

    /**
     * Handle login form submission
     */
    @PostMapping("/login")
    public String handleLogin(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "remember", required = false) String remember,
            Model model) {
        
        // TODO: Add authentication logic here
        // For now, redirect to home on successful login
        
        if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
            // Simple validation - replace with actual authentication
            return "redirect:/home";
        }
        
        model.addAttribute("error", "Invalid username or password");
        return "login";
    }

    /**
     * Logout endpoint
     */
    @GetMapping("/logout")
    public String logout() {
        // TODO: Add logout logic here (clear session, etc.)
        return "redirect:/login";
    }

    /**
     * Forgot password page
     */
    @GetMapping("/forgot-password")
    public String showForgotPasswordPage() {
        return "forgot-password";
    }

    /**
     * Register page
     */
    @GetMapping("/register")
    public String showRegisterPage() {
        return "auth-signup";
    }

    /**
     * Auth login page (direct route)
     */
    @GetMapping("/auth-login")
    public String showAuthLogin() {
        return "auth-login";
    }

    /**
     * Auth signup page (direct route)
     */
    @GetMapping("/auth-signup")
    public String showAuthSignup() {
        return "auth-signup";
    }

    /**
     * Employees page
     */
    @GetMapping("/employees")
    public String showEmployeesPage() {
        return "employees";
    }

    /**
     * Departments page
     */
    @GetMapping("/departments")
    public String showDepartmentsPage() {
        return "departments";
    }

    /**
     * Reports page
     */
    @GetMapping("/reports")
    public String showReportsPage() {
        return "reports";
    }

    /**
     * Settings page
     */
    @GetMapping("/settings")
    public String showSettingsPage() {
        return "settings";
    }

    /**
     * Attendance page
     */
    @GetMapping("/attendance")
    public String showAttendancePage() {
        return "attendance";
    }

    /**
     * Leave page
     */
    @GetMapping("/leave")
    public String showLeavePage() {
        return "leave";
    }

    /**
     * Payroll page
     */
    @GetMapping("/payroll")
    public String showPayrollPage() {
        return "payroll";
    }
}
