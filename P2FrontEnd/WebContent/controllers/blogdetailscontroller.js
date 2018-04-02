/**
 * 
 */

app.controller('BlogDetailsCtrl',function($scope,$location,BlogService,$rootScope,$routeparam,$sce,$routeParam){
      var id=$routeParams.id;               // this is an id that we get in the url path i.e. lastpart of the url
       $scope.rejectionTxt=false;
      BlogService.getBlog(id).then(
    		  function(response){
    			  console.log(response.data)
    			  $scope.blog=response.data
    			  $scope.content=$sce.trustAsHtml($scope.blog.blogContent)
    		  },function(response){
    			  $rootScope.error=response.data
    			  if(response.status==401)
    				  $location.path('/login')
    				  
    		  })
    	// select * from blogpostlikes_id=? and user_email=....!	  
    		  
    		  BlogService.hasUserLikedBlog(id).then(
    				  function(response){
    		//response.data will be eithr null or an object of type BlogPostLikes
    					  if(response.data=="")//blog is not yet liked by the user
    						  $scope.isLiked=false//this variable isLiked is used to determine the color of glyphicon
    						  else
    							  $scope.isLiked=true
    							  
    				  },function(response){
    					  $rootScope.error=response.data
    					  if(response.status==401)
    						  $location.path('/logi')
    				  		  })
    		  
      $scope.approve=function(blog){
    	  //blog.approved=0
    	  BlogService.approve(blog).then(function(response){
    		  $location.path('/blogsnotapproved')
    	  },function(response){
    		  $rootScope.error=response.error
    		  if(response.error==401)
    			  $location.path('/login')
    	  })
      }
      
        $scope.reject=function(blog){
        	//blog.approved=0
        	  BlogService.reject(blog,$scope.rejectionReason).then(function(response){
        		  $location.path('/blogsnotapproved')
        	  },function(response){
        		  $rootScope.error=response.error
        		  if(response.error==401)
        			  $location.path('/login')
        	  });
    	  }
        $scope.showRejectionTxt=function(){
        	$scope.rejectionTxt=true;
        }
        
        $scope.updateLikes=function(id){
        	BlogService.updateLikes(id).then(
        			function(response){
        				$scope.blog=response.data;  //update blogpost likes
        				$scope.isLike|$scope.isLiked
        			},function(response){
        				$rootScope.error=response.data
        				if(response.status==401)
        					$location.path('/login')
        				
        			});
        }
        
        $scope.addComment=function(blog,commentTxt){                  //(id,commentTxt)
        	$scope.blogComment={}                                    // {}only brackets means object...{""}means string
        	                                                        //1. below line is same as blogComment.setBogPost(blogPost) in middleware
        	$scope.blogComment.blogPost=blog;
        	                                                       //2.blogComment.setCommentTxt(commenTxt) in middleware
        	$scope.blogComment.commentTxt=commentTxt;
        	BlogService.addComment($scope.blogComment).then(       //(id,commentTxt)
        			function(response){  
        		                                 	  // $scope.blogComment=response.data	
        			   $scope.commentTxt=''  //clearing data after posting it
                          getBlogComments(id)
        			},function(response){
        				$rootscope.error=resonse.data
        				if(response.status==401)
        					$location.path('/login')
        			else{
        				$scope.exceptionMessage=response.data      
        				}
        			});
        }
      function getBlogComments(id){                              //id is a variable from $routeParam see up	
    	  BlogService.getBlogComments(id).then(function(response){
    		$scope.comments=response.data         //select * from blogcomment wher blogpost_id=761
    	},function(response){
    		$rootScope.error=response.data
    		if(response.status==401)
    			$location.path('/login')
    	});  
      }
      getBlogComments(id)
})