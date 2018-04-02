/**
 * BlogService
 */

app.factory('BlogService',function($http){
	var blogService={}
	
	blogService.addBlog=function(blog){
		return $http.post("http://localhost:8096/P2MiddleEnd/addblogpost",blog)
	}
	blogService.getBlogsWaitingForApproval=function(){
		return $http.get("http://localhost:8096/P2MiddleEnd/getblogs/"+0)
	}
	blogService.getBlogsApproved=function(){
		return $http.get("http://localhost:8096/P2MiddleEnd/getblogs/"+1)
	}
	blogService.getBlog=function(id){
		return $http.get("http://localhost:8096/P2MiddleEnd/getblogs/"+id)
	}
	blogService.approve=function(blog){
		//blog.approved=0
		return $http.put("http://localhost:8096/P2MiddleEnd/approve",blog)
	    //blog approved=1
	}
   blogService.reject=function(blog,rejectionReason){
	   //blog.approved=0
    	return $http.put("http://localhost:8096/P2MiddleEnd/reject/"+rejectionReason,blog)
    	//blogrecord will be deleted
   }
   blogService.updateLikes=function(id){
	   return $http.put("http://localhost:8096/P2MiddleEnd/updatelikes/"+id);
   }
   blogService.addComment=function(blogComment){          //(id,commentTxt){
	   return $http.post("http://localhost:8096/P2MiddleEnd/addcomment",blogComment)               //"+id+""/+commentTxt) ;
   }
   blogService.getBlogComments=function(id){
	   return$http.get("http://localhost:8096/P2MiddleEnd/blogcomments/"+id)
   }
	return blogService;
	
})