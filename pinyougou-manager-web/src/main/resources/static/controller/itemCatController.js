// 商家管理 控制器
app.controller('itemCatController', function ($scope, $controller, itemCatService, typeTemplateService) {

    // 伪继承：第一个参数：继承的父类
    $controller('baseController', {$scope:$scope});

    // 根据上级ID查询下级列表
    $scope.findByParentId = function (parentId) {
        $scope.parentId = parentId;  //记住上级ID
        itemCatService.findByParentId(parentId).success(
            function (response) {
                $scope.list = response;
            }
        )
    };

    /**
     * 面包屑导航
     * 顶级分类列表 >> entity_1 >> entity_2
     */

    $scope.grade = 1; // 默认为1级

    // 设置级别
    $scope.setGrade = function (value) {
        $scope.grade = value;
    };

    // 读取列表
    $scope.selectList = function (p_entity) {
        if ($scope.grade == 1) {  //如果为1级
            $scope.entity_1 = null;
            $scope.entity_2 = null;
        }
        if ($scope.grade == 2) { //如果为1级
            $scope.entity_1 = p_entity;
            $scope.entity_2 = null;
        }
        if ($scope.grade == 3) {  //如果为1级
            $scope.entity_2 = p_entity;
        }

        $scope.findByParentId(p_entity.id); //查询此级下级列表
    };

    // 记录上级ID
    $scope.parentId = 0;

    // 保存/更新
    $scope.save = function () {
        var obj = null;
        // 更新
        if ($scope.entity.id != null) {
            obj = itemCatService.update($scope.entity);
        } else {
            // 保存
            $scope.entity.parentId = $scope.parentId;
            obj = itemCatService.save($scope.entity);
        }
        obj.success(
            function (response) {
                if (response.success) {
                    alert(response.message);
                    //重新查询
                    $scope.findByParentId($scope.parentId);

                } else {
                    alert(response.message);
                }
            }
        )
    };

    // 详情
    $scope.findOne = function (id) {
        itemCatService.findOne(id).success(
            function (response) {
                $scope.entity = response;
            }
        );
    };

    $scope.typeTemplateList = [];
    // 读取类型模板列表
    $scope.findTypeTemplateList = function () {
        typeTemplateService.findTypeTemplateList().success(
            function (response) {
                $scope.typeTemplateList = response;
            }
        )
    };

    // 批量删除
    $scope.deleteBatch = function () {
        if (confirm('确定要删除商品分类id为：' + $scope.selectIds + ' 的吗？')) {
            itemCatService.deleteBatch($scope.selectIds).success(
                function (response) {
                    if (response.success) {
                        alert(response.message);
                        //重新查询
                        $scope.findByParentId($scope.parentId);
                    } else {
                        alert(response.message);
                    }
                }
            )
        }
    };
});