// 商家管理 服务
app.service('goodsService', function ($http) {

    // 保存
    this.save = function (entity) {
        return $http.post('/consumer/goods', entity);
    };

    // 更新
    this.update = function (entity) {
        return $http({
            method: 'PUT',
            url: '/consumer/goods',
            data: entity
        });
    };

    // 分页条件查询
    this.findPage = function (currentPage, pageNum, entity) {
        return $http.post('/consumer/goods/page?currentPage=' + currentPage + '&pageNum=' + pageNum, entity);
    };

    // 详情
    this.findOne = function (id) {
        return $http.get('/consumer/goods/' + id);
    };

    // 批量删除
    this.deleteBatch = function (ids) {
        return $http({
            method: 'DELETE',
            url: '/consumer/goods/deleteBatch/' + ids
        })
    };

    // 上/下架
    this.updateMarketable = function (id, marketable) {
        return $http.get('/consumer/goods/updateMarketable/' + id + '/' + marketable);
    }

});