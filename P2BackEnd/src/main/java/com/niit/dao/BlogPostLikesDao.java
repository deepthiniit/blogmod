package com.niit.dao;

import com.niit.model.BlogPost;
import com.niit.model.BlogPostLikes;

public interface BlogPostLikesDao {
	
	BlogPostLikes hasUserLikedBlog(int blogId,String email);
     BlogPost updateLikes(int id,String email);
}
