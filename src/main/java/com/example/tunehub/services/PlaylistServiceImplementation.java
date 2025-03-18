package com.example.tunehub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tunehub.entities.Playlist;
import com.example.tunehub.repositories.PlaylistRepository;

@Service
public class PlaylistServiceImplementation  implements PlaylistInterface{

	 @Autowired
	 PlaylistRepository playrepo;

	@Override
	public List<Playlist> fetchPlaylists() {
		return playrepo.findAll();
		
	}

	@Override
	public void addPlaylist(Playlist play) {
		// TODO Auto-generated method stub
		playrepo.save(play);
	}
}
