package com.douchai.system.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.douchai.common.utils.RedisKeyUtil;
import com.douchai.system.domin.SysMovieArea;
import com.douchai.system.domin.SysMovieCategory;
import com.douchai.system.mapper.SysMovieCategoryMapper;
import com.douchai.system.service.SysMovieCategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysMovieCategoryServiceImpl implements SysMovieCategoryService {

    @Autowired
    private SysMovieCategoryMapper sysMovieCategoryMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public List<SysMovieCategory> findAll() {
        String movieCategory = redisTemplate.opsForValue().get(RedisKeyUtil.getMovieCategoryKey());
        if(!StringUtils.isBlank(movieCategory)){
            //缓存存在，直接返回
            JSONArray list = JSONArray.parseArray(movieCategory);
            return list.toJavaList(SysMovieCategory.class);
        }
        List<SysMovieCategory> movieCategoryList = sysMovieCategoryMapper.findAll();
        String jsonString = JSONObject.toJSONString(movieCategoryList);
        redisTemplate.opsForValue().set(RedisKeyUtil.getMovieCategoryKey(),jsonString);
        return movieCategoryList;
    }

    @Override
    public SysMovieCategory findById(Long id) {
        return sysMovieCategoryMapper.findById(id);
    }

    @Override
    public int add(SysMovieCategory sysMovieCategory) {
        return sysMovieCategoryMapper.add(sysMovieCategory);
    }

    @Override
    public int update(SysMovieCategory sysMovieCategory) {
        int update = sysMovieCategoryMapper.update(sysMovieCategory);
        redisTemplate.delete(RedisKeyUtil.getMovieCategoryKey());
        return update;
    }

    @Override
    public int delete(Long[] ids) {
        int rows = 0;
        for (Long id : ids) {
            rows += sysMovieCategoryMapper.delete(id);
        }
        return rows;
    }
}
