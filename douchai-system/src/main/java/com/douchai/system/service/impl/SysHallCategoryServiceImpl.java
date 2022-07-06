package com.douchai.system.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.douchai.common.utils.RedisKeyUtil;
import com.douchai.system.domin.SysHallCategory;
import com.douchai.system.domin.SysMovieAge;
import com.douchai.system.mapper.SysHallCategoryMapper;
import com.douchai.system.service.SysHallCategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysHallCategoryServiceImpl implements SysHallCategoryService {

    @Autowired
    private SysHallCategoryMapper sysHallCategoryMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public List<SysHallCategory> findAll() {
        String data = redisTemplate.opsForValue().get(RedisKeyUtil.getHallCategoryKey());
        if(!StringUtils.isBlank(data)){
            //缓存存在，直接返回
            JSONArray list = JSONArray.parseArray(data);
            return list.toJavaList(SysHallCategory.class);
        }
        List<SysHallCategory> list = sysHallCategoryMapper.findAll();
        String jsonString = JSONObject.toJSONString(list);
        redisTemplate.opsForValue().set(RedisKeyUtil.getHallCategoryKey(),jsonString);
        return list;
    }

    @Override
    public SysHallCategory findById(Long id) {
        return sysHallCategoryMapper.findById(id);
    }

    @Override
    public int add(SysHallCategory sysHallCategory) {
        return sysHallCategoryMapper.add(sysHallCategory);
    }

    @Override
    public int update(SysHallCategory sysHallCategory) {
        int update = sysHallCategoryMapper.update(sysHallCategory);
        redisTemplate.delete(RedisKeyUtil.getHallCategoryKey());
        return update;
    }

    @Override
    public int delete(Long[] ids) {
        int rows = 0;
        for (Long id : ids) {
            rows += sysHallCategoryMapper.delete(id);
        }
        return rows;
    }
}
