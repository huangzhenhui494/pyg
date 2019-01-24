package com.pyg.portal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pyg.content.service.ContentService;
import com.pyg.pojo.TbContent;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ad")
public class AdController {

    // 注入广告服务对象
    @Reference(timeout = 1000000)
    private ContentService contentService;

    /**
     * 需求:根据分类id查询不同区域的广告内容信息
     * 参数:Long categoryId
     * 业务思想:
     * 页面广告被分为不同的类型,不同类型的广告通过不同的分类id进行加载
     * 大广告:1
     * 今日推荐:2
     * 返回值:List<TbContent>
     * 查询业务:
     * 1.查询有效的广告 status 为 1
     * 2.广告排序问题
     */
    @RequestMapping("/findContentListByCategoryId")
    public List<TbContent> findContentListByCategoryId(Long categoryId){
        // 调用远程服务方法
        return contentService.findContentListByCategoryId(categoryId);
    }

}
