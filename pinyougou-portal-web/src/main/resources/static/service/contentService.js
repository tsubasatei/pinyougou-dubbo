// 广告管理 服务
app.service('contentService', function ($http) {

    // 查询列表
    this.findContentList = function (categoryId) {
        return $http.get('/consumer/content/findContentList/' + categoryId);
    };


});