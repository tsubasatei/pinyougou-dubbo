// 商家管理 控制器
app.controller('sellerController', function ($scope, $controller, sellerService) {

    // 伪继承：第一个参数：继承的父类
    $controller('baseController', {$scope:$scope});

    // 初始化查询条件
    $scope.searchEntity = {};

    // 分页查询
    $scope.search = function (currentPage, pageNum) {
        sellerService.findPage(currentPage, pageNum, $scope.searchEntity).success(
            function (response) {
                $scope.list = response.records;
                $scope.paginationConf.totalItems = response.total;
            }
        )
    };

    // 详情
    $scope.findOne = function(sellerId) {
        sellerService.findOne(sellerId).success(
            function (response) {
                $scope.entity = response;
            }
        )
    };

    // 更改状态
    $scope.updateStatus = function (sellerId, status) {
        sellerService.updateStatus(sellerId, status).success(
            function (response) {
                if (response.success) {
                    alert(response.message);
                    $scope.reloadList();
                } else {
                    alert(response.message);
                }
            }
        )
    }

});