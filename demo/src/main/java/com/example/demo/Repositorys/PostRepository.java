package com.example.demo.Repositorys;

import com.example.demo.Models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository <Post,Integer>{
}
