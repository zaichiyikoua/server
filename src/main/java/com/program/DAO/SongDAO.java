package com.program.DAO;

import org.springframework.stereotype.Repository;

import com.program.pojo.Song;

@Repository
public interface SongDAO {
	// 新增歌曲
	public int insertSong(Song song);

	// 修改歌曲
	public int updateSong(Song song);
	// 删除歌曲

}
