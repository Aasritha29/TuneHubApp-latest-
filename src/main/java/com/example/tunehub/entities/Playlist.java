package com.example.tunehub.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToMany;

@Entity
public class Playlist {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	String name;
	@ManyToMany
	@JoinTable(
	    name = "playlist_song",
	    joinColumns = @JoinColumn(name = "playlist_id", referencedColumnName = "id"),
	    inverseJoinColumns = @JoinColumn(name = "song_id", referencedColumnName = "id")
	)
	private List<Songs> song;

	public Playlist() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Playlist(int id, String name, List<Songs> song) {
		super();
		this.id = id;
		this.name = name;
		this.song = song;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Songs> getSong() {
		return song;
	}
	public void setSong(List<Songs> song) {
		this.song = song;
	}

	@Override
	public String toString() {
		return "Playlist [id=" + id + ", name=" + name + ", song=" + song + "]";
	}
	
	
}