//购物车服务层
app.service('cartService', function($http){
    // 购物车列表
    this.findCartList = function(){
        return $http.get('/consumer/cart/findCartList');
    };

    // 添加商品到购物车
    this.addGoodsToCartList = function(itemId, num){
        return $http.get('/consumer/cart/' + itemId + '/' + num);
    };

    // 求合计
    this.sum = function(cartList){
        var totalValue = {totalNum:0, totalMoney:0.00 }; //合计实体
        for (var i = 0; i < cartList.length; i++) {
            var orderItemList = cartList[i].orderItemList;
            for (var j = 0; j < orderItemList.length; j++) {
                totalValue.totalNum += orderItemList[j].num;
                totalValue.totalMoney += orderItemList[j].totalFee;
            }
        }
        return totalValue;
    }
});
