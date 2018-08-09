package com.example.test.service;

import java.util.List;
import com.example.test.vo.TwitterVO;

public interface FolderMapper{
	
	public List<TwitterVO> selectFolderList() throws Exception;

	public List<TwitterVO> selectUserList() throws Exception;

}
