package com.example.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.tunehub.entities.Songs;
import com.example.tunehub.services.SongsService;

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
		return "displaysongs";
		
		
	}
	
	
}