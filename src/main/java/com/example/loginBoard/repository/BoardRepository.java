package com.example.loginBoard.repository;

import com.example.loginBoard.model.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
