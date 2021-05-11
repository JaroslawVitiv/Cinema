package com.busvancar.spring.cinema.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder

@Table(name="movie")
@NoArgsConstructor
@AllArgsConstructor

public class Movie{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description_uk")
	private String descriptionUa;
	
	@Column(name = "description_en")
	private String descriptionEn;
	
	@Column(name = "duration")
	private Integer duration;

	@Column(name = "genre")
	private Integer genreId;
	
	@Column(name = "poster")
	private String poster;
	
	@Column(name = "default_price")
	private Double price;
	
	@Transient
	private String genre;
	@Transient
	private String genreUa;
	
	/*
	 @OneToMany(
			 fetch = FetchType.LAZY,
			 mappedBy = "movie", 
			 cascade = CascadeType.ALL,  
			 orphanRemoval = true
	 )	
	 private List<MovieSession> sessions;
	 
	public void addMovieSession(MovieSession ms) {
		sessions.add(ms);
        ms.setMovie(this);
    }
 
    public void removeComment(MovieSession ms) {
    	sessions.remove(ms);
        ms.setMovie(null);
    }
    */

}
