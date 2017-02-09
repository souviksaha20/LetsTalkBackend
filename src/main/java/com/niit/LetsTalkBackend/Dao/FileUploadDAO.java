package com.niit.LetsTalkBackend.Dao;

import com.niit.LetsTalkBackend.Model.UploadFile;

public interface FileUploadDAO {
	void save(UploadFile uploadFile);
	UploadFile getFile(String username);
}
