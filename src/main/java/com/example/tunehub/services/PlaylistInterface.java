package com.example.tunehub.services;
import java.util.List;
import com.example.tunehub.entities.Playlist;
public interface PlaylistInterface {

	public List<Playlist> fetchPlaylists();

	public void addPlaylist(Playlist play);


	
}
