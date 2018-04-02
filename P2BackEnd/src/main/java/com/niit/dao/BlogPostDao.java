package com.niit.dao;

import java.util.List;

import com.niit.model.BlogComment;
import com.niit.model.BlogPost;

public interface BlogPostDao {
	void addBlogPost(BlogPost blogPost);
	List<BlogPost> listofBlogs(int approved);

	//blogPostDao.listofBlogs(1)  ->select * from blogpost wher approved=1
	//List of blogs which are approved
	
	BlogPost getBlog(int id);
	//blogPostDao.listOfBlogs(0)  -> select * from blogPost wher approved=0
	//list of blogs which are waiting for approval
	void approve(BlogPost blog);
	void reject(BlogPost blog,String rejectionReason);
	
	///for BlogComment 
	void addBlogComment(BlogComment blogComment);
	List<BlogComment>getAllBlogComment(int blogPostId);
}
	
	 