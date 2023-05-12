package com.luucungquan.movies.service;

import java.util.List;
import java.util.Optional;

import com.luucungquan.movies.models.Movies;

public interface MovieService {
	List<Movies> findByAll();

	void saveUsersFromJson();

	Optional<Movies> getSingleMovie(int id);

	Optional<Movies> findMoviesByImdbId(String imdbId);

}
