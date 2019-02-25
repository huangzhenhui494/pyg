package com.pyg.cart.service;

import com.pyg.vo.Cart;

import java.util.List;

public interface CartService {
    /**
     * 需求:查询redis购物车数据
     * @return
     */
    List<Cart> findRedisCartList(String username);

    /**
     * 需求:添加购物车对象
     * @param cartList
     * @param itemId
     * @param num
     * @return
     */
    List<Cart> addGoodsToCartList(List<Cart> cartList, Long itemId, Integer num);

    /**
     * 把购物车列表保存到redis购物车里
     * @param cartList
     * @param username
     */
    void saveCartListToRedisCart(List<Cart> cartList, String username);

    /**
     * 需求:合并购物车
     * @param
     * @param cartListStr
     * @return
     */
    List<Cart> mergetCart(String username, String cartListStr);
}
