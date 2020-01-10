// 商品分类管理 服务
app.service('itemCatService', function ($http) {

    // 根据上级ID查询下级列表
    this.findByParentId = function (parentId) {
        return $http.get('/consumer/itemCat/findByParentId/' + parentId);
    };

    // 查询详情
    this.findOne = function (id) {
        return $http.get('/consumer/itemCat/' + id);
    };

    // 查询所有列表
    this.findItemCatList = function () {
        return $http.get('/consumer/itemCat/list');
    }
});