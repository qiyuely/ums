// *** 系统的app，内定义驱动整个系统的全局变量 ***

/**
 * 系统的app
 */
var mainApp = angular.module("mainApp", ["ui.router"]);

/**
 * 用于集中处理路由而定义出来，各功能点定义单独的js文件，在每个js中往此对象注入该js所对应的路由信息
 * ，最终这会由app统一注入到ui-router
 * 例子：
 * mainStates["setting/sys/toView"] = {
 * 		url : "/setting/sys/toView",
 * 		templateUrl : "/setting/sys/toView",
 * 		controller : function($scope) {
 * 			
 * 		}
 * }
 */
var mainStates = {};

mainApp.directive('sglclick', ['$parse', function($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attr) {
            var fn = $parse(attr['sglclick']);
            var delay = 400, clicks = 0, timer = null;
            element.on('click', function (event) {
                clicks++;  //count clicks
                if(clicks === 1) {
                    timer = setTimeout(function() {
                        scope.$apply(function () {
                            fn(scope, { $event: event });
                        });
                        clicks = 0;          
                    }, delay);
                } else {
                    clearTimeout(timer);   
                    clicks = 0;         
                }
            });
        }
    };
}])