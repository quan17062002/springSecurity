package com.luucungquan.movies.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luucungquan.movies.models.Movies;

@Repository
public interface MovieReposiory extends JpaRepository<Movies, Integer> {
	Optional<Movies> findMoviesByImdbId(String imdbId);
	
}
