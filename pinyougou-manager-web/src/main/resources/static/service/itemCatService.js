// 商品分类管理 服务
app.service('itemCatService', function ($http) {

    // 根据上级ID查询下级列表
    this.findByParentId = function (parentId) {
        return $http.get('/consumer/itemCat/findByParentId/' + parentId);
    };

    // 保存
    this.save = function (entity) {
        return $http.post('/consumer/itemCat', entity);
    };

    // 查询详情
    this.findOne = function (id) {
        return $http.get('/consumer/itemCat/' + id);
    };

    // 更新
    this.update = function (entity) {
        return $http({
            method: 'PUT',
            url: '/consumer/itemCat',
            data: entity
        })
    };

    // 批量删除
    this.deleteBatch = function (ids) {
        return $http({
            method: 'DELETE',
            url: '/consumer/itemCat/deleteBatch/' + ids
        })
    };

    // 查询所有列表
    this.findItemCatList = function () {
        return $http.get('/consumer/itemCat/list');
    }
});