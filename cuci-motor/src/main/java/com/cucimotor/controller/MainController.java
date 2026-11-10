package com.cucimotor.controller;

import com.cucimotor.model.Antrian;
import com.cucimotor.repository.AntrianRepository;
import com.cucimotor.repository.LayananRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @Autowired
    private AntrianRepository antrianRepo;

    @Autowired
    private LayananRepository layananRepo;

    // Direct Root ke Login
    @GetMapping("/")
    public String index(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/dashboard";
        }
        return "redirect:/login";
    }

    // Halaman Login GET
    @GetMapping("/login")
    public String loginPage(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/dashboard";
        }
        return "login";
    }

    // Proses Form Login POST
    @PostMapping("/login")
    public String processLogin(@RequestParam String username, 
                               @RequestParam String password, 
                               HttpSession session, 
                               Model model) {
        if ("petugas".equals(username) && "petugas123".equals(password)) {
            session.setAttribute("user", username);
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Username atau Password salah!");
            return "login";
        }
    }

    // Process Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    // Dashboard (Dipelindungi Session Check)
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        model.addAttribute("antrianList", antrianRepo.findAll());
        model.addAttribute("layananList", layananRepo.findAll());
        model.addAttribute("newAntrian", new Antrian());
        return "dashboard";
    }

    @PostMapping("/antrian/tambah")
    public String tambahAntrian(@ModelAttribute Antrian antrian, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        antrian.setStatus("DIPROSES");
        antrianRepo.save(antrian);
        return "redirect:/dashboard";
    }

    @GetMapping("/antrian/update-status/{id}")
    @ResponseBody
    public String updateStatus(@PathVariable Long id, @RequestParam String status) {
        Antrian antrian = antrianRepo.findById(id).orElse(null);
        if (antrian != null) {
            antrian.setStatus(status);
            antrianRepo.save(antrian);
            return "OK";
        }
        return "ERROR";
    }
}