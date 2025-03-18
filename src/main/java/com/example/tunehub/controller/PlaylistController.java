package com.example.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.tunehub.entities.Playlist;
import com.example.tunehub.entities.Songs;
import com.example.tunehub.services.PlaylistInterface;
import com.example.tunehub.services.SongsService;

@Controller
public class PlaylistController {

	@Autowired
	PlaylistInterface playserv;
	
	@Autowired
	SongsService sserv;
	
	@GetMapping("/createplaylist")
	
	public String createPlaylist(Model model)
	{
	List<Songs> songslist=sserv.fetchAllSongs();
	model.addAttribute("songslist", songslist);
	return "createplaylist";
	}
	
	@PostMapping("/addplaylist")
	public String addPlaylist(@ModelAttribute Playlist play)
	{
		playserv.addPlaylist(play);
		List<Songs> songslist=play.getSong();
		
		for(Songs song:songslist)
		{
			song.getPlaylist().add(play);
      		sserv.updateSongs(song);
		}
		return "playlistsuccess";
	}
	
	@GetMapping("/viewPlaylists")
	public String viewPlaylists(Model model)
	{
	   List<Playlist> plist= playserv.fetchPlaylists();
	   
	   model.addAttribute("plist", plist);
	   return "viewPlaylists";
	}
}