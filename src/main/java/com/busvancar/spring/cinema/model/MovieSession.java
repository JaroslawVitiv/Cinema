package com.busvancar.spring.cinema.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder

@Table(name = "movie_sessions")
@NoArgsConstructor
@AllArgsConstructor

public class MovieSession {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "movie_id")
	private Integer movieId;
	
	@Column(name = "price")
	private Integer price;

	@Column(name = "available")
	private Integer availableSeats;

	@Column(name = "session_time")
	private Timestamp dateTime;
	
/*
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", insertable=false, updatable=false)
    private Movie movie;

	@Override
	public boolean equals(Object obj) {
	   if (this == obj) return true;
	   if (!(obj instanceof MovieSession)) return false;
	return id != null && id.equals(((MovieSession) obj).getId());
	}
	 
	@Override
	public int hashCode() {
	    return getClass().hashCode();
	}
	*/
}
