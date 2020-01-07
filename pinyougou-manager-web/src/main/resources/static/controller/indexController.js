// 首页控制器
app.controller('indexController', function ($scope, loginService) {

    // 获取登录用户名
    $scope.getLoginName = function () {
        loginService.getLoginName().success(
            function (response) {
                $scope.loginName = response.loginName;
            }
        )
    }
});