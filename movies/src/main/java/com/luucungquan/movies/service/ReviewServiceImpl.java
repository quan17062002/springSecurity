package com.luucungquan.movies.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luucungquan.movies.models.Movies;
import com.luucungquan.movies.models.Review;
import com.luucungquan.movies.repository.MovieReposiory;
import com.luucungquan.movies.repository.ReviewRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	MovieReposiory movieReposiory;
	@Autowired
	ReviewRepository reviewRepository;

	@Override
	public Review createReview(String imdbId, String review) {
		Optional<Movies> movies = movieReposiory.findMoviesByImdbId(imdbId);
	     if(movies.isPresent()) {
	    	 throw new IllegalArgumentException("movies not found for imbl"+imdbId);
	     }
	     Movies movies2Movies = movies.get();
	     Review review2 = new Review(review);
	     List<Review>liReviews = movies2Movies.getReview();
	     liReviews.add(review2);
	    movies2Movies.setReview(liReviews);
	    movieReposiory.save(movies2Movies);
	    reviewRepository.save(review2);
	     return review2 ;
	}
}