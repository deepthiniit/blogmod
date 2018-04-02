/**
 * Angular JS module and config SPA
 */
var app=angular.module('myapp',['ngRoute','ngCookies'])
app.config(function($routeProvider)
{
	$routeProvider
	.when('/',{	  
		 templateUrl:'views/home.html',
		    controller:'notificationCtrl'
})
	.when('/register',{	  
	    templateUrl:'views/registrationform.html',//all variables and function add to $scope object can be accessed in the views
	    controller:'UserController'//$scope
})
.when('/login',  {
	templateUrl:'views/login.html',
	controller:'UserController'
})
.when("/edituserprofile",{
	templateUrl:'views/edituserprofile.html',
	controller:'UserController'
})

.when("/addjob",{
	templateUrl:"views/jobforms.html",
     controller:"JobCtrl"
})
.when('/alljobs',{
     templateUrl:"views/jobslist.html",
     controller:"JobCtrl"    	 
})
.when('/getjob/:id',{
	templateUrl:'views/jobdetail.html',
    controller:'JobCtrl'
})
.when('/addblog',{
	templateUrl:'views/blogform.html',
    controller:'BlogCtrl'
})
.when('blogsnotapproved',{
	templateUrl:'views/blogsnotapproved.html',
	controller:'BlogCtrl'//list of blogs
})
.when('blogsapproved',{
	templateUrl:'views/blogsapproved.html',
	controller:'BlogCtrl'  //list of blogs
})
.when('getblog/:id',{
	templateUrl:'views/blogdetails.html',
	controller:'BlogDetailsCtrl' //single blog post object-queries getBlog() update blog,add comment
})
.when('/getblognotapproved/:id',{
	templateUrl:'views/blogapprovalform.html',
	controller:'BlogDetailsCtrl'   //$scope.blogPost=select*from blogpost wher id=?
})
.when('/getnotification/:id',{
	templateUrl:'views/notificationdetails.html',
    controller:'notificationCtrl'
    	})
.when('/uploadprofilepic',{
		templateUrl:'views/uploadprofilepic.html'
	})
	.when('/suggestedusers',{
		templateUrl:'views/suggestedusers.html',
			controller:'FriendCtrl'
	})
	.when('/pendingrequests',{
		templateUrl:'views/pendingrequests.html',
			controller:'FriendCtrl'
	})
	
.when('/home',{
    templateUrl:'views/home.html',
    controller:'notificationCtrl'
})


.otherwise({ 
	templateUrl:"views/home.html"
		})
})
		
	app.run(function($location,$rootScope,$cookieStore,UserService){
		if($rootScope.loggedInUser==undefined)
			$rootScope.loggedInUser=$cookieStore.get("currentuser") 
			
			$rootScope.logout=function(){
			console.log('entering logout')
			UserService.logout().then(
			function(response){
				delete $rootScope.loggedInUser;
				$cookieStore.remove('currentuser')
				$rootScope.message= "Successfully loggedout.."
			    $location.path("/login");
				},function(response){
					$rootScope.error=response.data
					  if(response.status==401)
						  $location.path('/login')
					
				})
		}
	
	})
	
	
