package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Job;


@Repository
@Transactional

public class JobDaoImpl implements JobDao {
	@Autowired
 private SessionFactory sessionFactory;
	public void addJob(Job job){
		Session session=sessionFactory.getCurrentSession();
		session.save(job);
		
		
	
	}
	public List<Job> getAllJObs() {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("form Job");  //select * job_s180250
		 
		return query.list();
	}
	public Job getJob(int id) {
		Session session=sessionFactory.getCurrentSession();
		Job job=(Job)session.get(Job.class,id);  //select * from job_s180250 wher id=?		// TODO Auto-generated method stub
		return job;
	}
	public void insertUser(Job job) {
		// TODO Auto-generated method stub
		
	}

}
