package com.example.demo.Repositorys;
import com.example.demo.Models.ApplicationUser;
import org.springframework.data.repository.CrudRepository;

// when create this interFace(ApplicationUserRepository) and extends it from Crud that's inform spring boots,
// this repository interface will deal with object of type  applicationUser

public interface ApplicationUserRepository extends CrudRepository <ApplicationUser,Integer> {
    public ApplicationUser findByUsername(String username);      //to let spring boot to generate query for username

}
