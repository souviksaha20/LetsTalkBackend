package com.niit.LetsTalkBackend.Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.LetsTalkBackend.Model.Job;

@Repository
public class JobDaoImpl implements JobDao {
	@Autowired
	private SessionFactory sessionFactory;
	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void postJob(Job job) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.save(job);
		session.flush();
		session.close();

	}

	@Override
	public List<Job> getAllJobs() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Job");
		List<Job> jobs = query.list();
		session.close();

		return jobs;
	}

	@Override
	public Job getJobDetail(int jobId) {
		Session session=sessionFactory.openSession();
		Job job=(Job)session.get(Job.class, jobId);
		session.close();
		return job;
	
	}

}
