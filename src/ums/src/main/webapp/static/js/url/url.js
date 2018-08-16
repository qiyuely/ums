/* url视图 */

mainStates["urlView"] = {
	templateUrl : "/url/toView",
	controller : function($scope, $http) {
		
		//数据列表
		$scope.dataList = {};
		
		/**
		 * 查询
		 */
		$scope.search = function() {
			$http({
				url : "/url/queryList",
				method : "post",
				params : {}
			}).then(function(result) {
				$scope.dataList = result.data.data;
			});
		}
		//默认查询一次
		$scope.search();
	}
}