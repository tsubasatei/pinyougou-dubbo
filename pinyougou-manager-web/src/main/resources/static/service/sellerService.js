// 商家管理 服务
app.service('sellerService', function ($http) {

    // 分页条件查询
    this.findPage = function (currentPage, pageNum, entity) {
        return $http.post('/consumer/seller/page?currentPage=' + currentPage + '&pageNum=' + pageNum, entity);
    };

    // 详情
    this.findOne = function (id) {
        return $http.get('/consumer/seller/' + id);
    };

    // 保存
    this.updateStatus = function (sellerId, status) {
        return $http.get('/consumer/seller/updateStatus/' + sellerId + '/' + status);
    };

});