// 模板管理 服务
app.service('typeTemplateService', function ($http) {

    // 查询列表
    this.findList = function () {
        return $http.get('/consumer/typeTemplate/list');
    };

    // 分页条件查询
    this.findPage = function (currentPage, pageNum, entity) {
        return $http.post('/consumer/typeTemplate/page?currentPage=' + currentPage + '&pageNum=' + pageNum, entity);
    };

    // 详情
    this.findOne = function (id) {
        return $http.get('/consumer/typeTemplate/' + id);
    };

    // 保存
    this.save = function (entity) {
        return $http.post('/consumer/typeTemplate', entity);
    };

    // 更新
    this.update = function (entity) {
        return $http({
            method: 'PUT',
            url: '/consumer/typeTemplate',
            data: entity
        })
    };

    // 删除
    this.delete = function (id) {
        return $http({
            method: 'DELETE',
            url: '/consumer/typeTemplate/' + id
        })
    };

    // 批量删除
    this.deleteBatch = function (ids) {
        return $http({
            method: 'DELETE',
            url: '/consumer/typeTemplate/deleteBatch/' + ids
        })
    };

    // 读取类型模板列表
    this.findTypeTemplateList = function () {
        return $http.get('/consumer/typeTemplate/findTypeTemplateList');
    }
});