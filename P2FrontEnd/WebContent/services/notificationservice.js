/**
 * 
 */

app.factory('NotificationService',function($http){
	var notificationService={}
			notificationService.getNotificationsNotViewed=function(){
				return $http.get("http://localhost:8096/P2MiddleEnd/getnotifications")
			}
	notificationService.getNotification=function(id){
		return $http.get("http://localhost:8096/P2MiddleEnd/getnotification/"+id)
	}
	return notificationService;
})