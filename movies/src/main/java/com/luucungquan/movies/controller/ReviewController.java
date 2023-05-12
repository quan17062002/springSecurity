package com.luucungquan.movies.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luucungquan.movies.service.ReviewService;
@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {
@Autowired
	ReviewService reviewService;
	@PostMapping 
	public ResponseEntity<?> create(@RequestBody Map<String, String> payload){
	    return new ResponseEntity<>(reviewService.createReview(payload.get("imdbId"), payload.get("review")), HttpStatus.CREATED);
	}

	 
}
