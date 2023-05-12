package com.luucungquan.movies.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movies {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String imdbId;
	private String title;
	private String releaseDate;// ngày phát hành
	private String trailerLink;
	private String poster;
	private List<String> genres;// thể loại
	@Column(length = 3000) 
	private List<String> backdrops;// phông nền
	@OneToMany(fetch = FetchType.LAZY)
	private List<Review> review;

}
