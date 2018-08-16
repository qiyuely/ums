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
		//编辑的父节点数据
		$scope.editParentData = {};
		
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
				
				for (var data in curDataList) {
					data.parentName = "根节点";
				}
				
				var rootElem = {
					"name" : "根节点",
					"childList" : curDataList
				}
				$scope.dataList = [rootElem];
			});
		}
		//默认查询一次
		$scope.search();
		
		$('.dd').nestable();
		
		/**
		 * 打开新增界面
		 */
		$scope.toAdd = function(data) {
			$scope.editType = "0";
			$scope.editParentData = data;
			
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
			$scope.editType = "1";
			
			$scope.editData = data;
			
			$('#editPanel').modal("show");
		}
		
		/**
		 * 保存
		 */
		$scope.save = function() {
			//修改
			if ($scope.editType == "1") {
				$scope.update();
			} else {
				$scope.add();
			}
		}
		
		/**
		 * 新增
		 */
		$scope.add = function() {
			$http({
				url : "/url/type/createUrlType",
				method : "post",
				params : $scope.editData
			}).then(function(result) {
				var data = result.data.data;
				$scope.editParentData.childList.push(data);
				
				rmsg("创建成功！");
				$('#editPanel').modal('hide');
			});
		}
		
		/**
		 * 修改
		 */
		$scope.update = function() {
			$http({
				url : "/url/type/updateUrlType",
				method : "post",
				params : $scope.editData
			}).then(function(result) {
				rmsg("修改成功！");
				$('#editPanel').modal('hide');
			});
		}
		
		/**
		 * 删除
		 */
		$scope.del = function(dataIndex, parentData) {
			var data = parentData.childList[dataIndex];
			
			$http({
				url : "/url/type/deleteUrlType",
				method : "post",
				params : {id : data.id}
			}).then(function(result) {
				parentData.childList.splice(dataIndex, 1);
				rmsg("删除成功！");
			});
		}
		
		/**
		 * 是否允许修改
		 */
		$scope.isAllowUpdate = function(data) {
			if (!data) return false;
			if (data.id == null || data.id == '') return false;
			return true;
		}
		
		/**
		 * 是否允许删除
		 */
		$scope.isAllowDel = function(data) {
			if (!data) return false;
			if (data.id == null || data.id == '') return false;
			if (data.childList != null && data.childList.length != 0) return false;
			return true;
		}
	}
}
