// 搜索控制器
app.controller('userController', function ($scope, userService) {

    $scope.entity = {};
    // 注册
    $scope.register = function () {
        // 新增
        if ($scope.entity.password == null || $scope.entity.password == ""
            || $scope.password == null || $scope.password == "") {
            alert("密码不能为空");
            return;
        }
        if ($scope.entity.password != $scope.password) {
            alert("两次输入的密码不一致，请重新输入");
            return ;
        }
        userService.save($scope.entity, $scope.smsCode).success(
            function (response) {
                alert(response.message);
            }
        );
    };

    // 发送验证码
    $scope.sendCode = function () {
        if($scope.entity.phone == null || $scope.entity.phone == ""){
            alert("请输入手机号！");
            return ;
        }
        userService.sendCode($scope.entity.phone).success(
            function (response) {
                alert(response.message);
            }
        )
    }
});