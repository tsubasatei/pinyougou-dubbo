// 品牌管理 服务
app.service('brandService', function ($http) {

    // 查询列表
    this.findList = function () {
        return $http.get('/consumer/brand/list');
    };

    // 分页条件查询
    this.findPage = function (currentPage, pageNum, entity) {
        return $http.post('/consumer/brand/page?currentPage=' + currentPage + '&pageNum=' + pageNum, entity);
    };

    // 详情
    this.findOne = function (id) {
        return $http.get('/consumer/brand/' + id);
    };

    // 保存
    this.save = function (entity) {
        return $http.post('/consumer/brand', entity);
    };

    // 更新
    this.update = function (entity) {
        return $http({
            method: 'PUT',
            url: '/consumer/brand',
            data: entity
        })
    };

    // 删除
    this.delete = function (id) {
        return $http({
            method: 'DELETE',
            url: '/consumer/brand/' + id,
        })
    };

    // 批量删除
    this.deleteBatch = function (ids) {
        return $http({
            method: 'DELETE',
            url: '/consumer/brand/deleteBatch/' + ids
        })
    };
});