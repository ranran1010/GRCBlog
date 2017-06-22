package com.grc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.grc.entity.Blog;
import com.grc.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 14437 on 2017/6/21.
 */
@RestController
@EnableAutoConfiguration
public class BlogController {
    @Autowired
    BlogService blogService;

    @PostMapping(value = "/getUserBlogs")
    public Map<String,Object> getUserBlogs(@RequestParam("userId")Integer userId){
        String result = "";
        List<Blog> blogList = blogService.getUserBlog(userId);
        result = JSONArray.toJSONString(blogList);
        Map<String,Object> response = new HashMap<String, Object>();
        response.put("blogs",result);
        return response;
    }

    @PostMapping(value = "/getBlog")
    public Map<String,Object> getBlog(@RequestParam("blogId")Integer blogId){
        String result = "";
        Blog blog = blogService.getBlog(blogId);
        result = JSON.toJSONString(blog);
        Map<String,Object> response = new HashMap<String, Object>();
        response.put("blog",result);
        return response;
    }

    @PostMapping(value = "/addBlog")
    public Map<String,Object> addBlog(@RequestParam("userId")Integer userId,
                                      @RequestParam("title")String title,
                                      @RequestParam("blogContent")String blogContent,
                                      @RequestParam("classifyId")Integer classifyId,
                                      @RequestParam("ItClassifyId")Integer itClassifyId,
                                      @RequestParam("tags")String tags){
        Blog blog = new Blog();
        blog.setUserId(userId);
        blog.setTitle(title);
        blog.setBlogContent(blogContent);
        blog.setClassifyId(classifyId);
        blog.setItClassifyId(itClassifyId);
        blog.setTags(tags);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        blog.setPublishTime(timestamp);
        blogService.addBlog(blog);
        Map<String,Object> response = new HashMap<String, Object>();
        response.put("addResult","success");
        return response;
    }

    @PostMapping(value = "/updateBlog")
    public Map<String,Object> updateBlog(@RequestParam("userId")Integer blogId,
                                         @RequestParam("userId")Integer userId,
                                         @RequestParam("title")String title,
                                         @RequestParam("blogContent")String blogContent,
                                         @RequestParam("classifyId")Integer classifyId,
                                         @RequestParam("ItClassifyId")Integer itClassifyId,
                                         @RequestParam("tags")String tags){
        Blog blog = new Blog();
        blog.setBlogId(blogId);
        blog.setUserId(userId);
        blog.setTitle(title);
        blog.setBlogContent(blogContent);
        blog.setClassifyId(classifyId);
        blog.setItClassifyId(itClassifyId);
        blog.setTags(tags);
        Blog blog1 = blogService.getBlog(blogId);
        blog.setPublishTime(blog1.getPublishTime());
        blogService.addBlog(blog);
        Map<String,Object> response = new HashMap<String, Object>();
        response.put("updateResult","success");
        return response;
    }

    @PostMapping(value = "/deleteBlog")
    public Map<String,Object> deleteBlog(@RequestParam("blogId")Integer blogId){
        String result = "";
        blogService.deleteBlog(blogId);
        result = "success";
        Map<String,Object> response = new HashMap<String, Object>();
        response.put("deleteResult",result);
        return response;
    }

}
