package com.example.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.tunehub.entities.Playlist;
import com.example.tunehub.entities.Songs;
import com.example.tunehub.services.SongsService;

import jakarta.transaction.Transactional;

@Controller
public class SongsController {

	@Autowired
	SongsService sserv;
	
	@PostMapping("/addSong")
	public String addSongs(@ModelAttribute Songs s)
	{
		
		
		boolean songstatus=sserv.nameExists(s.getName());
		
		if(songstatus==false)
		{
			sserv.addSongs(s);
			return "songsuccess";
		}
		else {
			return "songfail";
		}
	}
	
	@GetMapping("/map-view")
	public String fetchAllSongs(Model model)
	{
		List<Songs> ls=sserv.fetchAllSongs();
		model.addAttribute("ls", ls);
		return "displaysongs";
	}
	
	@GetMapping("/viewsongs")
	public String viewCustomerSongs(Model model)
	{
		
		List<Songs> ls=sserv.fetchAllSongs();
		model.addAttribute("ls",ls);
		return "customerSongs";
		
		
	}
	
	@GetMapping("/edit-song/{id}")
	public String editSongForm(@PathVariable int id, Model model) {
	    Songs song = sserv.getSongById(id);
	    model.addAttribute("song", song);
	    return "addSongForm"; // reuse your add-song form with pre-filled data
	}

	@PostMapping("/update-song")
	public String updateSong(@ModelAttribute Songs song) {
	    sserv.updateSongs(song);  // Already calls save() internally
	    return "redirect:/map-view";  // Go to displaysongs.html
	}

	
//    @PostMapping("/delete-song/{id}")
//    @Transactional
//    public String deleteSong(@PathVariable int id) {
//        sserv.deleteSongById(id);
//
//        return "displaysongs"; 
//    }
	
	@Transactional
	@PostMapping("/delete-song/{id}")
	public String deleteSong(@PathVariable int id) {
	    Songs song = sserv.getSongById(id);
	    
	    // Remove song from all playlists
	    for (Playlist playlist : song.getPlaylist()) {
	        playlist.getSong().remove(song); // Remove song from each playlist
	    }

	    sserv.deleteSongById(id); // Now safe to delete
	    return "redirect:/map-view";
	}


}