// 模块
var app = angular.module('pinyougou', []);

/**
 * $sce 服务写成过滤器
 * $sce 服务的 trustAsHtml 方法来实现转换。
 */
app.filter('trustHtml', ['$sce', function($sce){
    return function(data){
        return $sce.trustAsHtml(data);
    }
}]);
