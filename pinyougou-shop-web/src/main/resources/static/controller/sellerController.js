// 商家管理 控制器
app.controller('sellerController', function ($scope, sellerService) {

    // 保存/更新
    $scope.register = function () {
        //判断checkbox 是否选中
        if (!$('#m1').is(':checked')) { //选中，返回true，没选中，返回false
            alert('请勾选同意协议并注册');
            return false;
        }
        sellerService.save($scope.entity).success(
            function (response) {
                if (response.success) {
                    // 注册成功。跳转登录页面
                    location.href = '/login';
                } else {
                    alert(response.message);
                }
            }
        )
    };

});