/**
 *
 * jobservice
 * 
 */

app.factory('JobService',function($http){
	var jobservice={}

	
	jobservice.addjob=function(job){
	return $http.post("http://localhost:8096/P2MiddleEnd/addjob",job)
}
	
	jobService.getAllJobs=function(){
		return $http.get("http://localhost:8096/P2MiddleEnd/alljobs");
	}
	jobService.getJob=function(id){
		return $http.get("http://localhost:8096/P2MiddleEnd/getjob/"+id);
	}
return jobService;
})