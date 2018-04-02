/**
 * 
 */
app.controller('FriendCtrl',function($scope,Slocation,$rootScope,FriendService){
	function getAllSuggestedUsers(){
		console.log('entering friend controller')
		FriendService.getAllSuggestedUsers().then(
				function(response){
					$scope.suggestedUsers=response.data
			
		},function(response){
			$rootScope.error=response.data
			if(response.status==401)
				$location.path('/login')
			
		})
	}
	function getPending(){
		FriendService.getPendingRequests().then(
				function(response){
					$scope.pendingRequests=response.data
				},function(response){
					$rootScope.error=response.data
					if(response.status==401)
						$location.path('/login')
				})
	}
	$scope.addFriend=function(toId)
	{
		FriendService.addFriend(toId).then(
				function(response){
					alert('Friend request has been sent successfully')
					getAllSuggestedUsers()
				},function(response){
				$rootScope.error=response.data
				if(response.status==401)
					$location.path('/login')
			
		})
	}
	$scope.acceptRequest=function(request){
		FriendService.acceptRequest(request).then(function(response){
			getPendingRequests()
		},function(response){
			$rootScope.error=response.data
			if(response.status==401)
				$location.path('/login')
			
		})
	}
	$scope.deleteRequest=function(request){
		FriendService.deleteRequest(request).then(function(response){
			getPendingRequests()
		},function(response){
			$rootScope.error=response.data
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	getAllSuggestedUsers()
	getPendingRequests()
})