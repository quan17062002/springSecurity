package com.luucungquan.movies.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luucungquan.movies.models.Movies;
import com.luucungquan.movies.service.MovieService;
import com.luucungquan.movies.service.ReviewService;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
	@Autowired
	MovieService movieService;

	@Autowired
	ReviewService reviewService;

	@GetMapping
	public ResponseEntity<List<Movies>> allMovies() {
		return new ResponseEntity<>(movieService.findByAll(), HttpStatus.OK);
	};

	@GetMapping("/addmovie")
	public void addMovies() {
		movieService.saveUsersFromJson();
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<?> getSingleMovie(@PathVariable int id) {
		return ResponseEntity.ok().body(movieService.getSingleMovie(id));
	}

	@GetMapping("/{imdbId}")
	public ResponseEntity<Optional<Movies>> getResponseEntity(@PathVariable String imdbId) {
		return new ResponseEntity<>(movieService.findMoviesByImdbId(imdbId), HttpStatus.OK);
	}

	
}
