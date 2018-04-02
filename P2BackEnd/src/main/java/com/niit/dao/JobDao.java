package com.niit.dao;

import java.util.List;

import com.niit.model.Job;

public interface JobDao {
  void addJob(Job job);//insert into job_s180250 values(....)
  List<Job>getAllJObs();//select *from job_s180250
  Job getJob(int id);//select * from job_s180250 wher id=?
void insertUser(Job job);
}
