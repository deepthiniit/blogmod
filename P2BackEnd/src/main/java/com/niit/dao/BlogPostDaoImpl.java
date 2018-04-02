package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.BlogComment;
import com.niit.model.BlogPost;
import com.niit.model.Notification;

@Repository
@Transactional


public class BlogPostDaoImpl  implements BlogPostDao
{@Autowired
	private SessionFactory sessionFactory;

	public void addBlogPost(BlogPost blogPost) {
		 Session session=sessionFactory.getCurrentSession();
		 session.save(blogPost);//insert into blogPost_s180250 values(?...)
	}
        //List of blogs waiting for approval if approved=0
        //list of blogd which are approved if approved=1
	
	public List<BlogPost> listofBlogs(int approved) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from BlogPost wher approved="+approved);
		 List<BlogPost> blogs=query.list();
		return blogs;
	}

	public BlogPost getBlog(int id) {
		Session session=sessionFactory.getCurrentSession();
		BlogPost blogPost=(BlogPost)session.get(BlogPost.class, id);
		return blogPost;
	}

	public void approve(BlogPost blog) {
		Session session=sessionFactory.getCurrentSession();
		blog.setApproved(true);
		session.update(blog);//update blogpost set approved=1 wher id=?
		Notification notification=new Notification();
		notification.setBlogTittle(blog.getBlogTitle());
		notification.setApprovalStatus("Approved");
		notification.setEmail(blog.getPostedBy().getEmail());
		session.save(notification);   //insert into notification value(...)
		}

	public void reject(BlogPost blog,String rejectionReason) {
		Session session=sessionFactory.getCurrentSession();
		Notification notification=new Notification();
		notification.setBlogTittle(blog.getBlogTitle());
		notification.setApprovalStatus("Rejected");
		notification.setEmail(blog.getPostedBy().getEmail());
		notification.setRejectionReason(rejectionReason);
		session.save(notification);   //insert into notification...
		session.delete(blog);   //delete from blogpost wher id=?
	}

	public void addBlogComment(BlogComment blogComment) {
		 Session session=sessionFactory.getCurrentSession();
		 session.save(blogComment);
		 //insert into blogcomment values(?....
		
	}

	public List<BlogComment>getAllBlogComment(int blogPostId) {
		 Session session=sessionFactory.getCurrentSession();
		 //select * from blogcomment wher id of the blogpost
		 Query query=session.createQuery("from BlogComment where blogPost.id=?");
		 query.setInteger(0, blogPostId);
		 List<BlogComment>blogComments=query.list();
		 return blogComments;
		 
		 
	}

}
