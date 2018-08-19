/* url视图 */

mainStates["urlView"] = {
	templateUrl : "/url/toView",
	controller : function($scope, $http) {
		
		//数据列表
		$scope.dataList = {};
		//编辑类型，0：新增，1：修改
		$scope.editType = "0";
		//编辑的数据
		$scope.editData = {};
		
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
		
		/**
		 * 打开新增界面
		 */
		$scope.toAdd = function(data) {
			$scope.editType = "0";
			
			//重置
			$scope.editData = {};
			
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
			//数据验证
			if (!$scope.validateEdit()) {
				return;
			}
			
			$http({
				url : "/url/createUrl",
				method : "post",
				params : $scope.editData
			}).then(function(result) {
				if (httpSuccess(result)) {
					var data = result.data.data;
					$scope.dataList.push(data);
					
					rmsg("创建成功！");
					$('#editPanel').modal('hide');
				} else {
					writeHttpErrorMsg(result);
				}
			});
		}
		
		/**
		 * 修改
		 */
		$scope.update = function() {
			//数据验证
			if (!$scope.validateEdit()) {
				return;
			}
			
			cfMsg("是否进行修改？", function() {
				$http({
					url : "/url/updateUrl",
					method : "post",
					params : $scope.editData
				}).then(function(result) {
					if (httpSuccess(result)) {
						rmsg("修改成功！");
						$('#editPanel').modal('hide');
					} else {
						writeHttpErrorMsg(result);
					}
				});
			});
		}
		
		/**
		 * 删除
		 */
		$scope.del = function(dataIndex) {
			var data = $scope.dataList[dataIndex];
			
			cfMsg("是否删除该节点？", function() {
				$http({
					url : "/url/deleteUrl",
					method : "post",
					params : {id : data.id}
				}).then(function(result) {
					if (httpSuccess(result)) {
						$scope.dataList.splice(dataIndex, 1);
						rmsg("删除成功！");
					} else {
						writeHttpErrorMsg(result);
					}
				});
			})
		}
		
		/**
		 * 验证编辑的数据
		 */
		$scope.validateEdit = function() {
			var data = $scope.editData;
			if (isEmpty(data.url)) {
				fmsg("URL不能为空");
				return false;
			}
			
			return true;
		}
	}
}