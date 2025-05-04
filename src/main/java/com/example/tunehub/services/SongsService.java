package com.example.tunehub.services;

import java.util.List;

import com.example.tunehub.entities.Songs;

public interface SongsService {

	public String addSongs(Songs s);
	public boolean nameExists(String name);
	public List<Songs> fetchAllSongs();
	public void updateSongs(Songs song);
	public void deleteSongById(int id);
	public Songs getSongById(int id);

}
