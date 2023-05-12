package com.luucungquan.movies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luucungquan.movies.models.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

}
