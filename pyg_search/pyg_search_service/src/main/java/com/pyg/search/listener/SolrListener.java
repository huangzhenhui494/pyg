package com.pyg.search.listener;

import com.pyg.mapper.TbGoodsMapper;
import com.pyg.mapper.TbItemMapper;
import com.pyg.pojo.TbGoods;
import com.pyg.pojo.TbGoodsExample;
import com.pyg.pojo.TbItem;
import com.pyg.pojo.TbItemExample;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;

import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.Collection;
import java.util.List;

/**
 * 需求:监听器接收消息,同步索引库
 * 业务流程:
 *  1.接收消息,消息内容是 商品id
 *  2.根据商品id查询商品数据信息
 *  3.把查询到的商品数据同步到索引库
 */
public class SolrListener implements MessageListener {

    // 注入商品服务
    @Autowired
    private TbGoodsMapper tbGoodsMapper;

    // 注入tbItem接口代理对象
    @Autowired
    private TbItemMapper itemMapper;

    // 注入solr模板对象
    @Autowired
    private SolrTemplate solrTemplate;

    @Override
    public void onMessage(Message message) {
        try {
            // 定义空数组 spu id
            Long ids[] = null;
            // 接收消息
            if(message instanceof ActiveMQObjectMessage){
                // 强转
                ActiveMQObjectMessage m = (ActiveMQObjectMessage) message;
                ids = (Long[]) m.getObject();
            }
            // 循环ids
            for (Long id : ids) {
                // 判断是否是上架,删除
                // 创建example
                TbGoodsExample example = new TbGoodsExample();
                // 创建criteria对象
                TbGoodsExample.Criteria criteria = example.createCriteria();
                // 设置外键 spu id
                criteria.andIdEqualTo(id);
                // 设置查询参数
                criteria.andIsDeleteIsNull();
                // 执行查询 sku 集合
                List<TbGoods> list = tbGoodsMapper.selectByExample(example);

                // 创建example对象
                TbItemExample example1 = new TbItemExample();
                // 创建criteria对象
                TbItemExample.Criteria criteria1 = example1.createCriteria();
                // 设置查询参数
                criteria1.andGoodsIdEqualTo(id);
                // 执行查询集合
                List<TbItem> itemList = itemMapper.selectByExample(example1);
                // 判断操作是删除操作还是修改,添加

                if(list != null && list.size() > 0){
                    // 修改,添加,上架

                    // 写入索引库
                     solrTemplate.saveBeans(itemList);
                    // 提交
                    solrTemplate.commit();
                }else{
                    // 删除,即isdelete为null
                    // 查询旗下所有商品的id
                    Collection<String> strList = null;
                    for (TbItem tbItem : itemList) {
                        solrTemplate.deleteById(tbItem.getId().toString());
                        solrTemplate.commit();
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
