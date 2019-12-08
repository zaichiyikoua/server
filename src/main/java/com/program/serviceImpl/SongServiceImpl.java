package com.program.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.program.DAO.SongDAO;
import com.program.pojo.Song;
import com.program.service.SongService;

@Service
public class SongServiceImpl implements SongService{

	@Autowired
	SongDAO songDAO;

	@Override
	public int insertSong(Song song) {
		return songDAO.insertSong(song);
	}

	@Override
	public int updateSong(Song song) {
		return songDAO.updateSong(song);
	}
	
	
}
