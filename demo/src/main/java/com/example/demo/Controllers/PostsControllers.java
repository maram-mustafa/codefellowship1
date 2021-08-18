package com.example.demo.Controllers;

import com.example.demo.Models.ApplicationUser;
import com.example.demo.Models.Post;
import com.example.demo.Repositorys.ApplicationUserRepository;
import com.example.demo.Repositorys.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class PostsControllers {

    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    PostRepository postRepository;

    @GetMapping("/userprofile")
    public String UserProfile(Principal p , Model model) {
        if (applicationUserRepository != null) {
            model.addAttribute("userInfo", p.getName());
            model.addAttribute("allUserInfo", applicationUserRepository.findByUsername(p.getName()));
        } else {
            model.addAttribute("userInfo", "");
            model.addAttribute("allUserInfo", new ApplicationUser());
        }

        return "user.html";
    }



    @PostMapping("/userprofile")
    public RedirectView addNewPost(Principal p , @RequestParam String body){
        Post post = new Post(body , applicationUserRepository.findByUsername(p.getName()));
        postRepository.save(post);
        return new RedirectView("/userprofile");
    }




}
