// 广告管理 控制器
app.controller('contentController', function ($scope, contentService) {

    //广告集合
    $scope.contentList = [];

    // 查询列表
    $scope.findContentList = function (categoryId) {
        contentService.findContentList(categoryId).success(
            function (response) {
                $scope.contentList[categoryId] = response; // 给列表变量赋值
            }
        )
    };


});