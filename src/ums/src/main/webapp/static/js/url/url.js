/* url视图 */

mainStates["urlView"] = {
	templateUrl : "/url/toView",
	controller : function($scope, $http, $timeout) {
		
		//数据列表
		$scope.dataList = new Array();
		//编辑类型，0：新增，1：修改
		$scope.editType = "0";
		//编辑的数据
		$scope.editData = {};
		//是否展示url类型模板区域，0：否；1：是
		$scope.isTypeTempShowArea = 1;
		//单列表结构的url类型数据列表
		$scope.typeDataLineList = new Array();
		//类型模板已选择的类型数据列表
		$scope.typeTempSelectTypeDataList = new Array();
		//类型模板数据列表
		$scope.typeTempDataList = new Array();
		//已选中的类型模板
		$scope.typeTempDataSelected = {};
		//编辑的类型数据列表
		$scope.editTypeDataList = new Array();
		//过滤出的url信息列表
		$scope.filterUrlInfoList = new Array();
		//关键词
		$scope.urlKeyword = "";
		//操作设定历史记录列表
		$scope.sysOpSettingHistoryList = new Array();
		//是否使用操作设定功能
		$scope.useSysOpSetting = true;
		
		
		//构建url类型选择组件
		urlTypeSelectModule.build($scope, $http);
		
		/**
		 * 查询url相关全部信息
		 */
		$scope.searchAllInfo = function() {
			$http({
				url : "/url/queryUrlAllInfo",
				method : "post",
				params : {}
			}).then(function(result) {
				var urlAllInfo = result.data.data || new Array();
				//url信息列表
				var	urlList = urlAllInfo.urlList ||  new Array();
				//url类型列表
				var typeList = urlAllInfo.typeList ||  new Array();
				//url类型模板列表
				var typeTempList = urlAllInfo.typeTempList ||  new Array();
				//操作设定信息列表
				var	opSettingHistoryList = urlAllInfo.opSettingHistoryList ||  new Array();
				
				$scope.dataList = urlList;
				
				//提取url类型数据列表为单列表结构的
				$scope.fetchLineTypeDataLine(typeList);
				
				//默认类型模板
				var defaultTypeTempData = {
					id : "DEFAULT_TEMP",
					name : "默  认",
					active : 1,
					typeIdList : new Array(),
					typeIdEnabledList : new Array()
				}
				$scope.typeTempDataList.push(defaultTypeTempData);
				
				for (var i = 0; i < typeTempList.length; i++) {
					var typeTempData = typeTempList[i];
					typeTempData.active = 0;
					
					//可用类型id列表
					typeTempData.typeIdEnabledList = new Array();
					for (var k = 0; k < typeTempData.typeIdList.length; k++) {
						var typeId = typeTempData.typeIdList[k];
						typeTempData.typeIdEnabledList.push(typeId);
					}
					
					$scope.typeTempDataList.push(typeTempData);
				}
				
				$scope.sysOpSettingHistoryList = opSettingHistoryList;
				
				//初始化操作设定
				$scope.initSysOpSettingHistory();
			});
		}
		//默认查询url相关全部信息
		$scope.searchAllInfo();
		
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
				url : "/url/createUrl",
				method : "post",
				params : editReq
			}).then(function(result) {
				if (httpSuccess(result)) {
					var callData = result.data.data;
					var data = $scope.editData;
					data.id = callData.id;
					
					$scope.dataList.push(data);
					
					//过滤出url信息列表
					$scope.filterUrlInfoListHandle();
					
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
					url : "/url/updateUrl",
					method : "post",
					params : editReq
				}).then(function(result) {
					if (httpSuccess(result)) {
						//过滤出url信息列表
						$scope.filterUrlInfoListHandle();
						
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
				url : editData.url,
				remark : editData.remark,
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
					url : "/url/deleteUrl",
					method : "post",
					params : {id : data.id}
				}).then(function(result) {
					if (httpSuccess(result)) {
						$scope.dataList.splice(dataIndex, 1);
						
						//过滤出url信息列表
						$scope.filterUrlInfoListHandle();
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
		
		/**
		 * 编辑url打开url类型选择界面
		 */
		$scope.editUrlToTypeSelect = function() {
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
		 * 展示或关闭url类型模板区域
		 */
		$scope.showTypeTempArea = function() {
			var newIsTypeTempShowArea = 1;
			if ($scope.isTypeTempShowArea == 1) {
				newIsTypeTempShowArea = 0;
			} else {
				newIsTypeTempShowArea = 1;
			}
			
			//修改操作设定
			$scope.updateSysOpSetting(sysOpSettingKey.isTypeTempShowArea, newIsTypeTempShowArea);
		}
		
		/**
		 * 类型模板类型选择打开url类型选择界面
		 */
		$scope.typeTempToTypeSelect = function() {
			//url类型选择回调函数集
			var urlTypeSelectModuleCallbackMap = {
				
				/**
				 * url类型选择回调函数
				 */
				typeSelectLi : function(typeData) {
					var typeTempSelectTypeDataList = $scope.typeTempSelectTypeDataList;
					var typeTempData = $scope.typeTempDataSelected;
					
					for (var i = 0; i < typeTempSelectTypeDataList.length; i++) {
						var typeItem = typeTempSelectTypeDataList[i];
						//已经存在则去除选择
						if (typeItem.id == typeData.id) {
							typeTempData.typeIdList.splice(i, 1);
							typeTempSelectTypeDataList.splice(i, 1);
							
							//如果有可用类型id则一并移除
							for (var k = 0; k < typeTempData.typeIdEnabledList.length; k++) {
								var typeIdEnabled = typeTempData.typeIdEnabledList[k];
								if (typeItem.id == typeIdEnabled) {
									typeTempData.typeIdEnabledList.splice(k, 1);
									break;
								}
							}
							return;
						}
					}
					
					//最终不存在，则新增
					typeData.enabledForTypeTemp = 1;
					typeTempData.typeIdList.push(typeData.id);
					typeTempData.typeIdEnabledList.push(typeData.id);
					typeTempSelectTypeDataList.push(typeData);
					
					//过滤出url信息列表
					$scope.filterUrlInfoListHandle();
					
					//修改操作设定
					$scope.updateSysOpSetting(sysOpSettingKey.typeTempTypeIdEnabledList, $scope.fetchEnabledTypeIdListBySysOpSetting());
					
					//如果是默认模板，则保存下操作设定的类型id
					if ("DEFAULT_TEMP" == typeTempData.id) {
						$scope.updateSysOpSetting(sysOpSettingKey.defaultTypeTempForTypeIdList, typeTempData.typeIdList.join(","));
					}
				},
				
				/**
				 * url类型是否已被选择
				 */
				isTypeSelectedLi : function(typeData) {
					var typeTempSelectTypeDataList = $scope.typeTempSelectTypeDataList;
					
					if (isNotEmpty(typeTempSelectTypeDataList)) {
						for (var i = 0; i < typeTempSelectTypeDataList.length; i++) {
							var typeItem = typeTempSelectTypeDataList[i];
							//存在该类型
							if (typeItem.id == typeData.id) {
								return true;
							}
						}
					}
					
					return false;
				},
				
				/**
				 * url类型选择保存
				 */
				typeSelectSave : function() {
					var typeTempData = $scope.typeTempDataSelected;
					var editReq = {
						id : typeTempData.id,
						typeIdList : typeTempData.typeIdList
					};
					
					$http({
						url : "/url/typeTemp/updateUrlTypeTempRel",
						method : "post",
						params : editReq
					}).then(function(result) {
						if (httpSuccess(result)) {
							$scope.urlTypeSelectModule.close();
						} else {
							writeHttpErrorMsg(result);
						}
					});
				},
			}
			
			$scope.urlTypeSelectModule.toTypeSelect(urlTypeSelectModuleCallbackMap, "2");
		}
		
		/**
		 * 模板选择url类型板
		 */
		$scope.typeTempSelectType = function(typeData, $event) {
			//避免双击触发两次单击事件
			if (typeData.typeTempSelectTypeClicked) {
				typeData.typeTempSelectTypeClicked = true;
				return;
			}
			
			typeData.typeTempSelectTypeClicked = true;
			
			$timeout(function () {
				typeData.typeTempSelectTypeClicked = false;
			}, 500);
			
			var newEnabledForTypeTemp = typeData.enabledForTypeTemp == 1 ? 0 : 1;
			typeData.enabledForTypeTemp = newEnabledForTypeTemp;
			var typeTempData = $scope.typeTempDataSelected;
			
			var isMatch = false;
			for (var k = 0; k < typeTempData.typeIdEnabledList.length; k++) {
				var typeIdEnabled = typeTempData.typeIdEnabledList[k];
				if (typeData.id == typeIdEnabled) {
					//禁用
					if (newEnabledForTypeTemp == 0) {
						typeTempData.typeIdEnabledList.splice(k, 1);
					}
					
					isMatch = true;
					break;
				}
			}
			
			//如最终未匹配上，则为新增
			if (!isMatch && newEnabledForTypeTemp == 1) {
				typeTempData.typeIdEnabledList.push(typeData.id);
			}
			
			//过滤出url信息列表
			$scope.filterUrlInfoListHandle();
			
			//修改操作设定
			$scope.updateSysOpSetting(sysOpSettingKey.typeTempTypeIdEnabledList, $scope.fetchEnabledTypeIdListBySysOpSetting());
		}
		
		/**
		 * 模板选择单个url类型
		 */
		$scope.typeTempSelectSingleType = function(typeData) {
			var typeTempData = $scope.typeTempDataSelected;
			var typeDataSelectList = $scope.typeTempSelectTypeDataList ;
			
//			for (var k = 0; k < typeTempData.typeIdEnabledList.length; k++) {
//				var typeIdEnabled = typeTempData.typeIdEnabledList[k];
//				//双击时，此类型节点不做处理，其它全部置为不可用
//				if (typeData.id != typeIdEnabled) {
//					typeTempData.typeIdEnabledList.splice(k, 1);
//					k--;
//					continue;
//				}
//			}
			
			for (var i = 0; i < typeDataSelectList.length; i++) {
				var typeDataSelect = typeDataSelectList[i];
					//双击时，此类型节点不做处理，其它同级别节点全部置为不可用
					if (typeData.parentId == typeDataSelect.parentId && typeData.id != typeDataSelect.id) {
						typeDataSelect.enabledForTypeTemp = 0;
						
						for (var k = 0; k < typeTempData.typeIdEnabledList.length; k++) {
							var typeIdEnabled = typeTempData.typeIdEnabledList[k];
							if (typeDataSelect.id == typeIdEnabled) {
								typeTempData.typeIdEnabledList.splice(k, 1);
								k--;
								break;
							}
						}
					}
				
			}
			
			//过滤出url信息列表
			$scope.filterUrlInfoListHandle();
			
			//修改操作设定
			$scope.updateSysOpSetting(sysOpSettingKey.typeTempTypeIdEnabledList, $scope.fetchEnabledTypeIdListBySysOpSetting());
		}
		
		/**
		 * 选择某个url类型模板
		 */
		$scope.typeTempSelect = function(typeTempData, useSysOpSetting) {
			useSysOpSetting = useSysOpSetting || false;
			$scope.typeTempDataSelected = typeTempData;
			$scope.typeTempSelectTypeDataList = new Array();
			var typeDataLineList = $scope.typeDataLineList;
			
			if (isNotEmpty(typeTempData.typeIdList)) {
				for (var j = 0; j < typeTempData.typeIdList.length; j++) {
					var typeId = typeTempData.typeIdList[j];
					
					for (var i = 0; i < typeDataLineList.length; i++) {
						var typeData = typeDataLineList[i];
						
						//有此url类型
						if (typeId == typeData.id) {
							$scope.typeTempSelectTypeDataList.push(typeData);
							
							var enabledForTypeTemp = 0;
							for (var k = 0; k < typeTempData.typeIdEnabledList.length; k++) {
								var typeIdEnabled = typeTempData.typeIdEnabledList[k];
								if (typeId == typeIdEnabled) {
									enabledForTypeTemp = 1;
									break;
								}
							}
							//针对类型模板是否可用
							typeData.enabledForTypeTemp = enabledForTypeTemp;
							
							break;
						}
					}
				}
			}
			
			if (!useSysOpSetting) {
				//过滤出url信息列表
				$scope.filterUrlInfoListHandle();
				
				//修改操作设定
				$scope.updateSysOpSetting(sysOpSettingKey.typeTempIdSelected, typeTempData.id);
				
				//修改操作设定
				$scope.updateSysOpSetting(sysOpSettingKey.typeTempTypeIdEnabledList, $scope.fetchEnabledTypeIdListBySysOpSetting());
			}
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
		
		/**
		 * 关键词失去焦点事件
		 */
		$scope.keywordBlur = function() {
			//过滤出url信息列表
			$scope.filterUrlInfoListHandle();
			
			//修改操作设定
			$scope.updateSysOpSetting(sysOpSettingKey.keyword, $scope.urlKeyword);
		}
		
		/**
		 * 关键词回车事件
		 */
		$scope.keywordKeyup = function(e) {
			var keycode = window.event ? e.keyCode : e.which;
			if(keycode == 13){
				//过滤出url信息列表
				$scope.filterUrlInfoListHandle();
				
				//修改操作设定
				$scope.updateSysOpSetting(sysOpSettingKey.keyword, $scope.urlKeyword);
			}
		}
		
		/**
		 * 过滤出url信息列表
		 */
		$scope.filterUrlInfoListHandle = function() {
			$scope.filterUrlInfoList = new Array();
			var filterUrlInfoList = $scope.filterUrlInfoList;
			var allUrlDataList =  $scope.dataList;
			var urlKeyword = $scope.urlKeyword;
			var typeTempSelectTypeDataList = $scope.typeTempSelectTypeDataList;
			
			if (isNotEmpty(allUrlDataList)) {
				for (var i = 0; i < allUrlDataList.length; i++) {
					var urlData = allUrlDataList[i];
					
					//关键词过滤
					if (isNotEmpty(urlKeyword)) {
						if (isEmpty(urlData.url)) {
							continue;
						}
						var reg = new RegExp(urlKeyword, "i");
						if (!urlData.url.match(reg)) {
							continue;
						}
					}
					
					//url类型过滤
					if (isEmpty(urlData.typeIdList)) {
						continue;
					}
					
					label:
					if (isNotEmpty(typeTempSelectTypeDataList)) {
						var isMatch = true;
						
						for (var j = 0; j < typeTempSelectTypeDataList.length; j++) {
							var typeData = typeTempSelectTypeDataList[j];
							if (!typeData.enabledForTypeTemp == 1) {
								continue;
							}
							
							isMatch = false;
							
							for (var k = 0; k < urlData.typeIdList.length; k++) {
								var typeIdItem = urlData.typeIdList[k];
								
								//匹配上则此url满足
								if (typeIdItem == typeData.id) {
									isMatch = true;
									break label;
								}
							}
						}
						
						if (!isMatch) {
							continue;
						}
					}
					
					
					filterUrlInfoList.push(urlData);
				}
			}
		}
		
		/**
		 * 初始化操作设定
		 */
		$scope.initSysOpSettingHistory = function() {
			if (!$scope.useSysOpSetting) {
				return;
			}
			var sysOpSettingHistoryList = $scope.sysOpSettingHistoryList;
			var typeTempDataList = $scope.typeTempDataList;
			
			for (var i = 0; i < sysOpSettingHistoryList.length; i++) {
				var sysOpSetting = sysOpSettingHistoryList[i];
				
				//默认模板已选择的类型id
				if (sysOpSettingKey.defaultTypeTempForTypeIdList == sysOpSetting.opKey) {
					for (var j = 0; j < typeTempDataList.length; j++) {
						var typeTempData = typeTempDataList[j];
						//如果是默认模板，则取出操作设定的类型id
						if ("DEFAULT_TEMP" == typeTempData.id) {
							var typeIdList = sysOpSetting.setting.split(",");
							typeTempData.typeIdList = typeIdList;
						}
					}
					
					break;
				}
			}
			
			for (var i = 0; i < sysOpSettingHistoryList.length; i++) {
				var sysOpSetting = sysOpSettingHistoryList[i];
				
				//已选择的类型模板中的可用类型id
				if (sysOpSettingKey.typeTempTypeIdEnabledList == sysOpSetting.opKey) {
					if (isNotEmpty(typeTempDataList)) {
						var typeIdEnabledListGroupArr = sysOpSetting.setting.split(";");
						
						for (var k = 0; k < typeIdEnabledListGroupArr.length; k++) {
							var typeIdEnabledList = typeIdEnabledListGroupArr[k].split(",");
							
							for (var j = 0; j < typeTempDataList.length; j++) {
								var typeTempData = typeTempDataList[j];
								
								if (typeIdEnabledList[0] == typeTempData.id) {
									typeIdEnabledList.splice(0, 1);
									typeTempData.typeIdEnabledList = typeIdEnabledList;
								}
							}
						}
						
					}
				}
			}
			
			
			
			var matchTempIndex = -1;
			for (var i = 0; i < sysOpSettingHistoryList.length; i++) {
				var sysOpSetting = sysOpSettingHistoryList[i];
				
				//是否展示url类型模板区域
				if (sysOpSettingKey.isTypeTempShowArea == sysOpSetting.opKey && isNotEmpty(sysOpSetting.setting)) {
					var isTypeTempShowArea = parseFloat(sysOpSetting.setting);
					
					$scope.isTypeTempShowArea = isTypeTempShowArea;
				}
				
				//关键词
				if (sysOpSettingKey.keyword == sysOpSetting.opKey) {
					var keyword = sysOpSetting.setting;
					
					$scope.urlKeyword = keyword;
				}
				
				//已选择的类型模板id
				if (sysOpSettingKey.typeTempIdSelected == sysOpSetting.opKey && isNotEmpty(sysOpSetting.setting)) {
					var typeTempIdSelected = sysOpSetting.setting;
					
					if (isNotEmpty(typeTempDataList)) {
						
						for (var j = 0; j < typeTempDataList.length; j++) {
							var typeTempData = typeTempDataList[j];
							
							if (typeTempIdSelected == typeTempData.id) {
								$scope.typeTempSelect(typeTempData, false);
								matchTempIndex = j;
								break;
							}
						}
						
						if (matchTempIndex != -1) {
							for (var j = 0; j < typeTempDataList.length; j++) {
								var typeTempData = typeTempDataList[j];
								
								if (matchTempIndex == j) {
									typeTempData.active = 1;
								} else {
									typeTempData.active = 0;
								}
							}
						}
					}
				}
			}
			
			//过滤出url信息列表
			$scope.filterUrlInfoListHandle();
		}
		
		/**
		 * 修改操作设定
		 */
		$scope.updateSysOpSetting = function(opKey, setting) {
			var editReq = {
				opKey : opKey,
				setting : setting
			}
			
			$http({
				url : "/sys/opSettingHistory/updateOpSettingHistory",
				method : "post",
				params : editReq
			}).then(function(result) {
				if (httpSuccess(result)) {
					
				} else {
					var msg = result.data.msg;
					if (isEmpty(msg)) {
						msg = "未知错误";
					}
					console.error(msg);
				}
			});
		}
		
		/**
		 * 提取用于操作设定的可用类型id列表
		 */
		$scope.fetchEnabledTypeIdListBySysOpSetting = function() {
			var setting = "";
			var typeTempDataList = $scope.typeTempDataList;
			
			if (isNotEmpty(typeTempDataList)) {
				for (var i = 0; i < typeTempDataList.length; i++) {
					var typeTempData = typeTempDataList[i];
					
					if (i != 0) {
						setting += ";"
					}
					
					setting += typeTempData.id;
					
					var typeIdEnabledList = typeTempData.typeIdEnabledList;
					if (isNotEmpty(typeIdEnabledList)) {
						setting += ",";
						setting += typeIdEnabledList.join(",");
					}
				}
			}
			
			return setting;
		}
	}
}