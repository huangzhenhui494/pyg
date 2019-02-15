package com.pyg.solr.utils;

import com.alibaba.fastjson.JSON;
import com.pyg.mapper.TbItemMapper;
import com.pyg.pojo.TbItem;
import com.pyg.pojo.TbItemExample;
import com.pyg.pojo.TbItemExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SolrUtils {

    // 注入商品mapper接口代理对象
    @Autowired
    private TbItemMapper tbItemMapper;

    // 注入solr模板对象
    @Autowired
    private SolrTemplate solrTemplate;

    /**
     * 需求:查询数据库数据,导入到索引库
     * @param
     */
    public void importData(){
        // 创建example对象
        TbItemExample example = new TbItemExample();
        // 设置参数:必须是,已审核
        Criteria createCriteria = example.createCriteria();
        // 设置查询参数
        createCriteria.andStatusEqualTo("1");
        // 查询数据库
        List<TbItem> list = tbItemMapper.selectByExample(example);

        // 导入规格数据
        // 循环集合获取规格
        for (TbItem tbItem : list) {
            String spec = tbItem.getSpec();
            // 转换成map对象
            Map<String,String> specMaps  = (Map<String, String>) JSON.parse(spec);
            // 把值添加到动态域
            tbItem.setSpecMap(specMaps);
        }

        // 添加索引库
        solrTemplate.saveBeans(list);
        // 提交
        solrTemplate.commit();

    }
    // java - jar xx.jar
    public static void main(String[] args) {
        // 加载spring配置文件
        ApplicationContext app = new ClassPathXmlApplicationContext("classpath*:spring/*.xml");
        // 获取SolrUtils对象
        SolrUtils solrUtils = app.getBean(SolrUtils.class);
        // 调用导入索引库的方法
        solrUtils.importData();
    }
}
