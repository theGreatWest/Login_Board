package com.example.loginBoard.repository;

import com.example.loginBoard.model.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
