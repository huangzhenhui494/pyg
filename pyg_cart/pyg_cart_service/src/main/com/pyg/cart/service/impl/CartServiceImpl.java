package com.pyg.cart.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pyg.cart.service.CartService;
import com.pyg.mapper.TbItemMapper;
import com.pyg.pojo.TbItem;
import com.pyg.pojo.TbOrderItem;
import com.pyg.vo.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    // 注入redisTemplate
    @Autowired
    private RedisTemplate redisTemplate;

    // 注入商品mapper接口代理对象
    @Autowired
    private TbItemMapper itemMapper;

    /**
     * 需求:查询redis购物车数据
     * @return
     */
    @Override
    public List<Cart> findRedisCartList(String username) {

        // 查询redis服务购物车列表
        List<Cart> cartList = (List<Cart>) redisTemplate.boundHashOps("cartList").get(username);
        // 判断查询购物车数据是否有值
        if(cartList == null){
            // 如果为空,对象还不new,则返回的对象为空,而不是[]
            cartList = new ArrayList<Cart>();
        }
        return cartList;
    }

    /**
     * 需求添加购物车对象
     * @param cartList
     * @param itemId
     * @param num
     * @return
     */
    @Override
    public List<Cart> addGoodsToCartList(List<Cart> cartList, Long itemId, Integer num) {

        // 根据商品id查询商品数据
        TbItem item = itemMapper.selectByPrimaryKey(itemId);
        // 判断商品是否存在
        if(item == null){
            // 商品不存在
            throw new RuntimeException("商品不存在");
        }
        // 商品状态是否可用
        if(!item.getStatus().equals("1")){
            throw new RuntimeException("商品不可用");
        }
        // 商品可用,获取商家编号
        String sellerId = item.getSellerId();

        // 判断购物车列表中是否存在相同的商家
        Cart cart = this.isSameSeller(cartList,sellerId);

        // 判断是否有相同商家
        if(cart!=null){
            // 有相同的商家
            // 获取此商家的购物清单
            List<TbOrderItem> orderItemList = cart.getOrderItemList();
            // 判断是否有相同商品
            TbOrderItem orderItem = this.isSameItem(orderItemList,itemId);
            // 判断
            if(orderItem != null){
                // 该商品的数量加上num
                orderItem.setNum(orderItem.getNum()+num);
                // 计算总价格
                double totalPrice = orderItem.getNum()*orderItem.getPrice().doubleValue();
                // 总价格变化
                orderItem.setTotalFee(new BigDecimal(totalPrice));

                // 判断 如果商品的数量为0
                if(orderItem.getNum()<=0){
                    orderItemList.remove(orderItem);
                }

                // 判断
                if(orderItemList.size()==0){
                    cartList.remove(cart);
                }
                // 放回购物清单列表
                orderItemList.add(orderItem);
            }else {
                // 不存在相同商品
                TbOrderItem orderItem1 = new TbOrderItem();
                orderItem1.setId(item.getId());
                orderItem1.setSellerId(item.getSellerId());
                orderItem1.setNum(num);
                orderItem1.setPicPath(item.getImage());
                orderItem1.setGoodsId(item.getGoodsId());
                orderItem1.setPrice(item.getPrice());
                orderItem1.setTitle(item.getTitle());
                orderItem1.setTotalFee(new BigDecimal(item.getPrice().doubleValue()*num));
                // 把购物清单对象放入集合
                orderItemList.add(orderItem1);

            }
            // 放入cart商家
            cart.setOrderItemList(orderItemList);
        }else {
            // 没有相同商家
            cart = new Cart();
            // 设置商家
            cart.setSellerId(sellerId);
            cart.setSellerName(item.getSeller());

            // 新建购物清单
            List<TbOrderItem> tbOrderItemList = this.createOrderItem(item,num);

            // 设置购物清单
            cart.setOrderItemList(tbOrderItemList);

        }
        // 把商家对象放入购物车集合
        cartList.add(cart);

        return cartList;
    }

    /**
     * 需求:把商品添加到购物车清单
     * @param item
     * @return
     */
    private List<TbOrderItem> createOrderItem(TbItem item,Integer num) {
        // 创建集合对象List<TBOrderItem> 封装购物车数据
        List<TbOrderItem> tbOrderItems = new ArrayList<>();
        // 创建对象
        TbOrderItem orderItem = new TbOrderItem();
        orderItem.setId(item.getId());
        orderItem.setSellerId(item.getSellerId());
        orderItem.setNum(num);
        orderItem.setPicPath(item.getImage());
        orderItem.setGoodsId(item.getGoodsId());
        orderItem.setPrice(item.getPrice());
        orderItem.setTitle(item.getTitle());
        orderItem.setTotalFee(new BigDecimal(item.getPrice().doubleValue()*num));
        // 把购物清单对象放入集合
        tbOrderItems.add(orderItem);
        return tbOrderItems;
    }

    /**
     * 判断商家中是否存在相同的商品
     * @param orderItemList
     * @param itemId
     * @return
     */
    private TbOrderItem isSameItem(List<TbOrderItem> orderItemList, Long itemId) {
        //循环
        for (TbOrderItem orderItem : orderItemList) {
            // 如果id相等,说明有相同的商品
            if(orderItem.getItemId().equals(itemId.longValue())){
                return orderItem;
            }
        }
        return null;
    }

    /**
     * 判断是否具有相同的商家
     * @param cartList
     * @param sellerId
     * @return
     */
    private Cart isSameSeller(List<Cart> cartList, String sellerId) {
        // 循环判断是否有相同的商家
        for (Cart cart : cartList) {
            // 如果有相同的直接返回
            if(cart.getSellerId().equals(sellerId)){
                return cart;
            }
        }
        // 没有相同的
        return null;
    }

    /**
     * 把购物车列表保存到redis购物车里
     * @param cartList
     * @param username
     */
    @Override
    public void saveCartListToRedisCart(List<Cart> cartList, String username) {

        redisTemplate.boundHashOps("cartList").put("username",cartList);
    }
}
