// 商家管理 服务
app.service('sellerService', function ($http) {

    // 保存
    this.save = function (entity) {
        return $http.post('/consumer/seller', entity);
    };


});