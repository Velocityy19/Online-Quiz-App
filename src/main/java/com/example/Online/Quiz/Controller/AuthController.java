package com.example.Online.Quiz.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.Online.Quiz.Models.Users;
import com.example.Online.Quiz.Service.UsersService;

import jakarta.servlet.http.HttpSession;


@Controller
public class AuthController {
    
    @Autowired
    UsersService usersService;

    // View Home
    @GetMapping("/home")
    public String viewHome() {
        return "home";
    }
    
    // Get mapping for register
   @GetMapping("/register")
    public String showRegistrationForm(Model model) {
     model.addAttribute("user", new Users());
        return "register"; 
    }

    //Post mapping for register
    @PostMapping("/register")
    public String registerUser(@ModelAttribute Users user) {
    if ("admin@gmail.com".equalsIgnoreCase(user.getEmail())) {
        user.setRole("ADMIN");
    } else {
        user.setRole("PARTICIPANT");
    }
    usersService.createUser(user);
    return "redirect:/login";
}

    //Get mapping for login
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new Users());
        return "login";
    }

//     // Get mapping for paticipants to land on login page 
//     @GetMapping("/start-quiz")
//     public String startQuiz(HttpSession session) {
//         Object user = session.getAttribute("user");
//         if (user == null) {
//             return "redirect:/login";
//         }
//         return "redirect:/participant/quizzes";
// }

    //Post mapping for login
    @PostMapping("/login")
    public String loginUser(@ModelAttribute Users user, HttpSession session, Model model) {
        Users existingUser= usersService.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if (existingUser == null) {
           model.addAttribute("error","User not found");
            return "redirect:/login";
        }
        if (!existingUser.getPassword().equals(user.getPassword())) {
        model.addAttribute("error", "Invalid password!");
        return "login";
    }
        session.setAttribute("user", existingUser);
          if ("ADMIN".equalsIgnoreCase(existingUser.getRole()) 
            || "admin@gmail.com".equalsIgnoreCase(existingUser.getEmail())){
                return "redirect:/admin/home";
        }
        else{
            return "redirect:/participant/quizzes";
        }
    }
    
}
