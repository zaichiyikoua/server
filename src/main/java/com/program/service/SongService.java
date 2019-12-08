package com.program.service;

import com.program.pojo.Song;

public interface SongService {
	//新增歌曲
	public int insertSong (Song song);
	//修改歌曲
	public int updateSong(Song song);
}
