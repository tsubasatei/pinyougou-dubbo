// 搜索控制器
app.controller('searchController', function ($scope, $location, searchService) {

    // 搜索对象
    $scope.searchMap =
        {   'keywords':'',
            'category':'',
            'brand':'',
            'spec':{},
            'price':'',
            'pageNo':1,
            'pageSize':10,
            'sort':'',
            'sortField':''
        };
    // 搜索
    $scope.search = function () {
        if(typeof $scope.searchMap.keywords == "undefined" || $scope.searchMap.keywords == null || $scope.searchMap.keywords == ""){
            return false;
        }
        $scope.searchMap.pageNo = parseInt($scope.searchMap.pageNo);
        searchService.search($scope.searchMap).success(
            function (response) {
                $scope.resultMap = response; //搜索返回的结果
                buildPageLabel(); // 构建分页标签
            }
        )
    };

    // 添加搜索项
    $scope.addSearchItem = function (key, value) {
        // 如果点击的是商品分类/品牌/价格
        if (key == 'category' || key == 'brand' || key == 'price') {
            $scope.searchMap[key] = value;
        } else {
            // 点击规格
            $scope.searchMap.spec[key] = value;
        }
        // 执行搜索
        $scope.search();
    };

    // 移除复合搜索条件
    $scope.removeSearchItem = function (key) {
        // 移除的是商品分类或者是品牌
        if (key == 'category' || key == 'brand' || key == 'price') {
            $scope.searchMap[key] = '';
        } else {
            // 移除规格
            delete $scope.searchMap.spec[key]; //移除此属性
        }
        // 执行搜索
        $scope.search();
    };

    // 构建分页标签(totalPages为总页数)
    buildPageLabel = function(){
        // 新增分页栏属性
        $scope.pageLabel = [];
        // 得到最后页码
        var maxPageNo = $scope.resultMap.totalPages;
        // 开始页码
        var firstPage = 1;
        // 截止页码
        var endPage = maxPageNo;
        /**
         * 显示页码
         * 最多只显示5页条码
         */
        $scope.firstDot = true; // 前面有点
        $scope.endDot = true; // 后边有点

        // 如果总页数大于5页,显示部分页码
        if ($scope.resultMap.totalPages > 5) {
            // 如果当前页小于等于3
            if ($scope.searchMap.pageNo <= 3) {
                endPage = 5; // 只显示前5页
                $scope.firstDot = false;
            } else if ($scope.searchMap.pageNo >= $scope.resultMap.totalPages - 2) {
                firstPage = maxPageNo - 4; // 显示后5页
                $scope.endDot = false;
            } else { // 显示当前页为中心的5页
                firstPage = $scope.searchMap.pageNo - 2;
                endPage = $scope.searchMap.pageNo = 2;
            }
        } else {
            $scope.firstDot = false;
            $scope.endDot = false;
        }
        // 循环产生页码标签
        for (var i = firstPage; i <= endPage; i++) {
            $scope.pageLabel.push(i);

        }
    };

    // 根据页码查询
    $scope.queryByPage = function (pageNo) {
        if (pageNo < 1 || pageNo > $scope.resultMap.totalPages) {
            return false;
        }
        $scope.searchMap.pageNo = pageNo;
        $scope.search();
    };

    // 判断当前页是否为第一页
    $scope.isFirstPage = function () {
        if ($scope.searchMap.pageNo == 1) {
            return true;
        } else {
            return false;
        }
    };

    // 判断当前页是否为最后一页
    $scope.isEndPage = function () {
        if ($scope.searchMap.pageNo == $scope.resultMap.totalPages) {
            return true;
        } else {
            return false;
        }
    };

    // 设置排序规则
    $scope.sortSearch = function (sortField, sort) {
        $scope.searchMap.sortField = sortField;
        $scope.searchMap.sort = sort;
        $scope.search();
    };

    // 判断关键字是不是品牌
    $scope.keywordsIsBrand = function () {
        for (var i = 0; i < $scope.resultMap.brandList.length; i++) {
            if ($scope.searchMap.keywords.indexOf($scope.resultMap.brandList[i].text) >= 0) { // 如果包含
                return true;
            }
        }
        return false;
    };

    // 加载查询字符串
    $scope.loadKeywords = function () {
        $scope.searchMap.keywords = $location.search()['keywords'];
        $scope.search();
    }


});