// 商品管理 控制器
app.controller('goodsController', function ($scope, $controller, $location, goodsService, itemCatService, typeTemplateService, fileService) {

    // 伪继承：第一个参数：继承的父类
    $controller('baseController', {$scope:$scope});

    // 保存/更新
    $scope.save = function () {
        $scope.entity.goodsDesc.introduction = editor.html(); // 描述
        $scope.entity.goodsDesc.itemImages = JSON.stringify($scope.entity.goodsDesc.itemImages); // 图片列表
        $scope.entity.goodsDesc.customAttributeItems = JSON.stringify($scope.entity.goodsDesc.customAttributeItems); // 扩展属性
        if (1 == $scope.entity.goods.isEnableSpec) { // 启用规格
            $scope.entity.goodsDesc.specificationItems = JSON.stringify($scope.entity.goodsDesc.specificationItems); // 规格结果集
            for (var i = 0; i < $scope.entity.itemList.length; i++ ) {
                $scope.entity.itemList[i].spec = JSON.stringify($scope.entity.itemList[i].spec)
            }
        } else {
            $scope.entity.goodsDesc.specificationItems = JSON.stringify([]);
            $scope.entity.itemList = [];
        }
        var obj;
        if ($scope.entity.goods.id != null) {
            obj = goodsService.update($scope.entity);
        } else {
            obj = goodsService.save($scope.entity);
        }
        obj.success(
            function (response) {
                if (response.success) {
                    alert(response.message);
                    // 添加成功后置空
                    // $scope.entity = {};
                    // editor.html(''); //清空富文本编辑器
                    location.href = '/goods'; // 跳转列表
                } else {
                    alert(response.message);
                }
            }
        )
    };

    // 商品一级分类列表
    $scope.findItemCat1List = function (parentId) {
        itemCatService.findByParentId(parentId).success(
            function (response) {
                $scope.itemCat1List = response;
            }
        )
    };

    // $watch 方法用于监控某个变量的值，当被监控的值发生变化，就自动执行相应的函数。
    // 商品二级分类列表
    $scope.$watch('entity.goods.category1Id', function (newValue, oldValue) {
        if (newValue != undefined) {
            // 根据选择的值，查询二级分类
            itemCatService.findByParentId(newValue).success(
                function (response) {
                    $scope.itemCat2List = response;
                }
            );
            if(null == $location.search()['id']) {
                $scope.entity.goods.category3Id = null;
                $scope.entity.goods.typeTemplateId = null;
                $scope.brandList = null;
                $scope.entity.goodsDesc.customAttributeItems = null;
                $scope.specList = null;
                $scope.entity.itemList = null;
            }
        }
    });


    // 商品三级分类列表
    $scope.$watch('entity.goods.category2Id', function (newValue, oldValue) {
        if (newValue != undefined) {
            itemCatService.findByParentId(newValue).success(
                function (response) {
                    $scope.itemCat3List = response;
                }
            );
            if(null == $location.search()['id']) {
                $scope.entity.goods.typeTemplateId = null;
                $scope.brandList = null;
                $scope.entity.goodsDesc.customAttributeItems = null;
                $scope.specList = null;
                $scope.entity.itemList = null;
            }
        }
    });

    // 选择三级分类后，读取模板id
    $scope.$watch('entity.goods.category3Id', function(newValue, oldValue) {
        if (newValue != undefined) {
            itemCatService.findOne(newValue).success(
                function (response) {
                    $scope.entity.goods.typeTemplateId = response.typeId;
                }
            )
        }

    });

    //模板ID选择后，更新品牌列表
    $scope.$watch('entity.goods.typeTemplateId', function(newValue, oldValue) {
        typeTemplateService.findOne(newValue).success(
            function (response) {
                $scope.typeTemplate = response; //获取类型模板
                $scope.brandList = JSON.parse($scope.typeTemplate.brandIds); //品牌列表
                // 如果没有ID，则加载模板中的扩展数据
                if(null == $location.search()['id']) {
                    $scope.entity.goodsDesc.customAttributeItems = JSON.parse($scope.typeTemplate.customAttributeItems); // 扩展属性
                }
                // 规格列表
                typeTemplateService.findSpecList(newValue).success(
                    function (response) {
                        $scope.specList = response;
                    }
                )
            }
        )
    });

    // 文件上传
    $scope.uploadFile = function () {
        fileService.uploadFile().success(
            function (response) {
                if (response.success) { //如果上传成功，取出url
                    $scope.image_entity.url = response.message;//设置文件地址
                } else {
                    alert(response.message);
                }
            }
        ).error(function () {
            alert("上传发生错误");
        })
    };

    //定义页面实体结构
    $scope.entity={goods:{}, goodsDesc:{itemImages:[], specificationItems:[]}};

    //添加图片列表
    $scope.add_image_entity = function () {
        $scope.entity.goodsDesc.itemImages.push($scope.image_entity);
    };

    // 删除图片列表
    $scope.remove_image_entity = function(index) {
        $scope.entity.goodsDesc.itemImages.splice(index, 1);
    };

    // 判断规格选项是否选中
    $scope.updateSpecAttribute = function ($event, name, value) {
        var object = $scope.searchObjectByKey($scope.entity.goodsDesc.specificationItems, 'attributeName', name);
        if (object != null) {
            if ($event.target.checked) {  // 选中
                object.attributeValue.push(value);
            } else { // 取消勾选
                object.attributeValue.splice(object.attributeValue.indexOf(value), 1); // 移除选项
                // 如果选项都取消了，将此条记录移除
                if (object.attributeValue.length == 0) {
                    $scope.entity.goodsDesc.specificationItems.splice($scope.entity.goodsDesc.specificationItems.indexOf(object), 1);
                }
            }
        } else {
            $scope.entity.goodsDesc.specificationItems.push({'attributeName':name, 'attributeValue': [value]});
        }
    };

    // 更新规格属性后调用生成SKU列表的方法
    // 创建SKU列表
    $scope.createItemList = function () {
        // 初始化列表
        $scope.entity.itemList = [{spec:{}, price:0, num:99999, status:'0', isDefault:'0' } ];
        var items = $scope.entity.goodsDesc.specificationItems;
        for (var i = 0; i < items.length; i++) {
            $scope.entity.itemList = addColumn($scope.entity.itemList, items[i].attributeName, items[i].attributeValue)
        }
    };

    // 添加列值
    addColumn = function (list, columnName, columnValues) {
        var newList = []; // 新的集合
        for (var i = 0; i < list.length; i++) {
            var oldRow = list[i];
            for (var j = 0; j < columnValues.length; j++) {
                var newRow = JSON.parse(JSON.stringify(oldRow)); // 深克隆
                newRow.spec[columnName] = columnValues[j];
                newList.push(newRow);
            }
        }
        return newList;
    };

    // 初始化查询条件
    $scope.searchEntity = {};

    // 分页查询
    $scope.search = function (currentPage, pageNum) {
        goodsService.findPage(currentPage, pageNum, $scope.searchEntity).success(
            function (response) {
                $scope.list = response.records;
                $scope.paginationConf.totalItems = response.total;
            }
        )
    };

    // 详情
    $scope.findOne = function() {
        var id = $location.search()['id']; // 获取参数值
        if (null == id) {
            return ;
        }
        goodsService.findOne(id).success(
            function (response) {
                $scope.entity = response;
                // 向富文本编辑器添加商品介绍
                editor.html($scope.entity.goodsDesc.introduction);
                // 图片列表
                $scope.entity.goodsDesc.itemImages = JSON.parse($scope.entity.goodsDesc.itemImages);
                // 扩展属性
                $scope.entity.goodsDesc.customAttributeItems = JSON.parse($scope.entity.goodsDesc.customAttributeItems);
                // 规格
                $scope.entity.goodsDesc.specificationItems = JSON.parse($scope.entity.goodsDesc.specificationItems);
                // SKU列表规格列转换
                for (var i = 0; i < $scope.entity.itemList.length; i++) {
                    $scope.entity.itemList[i].spec = JSON.parse($scope.entity.itemList[i].spec);
                }
            }
        )
    };

    // 批量删除
    $scope.deleteBatch = function () {
        if (confirm('确定要删除商品id为：' + $scope.selectIds + ' 的吗？')) {
            goodsService.deleteBatch($scope.selectIds).success(
                function (response) {
                    if (response.success) {
                        alert(response.message);
                        $scope.reloadList(); // 刷新
                        $scope.selectIds = [];//清空ID集合
                    } else {
                        alert(response.message);
                    }
                }
            )
        }
    };

    $scope.status=['未审核','已审核','审核未通过','关闭']; //商品状态
    $scope.itemCatList = [];//商品分类列表
    //加载商品分类列表
    $scope.findItemCatList = function () {
        itemCatService.findItemCatList().success(
            function (response) {
                for (var i = 0; i < response.length; i++) {
                    $scope.itemCatList[response[i].id] = response[i].name;
                }
            }
        )
    };

    //根据规格名称和选项名称返回是否被勾选
    $scope.checkAttributeValue = function (specName, specValue) {
        var items = $scope.entity.goodsDesc.specificationItems;
        var object = $scope.searchObjectByKey(items, 'attributeName', specName);
        if (null == object) {
            return false;
        } 
        if (object.attributeValue.indexOf(specValue) >= 0) {
            return true;
        }
        return false;
    };

    // 上/下级
    $scope.updateMarketable = function (id, marketable) {
        goodsService.updateMarketable(id, marketable).success(
            function (response) {
                if (response.success) {
                    alert(response.message);
                    $scope.reloadList();
                } else {
                    alert(response.message);
                }
            }
        )
    }

});