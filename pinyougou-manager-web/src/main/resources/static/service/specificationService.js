// 规格管理 服务
app.service('specificationService', function ($http) {

    // 查询列表
    this.findList = function () {
        return $http.get('/consumer/specification/list');
    };

    // 分页条件查询
    this.findPage = function (currentPage, pageNum, entity) {
        return $http.post('/consumer/specification/page?currentPage=' + currentPage + '&pageNum=' + pageNum, entity);
    };

    // 详情
    this.findOne = function (id) {
        return $http.get('/consumer/specification/' + id);
    };

    // 保存
    this.save = function (entity) {
        return $http.post('/consumer/specification', entity);
    };

    // 更新
    this.update = function (entity) {
        return $http({
            method: 'PUT',
            url: '/consumer/specification',
            data: entity
        })
    };

    // 删除
    this.delete = function (id) {
        return $http({
            method: 'DELETE',
            url: '/consumer/specification/' + id
        })
    };

    // 批量删除
    this.deleteBatch = function (ids) {
        return $http({
            method: 'DELETE',
            url: '/consumer/specification/deleteBatch/' + ids
        })
    };

    // 读取规格列表
    this.findSpecList = function () {
        return $http.get('/consumer/specification/findSpecList')
    }
});