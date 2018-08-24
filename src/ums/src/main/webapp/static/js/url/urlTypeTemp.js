
mainStates["urlTypeTempView"] = {
	templateUrl : "/url/typeTemp/toView",
	controller : function($scope, $http) {
		
		//数据列表
		$scope.dataList = new Array();
		//编辑类型，0：新增，1：修改
		$scope.editType = "0";
		//编辑的数据
		$scope.editData = {};
		//url类型数据列表
		$scope.typeDataList = new Array();
		
		
		
		//构建url类型选择组件
		urlTypeSelectModule.build($scope, $http);
		
		/**
		 * 查询url类型
		 */
		$scope.searchType = function() {
			$http({
				url : "/url/type/queryUrlTypeInfo",
				method : "post",
				params : {}
			}).then(function(result) {
				var curDataList = result.data.data || [];
				$scope.typeDataList = curDataList;
				
				//查询url信息列表
				$scope.search();
			});
		}
		//默认查询一次url类型
		$scope.searchType();
		
		/**
		 * 查询
		 */
		$scope.search = function() {
			$http({
				url : "/url/typeTemp/queryUrlTypeTempInfo",
				method : "post",
				params : {}
			}).then(function(result) {
				var dataList = result.data.data || [];
				$scope.dataList = dataList;
				
				for (var i = 0; i < dataList.length; i++) {
					var data = dataList[i];
					data.typeList = [];
					
					//补充url类型模板的url类型信息
					$scope.supplementTypeTempInfoForType(data, $scope.typeDataList);
				}
			});
		}
		
		/**
		 * 补充url类型模板的url类型信息
		 */
		$scope.supplementTypeTempInfoForType = function(data, typeDataList) {
			if(isNotEmpty(data.typeIdList) && isNotEmpty(typeDataList)) {
				for (var i = 0; i < data.typeIdList.length; i++) {
					var typeId = data.typeIdList[i];
					
					for (var j = 0; j < typeDataList.length; j++) {
						var typeData = typeDataList[j];
						
						//如果还有子类型列表，则递归处理
						if (isNotEmpty(typeData.childList)) {
							$scope.supplementTypeTempInfoForType(data, typeData.childList);
						}
						
						//有此url类型
						if (typeId == typeData.id) {
							data.typeList.push(typeData);
						}
					}
				}
			}
		}
		
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
			
			//提取编辑的请求参数
			var editReq = $scope.fetchEditReq();
			
			$http({
				url : "/url/typeTemp/createUrlTypeTemp",
				method : "post",
				params : editReq
			}).then(function(result) {
				if (httpSuccess(result)) {
					var callData = result.data.data;
					var data = $scope.editData;
					data.id = callData.id;
					
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
			
			//提取编辑的请求参数
			var editReq = $scope.fetchEditReq();
			
			cfMsg("是否进行修改？", function() {
				$http({
					url : "/url/typeTemp/updateUrlTypeTemp",
					method : "post",
					params : editReq
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
		 * 提取编辑的请求参数
		 */
		$scope.fetchEditReq = function() {
			var editData = $scope.editData;
			
			//url类型id
			var typeIdList = new Array();
			if (isNotEmpty(editData.typeList)) {
				for (var i = 0; i < editData.typeList.length; i++) {
					var typeItem = editData.typeList[i];
					typeIdList.push(typeItem.id);
				}
			}
			
			var editReq = {
				id : editData.id,
				name : editData.name,
				typeIdList : typeIdList
			};
			
			return editReq;
		}
		
		/**
		 * 删除
		 */
		$scope.del = function(dataIndex) {
			var data = $scope.dataList[dataIndex];
			
			cfMsg("是否删除该节点？", function() {
				$http({
					url : "/url/typeTemp/deleteUrlTypeTemp",
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
		 * 编辑url类型模板打开url类型选择界面
		 */
		$scope.editTypeTempToTypeSelect = function() {
			//url类型选择回调函数集
			var urlTypeSelectModuleCallbackMap = {
				
				/**
				 * url类型选择回调函数
				 */
				typeSelectLi : function(typeData) {
					var editData = $scope.editData;
					
					if (isEmpty(editData.typeList)) {
						editData.typeList = [];
					}
					
					for (var i = 0; i < editData.typeList.length; i++) {
						var typeItem = editData.typeList[i];
						//已经存在则去除选择
						if (typeItem.id == typeData.id) {
							editData.typeList.splice(i, 1);
							return;
						}
					}
					
					//最终不存在，则新增
					editData.typeList.push(typeData);
				},
				
				/**
				 * url类型是否已被选择
				 */
				isTypeSelectedLi : function(typeData) {
					var editData = $scope.editData;
					
					if (isNotEmpty(editData.typeList)) {
						for (var i = 0; i < editData.typeList.length; i++) {
							var typeItem = editData.typeList[i];
							//存在该类型
							if (typeItem.id == typeData.id) {
								return true;
							}
						}
					}
					
					return false;
				}
			}
			
			$scope.urlTypeSelectModule.toTypeSelect(urlTypeSelectModuleCallbackMap, "1");
		}
		
		/**
		 * url类型移除选择
		 */
		$scope.typeRemoveSelectLi = function(typeDataIndex) {
			$scope.editData.typeList.splice(typeDataIndex, 1);
		}
		
		/**
		 * 验证编辑的数据
		 */
		$scope.validateEdit = function() {
			var data = $scope.editData;
			if (isEmpty(data.name)) {
				fmsg("模板名称不能为空");
				return false;
			}
			
			return true;
		}
	}
}