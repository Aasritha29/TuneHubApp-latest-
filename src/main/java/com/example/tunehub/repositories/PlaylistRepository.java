package com.example.tunehub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tunehub.entities.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist,Integer> {

}
