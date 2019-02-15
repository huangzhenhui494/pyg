package com.pyg.solrj;

import com.pyg.pojo.TbItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/applicationContext-solr.xml")
public class MySpringDataSolr {

    // 注入solr模板对象
    @Autowired
    private SolrTemplate solrTemplate;

    // 测试
    // 向索引库添加数据
    @Test
    public void addIndex(){
        // 创建商品对象
        TbItem item = new TbItem();
        //
        item.setId(100000000L);
        item.setGoodsId(101001100L);
        item.setTitle("锤子手机99T内存 3英寸");
        item.setPrice(new BigDecimal(10000));
        item.setBrand("锤子");
        item.setCategory("手机");


        // 写入数据到索引库
        solrTemplate.saveBean(item);
        // 提交
        solrTemplate.commit();
    }

    // 测试
    // 根据id查询
    @Test
    public void findIndexById(){

        TbItem item = solrTemplate.getById(100000000, TbItem.class);
        System.out.println(item);
    }

    // 测试
    // 分页查询
    @Test
    public void findIndexByPage(){

        // 创建solrQuery对象封装条件
        Query query = new SimpleQuery("*:*");
        // 设置分页条件
        // 设置分页查询的起始位置
        query.setOffset(0);
        // 设置每页显示的条数
        query.setRows(10);

        // 执行查询
        ScoredPage<TbItem> page = solrTemplate.queryForPage(query, TbItem.class);
        // 获取分页记录
        List<TbItem> list = page.getContent();
        // 获取总记录数
        long totalElements = page.getTotalElements();
        System.out.println(totalElements+"==="+list);

    }

    // 测试
    // 条件查询
    @Test
    public void findIndexByCriteria(){
        Query query = new SimpleQuery("*:*");
        // 创建条件查询对象
        Criteria criteria = new Criteria("item_keywords").is("锤子");
        criteria.contains("16");
        // 把条件添加查询对象
        query.addCriteria(criteria);
        ScoredPage<TbItem> tbItems = solrTemplate.queryForPage(query, TbItem.class);
        // 获取记录
        List<TbItem> list = tbItems.getContent();
        System.out.println(list);
    }


    // 测试
    // 删除索引
    @Test
    public void deleteIndex(){

        // 刪除所有
        Query query = new SimpleQuery("*:*");
        solrTemplate.delete(query);

        // 根据id删除索引库
//        solrTemplate.deleteById(100000000L+"");
        solrTemplate.commit();
    }

    // 测试
    // 高亮查询
    @Test
    public void findSolrIndexWithHighlight(){
        // 创建高亮查询对象
        SimpleHighlightQuery query = new SimpleHighlightQuery();
        // 条件查询
        Criteria criteria = new Criteria("item_title").is("锤子");
        // and条件
        criteria.contains("内存");
        // 把条件添加到查询对象中
        query.addCriteria(criteria);

        // 创建高亮对象,添加高亮操作
        HighlightOptions highlightOptions = new HighlightOptions();
        highlightOptions.addField("item_title");
        highlightOptions.setSimplePrefix("<font color='red'>");
        highlightOptions.setSimplePostfix("</font>");

        // 设置高亮查询
        query.setHighlightOptions(highlightOptions);

        // 执行查询
        HighlightPage<TbItem> highlightPage = solrTemplate.queryForHighlightPage(query, TbItem.class);

        // 获取查询总记录数
        long totalElements = highlightPage.getTotalElements();
        System.out.println(totalElements);
        // 获取总记录
        List<TbItem> list = highlightPage.getContent();

        // 循环集合对象
        for (TbItem tbItem : list) {
            // 获取高亮
            List<HighlightEntry.Highlight> highlights = highlightPage.getHighlights(tbItem);
            HighlightEntry.Highlight highlight = highlights.get(0);
            List<String> snipplets = highlight.getSnipplets();
            System.out.println("高亮字段"+snipplets);

        }
    }


}
