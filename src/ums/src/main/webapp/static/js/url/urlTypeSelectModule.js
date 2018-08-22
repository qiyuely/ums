/**
 * url类型选择组件
 */
var urlTypeSelectModule = {
		
	/**
	 * 打开url类型选择组件界面
	 */
	show : function($scope, $http) {
		//初始化
		urlTypeSelectModule.init($scope, $http);
	},
	
	/**
	 * 初始化
	 */
	init : function($scope, $http) {
		//url类型选择组件数据集
		var urlTypeSelectModuleData = {
			/**
			 * url类型数据列表
			 */
			typeDataList : {}
		};
		
		//url类型数据
		$scope.urlTypeSelectModuleData = urlTypeSelectModuleData;
		
		$http({
			url : "/url/type/queryUrlTypeInfo",
			method : "post",
			params : {}
		}).then(function(result) {
			var curDataList = result.data.data || [];
			urlTypeSelectModuleData.typeDataList = curDataList;
			
			//初始化节点展开标识
			urlTypeSelectModule.initExpandStatus(curDataList);
			
			//打开界面
			$("#urlTypeSelectModulePanel").modal("show");
		});
	},
	
	/**
	 * 初始化节点展开标识
	 */
	initExpandStatus : function(dataList) {
		if (isNotEmpty(dataList)) {
			for (var i = 0; i < dataList.length; i++) {
				var data = dataList[i];
				var childDataList = data.childList;
				
				data.expandStatus = 1;
				
				//子节点列表
				if (isNotEmpty(childDataList)) {
					urlTypeSelectModule.initExpandStatus(childDataList)
				}
			}
		}
	},
}
