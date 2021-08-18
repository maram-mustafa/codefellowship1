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
            model.addAttribute("userData", p.getName());
            model.addAttribute("allUserData", applicationUserRepository.findByUsername(p.getName()));
        } else {
            model.addAttribute("userData", "No user");
            model.addAttribute("allUserData", new ApplicationUser());
        }
        return "user.html";
    }


    @PostMapping("/userprofile")
    public RedirectView addNewPost(Principal p , @RequestParam String body){
        Post post = new Post(body , applicationUserRepository.findByUsername(p.getName()));
        postRepository.save(post);
        return new RedirectView("/userprofile");
    }

    @GetMapping("/allusers")
    public  String getAllUser(Principal p,Model m){
        try {

            m.addAttribute("alluser",applicationUserRepository.findAll());

            ApplicationUser me = applicationUserRepository.findByUsername(p.getName());

            m.addAttribute("whoIFollow",me.getFollowers());


        }catch (NullPointerException e){

        }
        return "allUsers.html";
    }
//
//    @GetMapping("/allUsers")
//    public String getAllUsers(Principal p,Model model){
//        try{
//            model.addAttribute("userData",p.getName());
//            model.addAttribute("Allusers",applicationUserRepository.findAll());
//
//            ApplicationUser me = applicationUserRepository.findByUsername(p.getName());
//            model.addAttribute("whoIFollow",me.getFollowers());
//        }catch (NullPointerException e){
//            model.addAttribute("userData","");
//        }
//        return "allUsers.html";
//    }

//
//    @PostMapping("/follow")
//    public RedirectView addFollow(Principal p,@RequestParam int id){
//        ApplicationUser me = applicationUserRepository.findByUsername(p.getName());
//        ApplicationUser toFollow = applicationUserRepository.findById(id).get();
//        me.getFollowers().add(toFollow);
//
//        applicationUserRepository.save(me);
//        return new RedirectView("/feed");
//    }

    



}
