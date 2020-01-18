// 搜索控制器
app.controller('searchController', function ($scope, searchService) {

    // 搜索对象
    $scope.searchMap = {'keywords':'', 'category':'', 'brand':'', 'spec':{}};
    // 搜索
    $scope.search = function () {
        searchService.search($scope.searchMap).success(
            function (response) {
                $scope.resultMap = response; //搜索返回的结果
            }
        )
    };

    // 添加搜索项
    $scope.addSearchItem = function (key, value) {
        // 如果点击的是商品分类或者是品牌
        if (key == 'category' || key == 'brand') {
            $scope.searchMap[key] = value;
        } else {
            // 点击规格
            $scope.searchMap.spec[key] = value;
        }
        // 执行搜索
        $scope.search();
    };

    // 移除复合搜索条件
    $scope.removeSearchItem = function (key) {
        // 移除的是商品分类或者是品牌
        if (key == 'category' || key == 'brand') {
            $scope.searchMap[key] = '';
        } else {
            // 移除规格
            delete $scope.searchMap.spec[key]; //移除此属性
        }
        // 执行搜索
        $scope.search();
    }
});