/**
 * url类型选择组件
 */
var urlTypeSelectModule = {
	$scope : {},
	$http : {},
	
	//url类型数据列表
	typeDataList : {},
	//url类型选择回调函数集
	callbackMap : {},
	//操作类型
	type : "",
	
	/**
	 * 构建一个url类型选择组件
	 */
	build : function($scope, $http) {
		var sf = this;
		
		sf.$scope = $scope;
		sf.$http = $http;
		$scope.urlTypeSelectModule = sf;
		
		sf.urlTypeSelectModuleData = {
			/**
			 * url类型数据列表
			 */
			typeDataList : {}
		};
		
		$http({
			url : "/url/type/queryUrlTypeInfo",
			method : "post",
			params : {}
		}).then(function(result) {
			var curDataList = result.data.data || [];
			sf.typeDataList = curDataList;
			
			//初始化节点展开标识
			sf.initExpandStatus(curDataList);
		});
		
		return this;
	},
		
	/**
	 * 打开url类型选择组件界面
	 * type ： 操作类型
	 * callbackMap ： 回调函数集
	 */
	toTypeSelect : function(callbackMap, type) {
		var sf = this;
		
		sf.callbackMap = callbackMap || {};
		sf.type = type;
		
		//打开界面
		$("#urlTypeSelectModulePanel").modal("show");
	},
	
	/**
	 * url类型选择
	 */
	typeSelectLi : function(typeData) {
		var sf = this;
		$scope = sf.$scope;
		
		if (sf.callbackMap.typeSelectLi) {
			sf.callbackMap.typeSelectLi(typeData);
		}
	},
	
	/**
	 * url类型是否已被选择
	 */
	isTypeSelectedLi : function(typeData) {
		var sf = this;
		$scope = sf.$scope;
		
		if (sf.callbackMap.isTypeSelectedLi) {
			return sf.callbackMap.isTypeSelectedLi(typeData);
		}
		
		return false;
	},
	
	/**
	 * 初始化节点展开标识
	 */
	initExpandStatus : function(dataList) {
		var sf = this;
		if (isNotEmpty(dataList)) {
			for (var i = 0; i < dataList.length; i++) {
				var data = dataList[i];
				var childDataList = data.childList;
				
				data.expandStatus = 1;
				
				//子节点列表
				if (isNotEmpty(childDataList)) {
					sf.initExpandStatus(childDataList)
				}
			}
		}
	},
}
