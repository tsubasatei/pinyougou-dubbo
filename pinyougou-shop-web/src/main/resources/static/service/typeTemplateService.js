// 模板管理 服务
app.service('typeTemplateService', function ($http) {

    // 详情
    this.findOne = function (id) {
        return $http.get('/consumer/typeTemplate/' + id);
    };

    // 规格列表
    this.findSpecList = function (id) {
        return $http.get('/consumer/typeTemplate/findSpecList/' + id);
    }
});