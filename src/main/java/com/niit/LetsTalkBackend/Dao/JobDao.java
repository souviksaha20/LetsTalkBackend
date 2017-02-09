package com.niit.LetsTalkBackend.Dao;

import java.util.List;

import com.niit.LetsTalkBackend.Model.Job;

public interface JobDao {
	void postJob(Job job);
	List<Job> getAllJobs();
	Job getJobDetail(int jobId);
	

}
