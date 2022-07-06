package com.douchai.system.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.douchai.common.utils.RedisKeyUtil;
import com.douchai.system.domin.SysMovieAge;
import com.douchai.system.domin.SysMovieArea;
import com.douchai.system.mapper.SysMovieAreaMapper;
import com.douchai.system.service.SysMovieAreaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysMovieAreaServiceImpl implements SysMovieAreaService {
    @Autowired
    SysMovieAreaMapper sysMovieAreaMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public List<SysMovieArea> findAll() {
        String movieArea = redisTemplate.opsForValue().get(RedisKeyUtil.getMovieAreaKey());
        if(!StringUtils.isBlank(movieArea)){
            //缓存存在，直接返回
            JSONArray list = JSONArray.parseArray(movieArea);
            return list.toJavaList(SysMovieArea.class);
        }
        List<SysMovieArea> movieAreaList = sysMovieAreaMapper.findAll();
        String jsonString = JSONObject.toJSONString(movieAreaList);
        redisTemplate.opsForValue().set(RedisKeyUtil.getMovieAreaKey(),jsonString);
        return movieAreaList;
    }

    @Override
    public SysMovieArea findById(Long id) {
        return sysMovieAreaMapper.findById(id);
    }

    @Override
    public int add(SysMovieArea sysMovieArea) {
        return sysMovieAreaMapper.add(sysMovieArea);
    }

    @Override
    public int update(SysMovieArea sysMovieArea) {
        int update = sysMovieAreaMapper.update(sysMovieArea);
        redisTemplate.delete(RedisKeyUtil.getMovieAreaKey());
        return update;
    }

    @Override
    public int delete(Long[] ids) {
        int rows = 0;
        for (Long id : ids) {
            rows += sysMovieAreaMapper.delete(id);
        }
        return rows;
    }
}
