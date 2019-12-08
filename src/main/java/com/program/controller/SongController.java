package com.program.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.program.pojo.Song;
import com.program.serviceImpl.SongServiceImpl;

@RestController
@RequestMapping("/program/song")
public class SongController {
	@Autowired
	SongServiceImpl service;

	// 新增歌曲
	@PostMapping("/insertSong")
	public Map<String, Object> insertSong(Song song) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (song == null) {
			map.put("code", 400);
			map.put("info", "请输入要新增的歌曲");
			return map;
		}
		int result = service.insertSong(song);
		if (result > 0) {
			map.put("info", "添加成功");
			map.put("code", 200);
			return map;
		}
		map.put("code", 400);
		map.put("info", "添加失败");
		return map;
	}
	
	//
}
