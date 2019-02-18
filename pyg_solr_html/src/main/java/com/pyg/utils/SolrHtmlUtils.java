package com.pyg.utils;

import com.alibaba.fastjson.JSON;
import com.pyg.pojo.TbItem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SolrHtmlUtils {

    /**
     * 需求: 接收消息,同步索引库
     * 业务:
     * 1.接受发布订阅空间消息
     * 2.使用solr服务把消息写入索引库即可
     */
    // 注入solrTemplate模板对象
    @Autowired
    private SolrTemplate solrTemplate;

    @JmsListener(destination = "add_update_del_topic")
    public void addIndexGenHtml(String jsonStr){

        // 判断传递的值是否为空
        if(StringUtils.isNotBlank(jsonStr) && jsonStr.contains("add_update")){
            // 截取
            String[] str = jsonStr.split("=");
            // 转换
            List<TbItem> list = JSON.parseArray(str[1], TbItem.class);
            // 获取map接口key
            // 添加,修改
            if(list != null && list.size()>0){
                // 同步索引库
                solrTemplate.saveBeans(list);
                // 提交
                solrTemplate.commit();
            }
        }else {
            // 删除操作
            // 把ids转换为集合
            List<String> list = JSON.parseArray(jsonStr, String.class);
            // 删除
            solrTemplate.deleteById(list);
            // 提交
            solrTemplate.commit();
        }
    }




}
