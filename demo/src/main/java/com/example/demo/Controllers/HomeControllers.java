package com.example.demo.Controllers;

import com.example.demo.Repositorys.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class HomeControllers {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @GetMapping("/")
    public String getHome(Principal p, Model model){
        try{
            model.addAttribute("username",p.getName());
        }catch (NullPointerException e){
            model.addAttribute("username","No user");
        }
        return "home.html";
    }
}
