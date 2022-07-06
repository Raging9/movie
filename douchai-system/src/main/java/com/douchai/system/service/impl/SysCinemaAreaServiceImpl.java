package com.douchai.system.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.douchai.common.utils.RedisKeyUtil;
import com.douchai.system.domin.SysCinemaArea;
import com.douchai.system.domin.SysCinemaBrand;
import com.douchai.system.mapper.SysCinemaAreaMapper;
import com.douchai.system.service.SysCinemaAreaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysCinemaAreaServiceImpl implements SysCinemaAreaService {

    @Autowired
    private SysCinemaAreaMapper sysCinemaAreaMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public List<SysCinemaArea> findAll() {
        String data = redisTemplate.opsForValue().get(RedisKeyUtil.getCinemaAreaKey());
        if(!StringUtils.isBlank(data)){
            //缓存存在，直接返回
            JSONArray list = JSONArray.parseArray(data);
            return list.toJavaList(SysCinemaArea.class);
        }
        List<SysCinemaArea> list = sysCinemaAreaMapper.findAll();
        String jsonString = JSONObject.toJSONString(list);
        redisTemplate.opsForValue().set(RedisKeyUtil.getCinemaAreaKey(),jsonString);
        return list;
    }

    @Override
    public SysCinemaArea findById(Long id) {
        return sysCinemaAreaMapper.findById(id);
    }

    @Override
    public int add(SysCinemaArea sysCinemaArea) {
        return sysCinemaAreaMapper.add(sysCinemaArea);
    }

    @Override
    public int update(SysCinemaArea sysCinemaArea) {
        int update = sysCinemaAreaMapper.update(sysCinemaArea);
        redisTemplate.delete(RedisKeyUtil.getCinemaAreaKey());
        return update;
    }

    @Override
    public int delete(Long[] ids) {
        int rows = 0;
        for(Long id : ids){
            rows += sysCinemaAreaMapper.delete(id);
        }
        return rows;
    }
}
