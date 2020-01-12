// 品牌管理 控制器
app.controller('contentCategoryController', function ($scope, $controller, contentCategoryService) {

    // 伪继承：第一个参数：继承的父类
    $controller('baseController', {$scope:$scope});

    // 查询列表
    $scope.findList = function () {
        contentCategoryService.findList().success(
            function (response) {
                $scope.list = response; // 给列表变量赋值
            }
        )
    };

    // 初始化查询条件
    $scope.searchEntity = {};

    // 分页查询
    $scope.search = function (currentPage, pageNum) {
        contentCategoryService.findPage(currentPage, pageNum, $scope.searchEntity).success(
            function (response) {
                $scope.list = response.records;
                $scope.paginationConf.totalItems = response.total;
            }
        )
    };

    // 保存/更新
    $scope.save = function () {
        var obj = null;
        // 更新
        if ($scope.entity.id != null) {
            obj = contentCategoryService.update($scope.entity);
        } else {
            // 保存
            obj = contentCategoryService.save($scope.entity);
        }
        obj.success(
            function (response) {
                if (response.success) {
                    alert(response.message);
                    $scope.reloadList(); // 刷新
                } else {
                    alert(response.message);
                }
            }
        )
    };

    // 详情
    $scope.findOne = function(id) {
        contentCategoryService.findOne(id).success(
            function (response) {
                $scope.entity = response;
            }
        )
    };

    // 批量删除
    $scope.deleteBatch = function () {
        if (confirm('确定要删除广告分类id为：' + $scope.selectIds + ' 的吗？')) {
            contentCategoryService.deleteBatch($scope.selectIds).success(
                function (response) {
                    if (response.success) {
                        alert(response.message);
                        $scope.reloadList(); // 刷新
                        $scope.selectIds = [];//清空ID集合
                    } else {
                        alert(response.message);
                    }
                }
            )
        }
    };

});