package com.niit.controllers;

import java.util.Date;
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

import com.niit.dao.BlogPostDao;
import com.niit.dao.UserDao;
import com.niit.model.BlogComment;
import com.niit.model.BlogPost;
import com.niit.model.ErrorClass;
import com.niit.model.User;

@RestController
public class BlogPostController {
@Autowired
private BlogPostDao blogPostDao;
@Autowired
private UserDao userDao;

public ResponseEntity<?>addBlogPost(@RequestBody BlogPost blogPost,HttpSession session){
	String email=(String)session.getAttribute("loginId");
    if(email==null){   //not logged in
    	 ErrorClass error=new ErrorClass(5,"Unauthorised access...");
    	 return new ResponseEntity<ErrorClass>(error,HttpStatus.UNAUTHORIZED);
     }
    blogPost.setPostedOn(new Date());
    User postedBy=userDao.getUser(email);
     blogPost.setPostedBy(postedBy);
     try
     {
       blogPostDao.addBlogPost(blogPost);
       return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
      
     }catch(Exception e){
    	 ErrorClass error=new ErrorClass(6,"Unable to post blog.."+ e.getMessage());
    	 return new ResponseEntity<ErrorClass>(error,HttpStatus.INTERNAL_SERVER_ERROR);
     }
}
@RequestMapping(value="/getblogs/{approved}",method=RequestMethod.GET)
//$http.get("http://localhost:8080/P2MiddleEnd/getblogs/0")  ->Authentication and Authorization->
///getblos/1->Authentication
public ResponseEntity<?>getAllBlogs(@PathVariable int approved,HttpSession session){
	String email=(String)session.getAttribute("loginId");
	if(email==null){    //not  
		ErrorClass error=new ErrorClass(5,"Unauthorised access...");
		return new ResponseEntity<ErrorClass>(error,HttpStatus.UNAUTHORIZED);
	}
	if(approved==0){   //List of blogs waiting for approval,
		User user=userDao.getUser(email);
		if(!user.getRole().equals("ADMIN")){
			ErrorClass error=new ErrorClass(7,"Access denied");
			return new ResponseEntity<ErrorClass>(error,HttpStatus.UNAUTHORIZED);
		}
	}
	List<BlogPost> blogs=blogPostDao.listofBlogs(approved);//0 or 1
	return new ResponseEntity<List<BlogPost>>(blogs,HttpStatus.OK);
}
@RequestMapping(value="/getblog/(id)",method=RequestMethod.GET)
public ResponseEntity<?>getBlog(@PathVariable int id,HttpSession session) {
	 
	String email=(String)session.getAttribute("loginid");
	if(email==null){  //not loggedin
		ErrorClass error=new ErrorClass(5,"Unauthorised access...");
		return new ResponseEntity<ErrorClass>(error,HttpStatus.UNAUTHORIZED);
		
	}
	BlogPost blogPost=blogPostDao.getBlog(id);
	return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);

}
@RequestMapping(value="/approve",method=RequestMethod.PUT)
public ResponseEntity<?>approve(@RequestBody BlogPost blog,HttpSession session){
	String email=(String)session.getAttribute("loginId");
	if(email==null){  //not loggedin
        ErrorClass error=new ErrorClass(5,"Unauthorised access...");
        return new ResponseEntity<ErrorClass>(error,HttpStatus.UNAUTHORIZED);
	}
    User user=userDao.getUser(email);
    if(user.getRole().equals("ADMIN")){
    	ErrorClass error=new ErrorClass(7,"Access denied");
    	return new ResponseEntity<ErrorClass>(error,HttpStatus.UNAUTHORIZED);
    }
    
    blogPostDao.approve(blog);  //update blogpost set approved=1 wher id=?
    return new ResponseEntity<Void>(HttpStatus.OK);
    
}
@RequestMapping(value="/reject/{rejectionReason}",method=RequestMethod.PUT)
public ResponseEntity<?>reject(@RequestBody BlogPost blog,@PathVariable String rejectionReason,HttpSession session){
   String email=(String)session.getAttribute("loginId");
   if(email==null){   //not loggedin
	   ErrorClass error=new ErrorClass(5,"Unauthorised access...");
	   return new ResponseEntity<ErrorClass>(error,HttpStatus.UNAUTHORIZED);
   }
   User user=userDao.getUser(email);
   if(!user.getRole().equals("ADMIN")){
	   ErrorClass error=new ErrorClass(7,"AccessDEnied");
	   return new ResponseEntity<ErrorClass>(error,HttpStatus.UNAUTHORIZED);
   }
   blogPostDao.reject(blog, rejectionReason);   //update blogpost set approved=1 wher id=?
   return new ResponseEntity<Void>(HttpStatus.OK);
}


    //for BlogComment

   @RequestMapping(value="/addcomment",method=RequestMethod.POST)                                                 //@RequestMapping(value="/addcomment/{id}/{commentTxt}",method=RequestMethod.POST)
      public ResponseEntity<?>addBlogComment(@RequestBody BlogComment blogComment,HttpSession session){          // public ResponseEntity<?>addBlogCOmment(@PathVariable int id,
		                                                                                                        //  @PathVariable String commentTxt,HttpSession session){
	 String email=(String)session.getAttribute("loginId");
	   if(email==null){   //not loggedin
		   ErrorClass error=new ErrorClass(5,"Unauthorised access...");
		   return new ResponseEntity<ErrorClass>(error,HttpStatus.UNAUTHORIZED);
	   }
	                                                        //BlogComment blogComment=new BlogComment();same as below
	                                                        //BlogPost blogPost= blogPostDao.getBlog(id);
	   User commentedBy= userDao.getUser(email);
	   
	   //blogpost,commentrdBy,commentedOn,commentTxt
	  
	   blogComment.setCommentedon(new Date());
	   blogComment.setCommentedBy(commentedBy);
	                                                     // blogComment.setCommentTxt(commentTxt);       
	                                                    //blogComment.setBlogPost(blogPost);these two statement is not required because we costructed in frontend itself
	                                                    //that is line 1 and 2     
	   try{
	   blogPostDao.addBlogComment(blogComment);
	   }
		   catch(Exception e){
			   ErrorClass error=new ErrorClass(6,"Unable to post the comment"  +e.getMessage());
			   return new ResponseEntity<ErrorClass>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		
	   }
	   return new ResponseEntity<BlogComment>(blogComment,HttpStatus.OK);
} 
   
@RequestMapping(value="/blogcomments/{blogPostId}",method=RequestMethod.GET)   
public ResponseEntity<?>getAllComments(@PathVariable int blogPostId,HttpSession session){
	String email=(String)session.getAttribute("loginId");
	if(email==null){            //not logged in
		 ErrorClass error=new ErrorClass(5,"Unauthorised access...");
		   return new ResponseEntity<ErrorClass>(error,HttpStatus.UNAUTHORIZED);
	   }
	List<BlogComment> blogComments=blogPostDao.getAllBlogComment(blogPostId);
	return new ResponseEntity<List<BlogComment>>(blogComments,HttpStatus.OK);
	}
}









