package com.example.demo;

import com.example.demo.Models.ApplicationUser;
import com.example.demo.Repositorys.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// second step : UserDetailsServiceImpl implements UserDetailsService

@Service  // this class has instance out of it  can use it when ever I want using Autowired for instances
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);          // passing the class(applicationUser) that implements userDetails
        if (applicationUser == null) {          // error handling (if user equal null it dose not exist in database)
            throw new UsernameNotFoundException("the user " + username + "Dose not exist");
        }
        return applicationUser;
    }
}
