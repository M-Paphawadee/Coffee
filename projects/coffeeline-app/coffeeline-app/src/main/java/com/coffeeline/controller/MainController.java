package com.coffeeline.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coffeeline.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
    
    // หน้าแรก - ไปที่ app.html (หน้า SPA)
    @GetMapping("/")
    public String index() {
        return "forward:/index.html";
    }
    
    // Login page - Thymeleaf (ถ้ายังต้องการใช้)
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    
    // API Login สำหรับ HTML/AJAX
    @PostMapping("/login")
    @ResponseBody
    public Map<String, Object> doLogin(@RequestParam String username, 
                                       @RequestParam String password,
                                       HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        
        if (username == null || username.trim().isEmpty() || password.length() < 4) {
            result.put("success", false);
            result.put("message", "กรอกข้อมูลให้ครบ");
            return result;
        }
        
        String role = username.equalsIgnoreCase("admin") ? "ผู้ดูแลระบบ" : "พนักงาน";
        User user = new User(username, role);  // ใช้ name ตาม User model
        session.setAttribute("user", user);
        
        result.put("success", true);
        result.put("user", username);
        result.put("role", role);
        return result;
    }
    
    // API Logout
    @GetMapping("/logout")
    @ResponseBody
    public Map<String, Object> doLogout(HttpSession session) {
        session.invalidate();
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }
    
    // Dashboard page - Thymeleaf (ถ้ามี)
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        model.addAttribute("user", user);
        return "dashboard";
    }
    
    // API ตรวจสอบว่า login แล้วหรือยัง
    @GetMapping("/api/check-auth")
    @ResponseBody
    public Map<String, Object> checkAuth(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        User user = (User) session.getAttribute("user");
        
        if (user != null) {
            result.put("authenticated", true);
            result.put("user", user.getName());
            result.put("role", user.getRole());
        } else {
            result.put("authenticated", false);
        }
        
        return result;
    }
}