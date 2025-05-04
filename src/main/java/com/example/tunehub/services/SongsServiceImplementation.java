package com.example.tunehub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tunehub.entities.Songs;
import com.example.tunehub.repositories.SongsRepository;

@Service
public class SongsServiceImplementation implements SongsService {

	@Autowired
	SongsRepository songrepo;

	@Override
	public String addSongs(Songs s) {
	      songrepo.save(s);
	      return "song is added";
		
	}

	@Override
	public boolean nameExists(String name) {
		if(songrepo.findByName(name)==null)
		{
			return false;
		}
		return true;
	}

	@Override
	public List<Songs> fetchAllSongs() {
		List<Songs> ls=songrepo.findAll();
		return ls;
	}

	@Override
	public void updateSongs(Songs song) {
		// TODO Auto-generated method stub
		songrepo.save(song);
	}

	@Override
	public void deleteSongById(int id) {
	    //TODO Auto-generated method stub
		songrepo.deleteById(id);
	}

	@Override
	public Songs getSongById(int id) {
		// TODO Auto-generated method stub
		 return songrepo.findById(id).orElse(null);
	}
	


	
}
