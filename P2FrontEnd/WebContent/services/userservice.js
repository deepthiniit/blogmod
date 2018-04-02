/**
 * UserService  -  to make service side calls
 */

app.factory('UserService',function($http){

	    var userService={}
	    
	    userService.registerUser=function(user){   //get the user from frontend controller
	    	//formate of user-JSON
	    	/*console.log('in userservice')
	    	console.log(user)*/
	    	console.log('entering userservice registerUser function in frontend '+JSON.stringify(user));
	   return $http.post("http://localhost:8086/P2MiddleEnd/registerUser",user);
	    	
	    }
	    userService.login=function(user){
	    	console.log('userService-> login')
	    	console.log(user)
	    	return $htp.post("http://localhost:8096/P2MiddleEnd/login",user)
	    	
	    }
	    userService.logout=function(){
	    	return $http.put("http://localhost:8096/P2MiddleEnd/logout",user)
	    	
	    }
	    userService.getUser=function(){
	    	return $http.get("http://localhost:8086/P2MiddleEnd/getuser",user)
	    	
    	
    }
	    userService.updateUser=function(user){
	    	return $http.put("http://localhost:8096/P2MiddleEnd/updateuser",user)
	    	
	    }
	    return userService;
})