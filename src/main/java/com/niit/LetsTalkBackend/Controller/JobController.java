package com.niit.LetsTalkBackend.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.niit.LetsTalkBackend.Model.Error;
import com.niit.LetsTalkBackend.Dao.JobDao;
import com.niit.LetsTalkBackend.Model.Job;
import com.niit.LetsTalkBackend.Model.User;

@RestController
public class JobController {
	@Autowired
	private JobDao jobDao;

	public JobDao getJobDao() {
		return jobDao;
	}

	public void setJobDao(JobDao jobDao) {
		this.jobDao = jobDao;
	}

	@RequestMapping(value = "/postjob", method = RequestMethod.POST)
	public ResponseEntity<?> postJob(@RequestBody Job job, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			Error error = new Error(1, "Unauthorized user login with valid username and password");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		} else {

			jobDao.postJob(job);
			return new ResponseEntity<Void>(HttpStatus.OK);
		

		}
		
	}
	 @RequestMapping(value="/getAllJobs",method=RequestMethod.GET)
	    public ResponseEntity<?> getAllJobs(HttpSession session){
	    	User user=(User)session.getAttribute("user");
	    	if(user==null){
	    		System.out.println("USER is null");
	    		Error error=new Error(1,"Unauthorized user.. login using valid credentials");
				return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);//401
	    	}
	    	System.out.println("USER OBJECT " + user.getRole());
	    	List<Job> jobs=jobDao.getAllJobs();
	    	return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
	    	//response 
	    }
	 @RequestMapping(value="/getJobDetail/{jobId}",method=RequestMethod.GET)
	    public ResponseEntity<?> getJobDetail(@PathVariable(value="jobId")int jobId,
	    		HttpSession session){
	    	User user=(User)session.getAttribute("user");
	    	if(user==null){
	    		System.out.println("USER is null");
	    		Error error=new Error(1,"Unauthorized user.. login using valid credentials");
				return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);//401
	    	}
//	    	logger.debug("JobId "+ jobId);
	    	Job jobDetail=jobDao.getJobDetail(jobId);
	    	return new ResponseEntity<Job>(jobDetail,HttpStatus.OK);
	    }
}
