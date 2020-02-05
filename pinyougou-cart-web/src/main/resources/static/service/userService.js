// 搜索服务层
app.service('userService', function ($http) {

    // 保存
    this.save = function (entity, smsCode) {
        return $http.post('/consumer/user/' + smsCode, entity);
    };

    // 发送验证码
    this.sendCode = function (phone) {
        return $http.get('/consumer/user/sendCode/' + phone);
    }
});