
mainStates["urlTypeTempView"] = {
	templateUrl : "/url/typeTemp/toView",
	controller : function($scope, $http) {
		
		//数据列表
		$scope.dataList = new Array();
		//编辑类型，0：新增，1：修改
		$scope.editType = "0";
		//编辑的数据
		$scope.editData = {};
		//单列表结构的url类型数据列表
		$scope.typeDataLineList = new Array();
		//编辑的类型数据列表
		$scope.editTypeDataList = new Array();
		
		
		
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
				var dataList = result.data.data || [];
				
				//提取url类型数据列表为单列表结构的
				$scope.fetchLineTypeDataLine(dataList);
				
				//查询url信息列表
				$scope.search();
			});
		}
		//默认查询一次url类型
		$scope.searchType();
		
		/**
		 * 提取url类型数据列表为单列表结构的
		 */
		$scope.fetchLineTypeDataLine = function(typeDataList) {
			if(isNotEmpty(typeDataList)) {
				for (var i = 0; i < typeDataList.length; i++) {
					var typeData = typeDataList[i];
					
					$scope.typeDataLineList.push(typeData);
					
					//如果还有子类型列表，则递归处理
					if (isNotEmpty(typeData.childList)) {
						$scope.fetchLineTypeDataLine(typeData.childList);
					}
				}
			}
		}
		
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
				}
			});
		}
		
		/**
		 * 打开新增界面
		 */
		$scope.toAdd = function(data) {
			$scope.editType = "0";
			
			//重置
			$scope.editData = {};
			//初始化编辑的类型数据列表
			$scope.initEditTypeDataList();
			
			$('#editPanel').modal("show");
		}
		
		/**
		 * 打开修改界面
		 */
		$scope.toUpdate = function(data) {
			$scope.editType = "1";
			
			$scope.editData = data;
			//初始化编辑的类型数据列表
			$scope.initEditTypeDataList();
			
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
			
			var editReq = {
				id : editData.id,
				name : editData.name,
				typeIdList : editData.typeIdList
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
					
					if (isEmpty(editData.typeIdList)) {
						editData.typeIdList = new Array();
					}
					
					var isRemove = false;
					for (var i = 0; i < editData.typeIdList.length; i++) {
						var typeIdItem = editData.typeIdList[i];
						//已经存在则去除选择
						if (typeIdItem == typeData.id) {
							editData.typeIdList.splice(i, 1);
							isRemove = true;
							break;
						}
					}
					
					//最终不存在，则新增
					if (!isRemove) {
						editData.typeIdList.push(typeData.id);
					}
					
					//初始化编辑的类型数据列表
					$scope.initEditTypeDataList();
				},
				
				/**
				 * url类型是否已被选择
				 */
				isTypeSelectedLi : function(typeData) {
					var editData = $scope.editData;
					
					if (isNotEmpty(editData.typeIdList)) {
						for (var i = 0; i < editData.typeIdList.length; i++) {
							var typeIdItem = editData.typeIdList[i];
							//存在该类型
							if (typeIdItem == typeData.id) {
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
			$scope.editData.typeIdList.splice(typeDataIndex, 1);
			
			//初始化编辑的类型数据列表
			$scope.initEditTypeDataList();
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
		
		/**
		 * 初始化编辑的类型数据列表
		 */
		$scope.initEditTypeDataList = function() {
			var editData = $scope.editData;
			var typeDataLineList = $scope.typeDataLineList;
			$scope.editTypeDataList = new Array();
			
			if (isNotEmpty(editData.typeIdList)) {
				for (var i = 0; i < editData.typeIdList.length; i++) {
					var typeIdItem = editData.typeIdList[i];
					
					for (var j = 0; j < typeDataLineList.length; j++) {
						var typeData = typeDataLineList[j];
						
						if (typeIdItem == typeData.id) {
							$scope.editTypeDataList.push(typeData);
							break;
						}
					}
				}
			}
		}
	}
}