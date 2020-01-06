// 规格管理 控制器
app.controller('typeTemplateController', function ($scope, $controller, typeTemplateService, brandService, specificationService) {

    // 伪继承：第一个参数：继承的父类
    $controller('baseController', {$scope:$scope});

    // 查询列表
    $scope.findList = function () {
        typeTemplateService.findList().success(
            function (response) {
                $scope.list = response; // 给列表变量赋值
            }
        )
    };

    // 初始化查询条件
    $scope.searchEntity = {};

    // 分页查询
    $scope.search = function (currentPage, pageNum) {
        typeTemplateService.findPage(currentPage, pageNum, $scope.searchEntity).success(
            function (response) {
                $scope.list = response.records;
                $scope.paginationConf.totalItems = response.total;
            }
        )
    };

    // 保存/更新
    $scope.save = function () {
        var obj = null;
        $scope.entity.customAttributeItems = JSON.stringify($scope.entity.customAttributeItems);
        $scope.entity.brandIds = JSON.stringify($scope.entity.brandIds);
        $scope.entity.specIds = JSON.stringify($scope.entity.specIds);
        // 更新
        if ($scope.entity.id != null) {
            obj = typeTemplateService.update($scope.entity);
        } else {
            // 保存
            obj = typeTemplateService.save($scope.entity);
        }
        obj.success(
            function (response) {
                if (response.success) {
                    alert(response.message);
                    $scope.reloadList(); // 刷新
                } else {
                    alert(response.message);
                }
            }
        )
    };

    /**
     * 详情
     * 从数据库中查询出来的是字符串，我们必须将其转换为json对象才能实现信息的回显。
     */
    $scope.findOne = function(id) {
        typeTemplateService.findOne(id).success(
            function (response) {
                $scope.entity = response;
                $scope.entity.brandIds = JSON.parse($scope.entity.brandIds); //转换品牌列表
                $scope.entity.specIds = JSON.parse($scope.entity.specIds); //转换规格列表
                $scope.entity.customAttributeItems= JSON.parse($scope.entity.customAttributeItems);//转换扩展属性

            }
        )
    };

    // 批量删除
    $scope.deleteBatch = function () {
        if (confirm('确定要删除规格id为：' + $scope.selectIds + ' 的吗？')) {
            typeTemplateService.deleteBatch($scope.selectIds).success(
                function (response) {
                    if (response.success) {
                        alert(response.message);
                        $scope.reloadList(); // 刷新
                    } else {
                        alert(response.message);
                    }
                }
            )
        }
    };

    // $scope.brandList={data:[{id:1,text:'联想'},{id:2,text:'华为'},{id:3,text:'小米'}]};
    //品牌列表
    $scope.brandList={data:[]};
    // 读取品牌列表
    $scope.findBrandList = function () {
        brandService.findBrandList().success(
            function (response) {
                $scope.brandList = {data: response};
            }
        )
    };

    $scope.specList={data:[]};
    // 读取规格列表
    $scope.findSpecList = function () {
        specificationService.findSpecList().success(
            function (response) {
                $scope.specList = {data: response};
            }
        )
    };

    // 新增扩展属性选项行
    $scope.addTableRow = function () {
        $scope.entity.customAttributeItems.push({});
    };

    // 删除扩展属性选项行
    $scope.deleteTableRow = function (index) {
        $scope.entity.customAttributeItems.splice(index, 1);
    }

});