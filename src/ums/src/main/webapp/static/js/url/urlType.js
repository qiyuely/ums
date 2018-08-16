/* url视图 */

mainStates["urlTypeView"] = {
	templateUrl : "/url/type/toView",
	controller : function($scope, $http) {
		
		//数据列表
		$scope.dataList = [];
		//编辑类型，0：新增，1：修改
		$scope.editType = "0";
		//编辑的数据
		$scope.editData = {};
		
		/**
		 * 查询
		 */
		$scope.search = function() {
			$http({
				url : "/url/type/queryUrlTypeInfo",
				method : "post",
				params : {}
			}).then(function(result) {
				var curDataList = result.data.data || [];
				
				for (var data : curDataList) {
					data.parentName = "根节点";
				}
				
				var rootElem = {
					"name" : "根节点",
					"childList" : curDataList;
				}
				$scope.dataList = [rootElem];
			});
		}
		//默认查询一次
		$scope.search();
		
		/**
		 * 打开新增界面
		 */
		$scope.toAdd = function(data) {
			$scope.editType = "0";
			
			//重置
			$scope.editData = {
				parentId : data.id,
				parentName : data.name,
				name : ""
			};
			
			$('#editPanel').modal("show");
		}
		
		/**
		 * 打开修改界面
		 */
		$scope.toUpdate = function(data) {
			$scope.editType = "0";
			
			$scope.editData = data;
			
			$('#editPanel').modal("show");
		}
	}
}