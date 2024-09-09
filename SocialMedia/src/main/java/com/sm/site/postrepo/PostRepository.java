package com.sm.site.postrepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sm.site.post.Post;



public interface PostRepository extends JpaRepository <Post, Integer>{

}
