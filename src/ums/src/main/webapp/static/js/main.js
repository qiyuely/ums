//*** 主页 ***

/**
 * 注入已定义的路由
 */
mainApp.config(function($stateProvider) {
	for (var key in mainStates) {
		$stateProvider.state(key, mainStates[key]);
	}
});


/**
 * 主页controller
 */
mainApp.controller("mainController", function($scope, $state) {
	//默认打开首页
	$state.go("urlView");
});
