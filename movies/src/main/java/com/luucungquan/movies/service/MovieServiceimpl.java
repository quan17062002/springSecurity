package com.luucungquan.movies.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luucungquan.movies.models.Movies;
import com.luucungquan.movies.repository.MovieReposiory;

@Service
public class MovieServiceimpl implements MovieService {

	@Autowired
	MovieReposiory movieReposiory;

	@Override
	public List<Movies> findByAll() {
		return movieReposiory.findAll();
	}
	//======================

	@Override
	public void saveUsersFromJson() {
		ObjectMapper objectMapper = new ObjectMapper();
		TypeReference<List<Movies>> typeReference = new TypeReference<List<Movies>>() {
		};
		InputStream inputStream = TypeReference.class.getResourceAsStream("/movies.json");
		try {
			List<Movies> movies = objectMapper.readValue(inputStream, typeReference);
			movieReposiory.saveAll(movies);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	//========================

	@Override
	public Optional<Movies> getSingleMovie(int id) {
	 return movieReposiory.findById(id);
		
	}

	@Override
	public Optional<Movies> findMoviesByImdbId(String imdbId) {
		return movieReposiory.findMoviesByImdbId(imdbId);
	}
	

}
