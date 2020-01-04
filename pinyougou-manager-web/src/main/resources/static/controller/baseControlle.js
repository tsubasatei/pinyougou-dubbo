// 基础控制器
app.controller('baseController', function ($scope) {

    //重新加载列表数据
    $scope.reloadList=function(){
        //切换页码
        $scope.search( $scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
    };

    /**
     * 分页控件配置
     * 	currentPage: 当前页
     * 	totalItems: 总记录数
     * 	itemsPerPage: 每页的记录数
     * 	perPageOptions: 分页选项
     * 	onChange: 当页码变更后自动触发的事件
     */
    $scope.paginationConf = {
        currentPage: 1,
        totalItems: 10,
        itemsPerPage: 10,
        perPageOptions: [10, 20, 30, 40, 50],
        onChange: function(){
            $scope.reloadList();//重新加载
        }
    };

    // 选中的ID集合
    $scope.selectIds = [];

    // 更新复选框 selectIds
    $scope.updateSelection = function ($event, id) {
        if ($event.target.checked) {
            $scope.selectIds.push(id); //push向集合添加元素
        } else {
            var index = $scope.selectIds.indexOf(id); //查找 id 值的位置
            selectIds.splice(index, 1); // 参数1：移除的位置； 参数2：移除的个数
        }
    }

});