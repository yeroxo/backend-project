package com.example.demoweb.service;

import com.example.demoweb.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    public List<Post> listAllPosts(){
        return List.of(new Post("haha",1), new Post("Nohaha",3) ,new Post("haha!!!",50));
    }
}
