package com.douchai.system.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.douchai.common.utils.RedisKeyUtil;
import com.douchai.system.domin.SysMovieAge;
import com.douchai.system.mapper.SysMovieAgeMapper;
import com.douchai.system.service.SysMovieAgeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysMovieAgeServiceImpl implements SysMovieAgeService {

    @Autowired
    SysMovieAgeMapper sysMovieAgeMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public List<SysMovieAge> findAll() {
        String movieAge = redisTemplate.opsForValue().get(RedisKeyUtil.getMovieAgeKey());
        if(!StringUtils.isBlank(movieAge)){
            //缓存存在，直接返回
            JSONArray list = JSONArray.parseArray(movieAge);
            return list.toJavaList(SysMovieAge.class);
        }
        List<SysMovieAge> movieAgeList = sysMovieAgeMapper.findAll();
        String jsonString = JSONObject.toJSONString(movieAgeList);
        redisTemplate.opsForValue().set(RedisKeyUtil.getMovieAgeKey(),jsonString);
        return movieAgeList;
    }

    @Override
    public SysMovieAge findById(Long id) {
        return sysMovieAgeMapper.findById(id);
    }

    @Override
    public int add(SysMovieAge sysMovieAge) {
        return sysMovieAgeMapper.add(sysMovieAge);
    }

    @Override
    public int update(SysMovieAge sysMovieAge) {
        int update = sysMovieAgeMapper.update(sysMovieAge);
        redisTemplate.delete(RedisKeyUtil.getMovieAgeKey());
        return update;
    }

    @Override
    public int delete(Long[] ids) {
        int rows = 0;
        for(Long id : ids)
            rows += sysMovieAgeMapper.delete(id);
        return rows;
    }
}
