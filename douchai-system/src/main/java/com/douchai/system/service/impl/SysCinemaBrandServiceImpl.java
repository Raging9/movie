package com.douchai.system.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.douchai.common.utils.RedisKeyUtil;
import com.douchai.system.domin.SysCinemaBrand;
import com.douchai.system.domin.SysHallCategory;
import com.douchai.system.mapper.SysCinemaBrandMapper;
import com.douchai.system.service.SysCinemaBrandService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysCinemaBrandServiceImpl implements SysCinemaBrandService {

    @Autowired
    private SysCinemaBrandMapper sysCinemaBrandMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public List<SysCinemaBrand> findAll() {
        String data = redisTemplate.opsForValue().get(RedisKeyUtil.getCinemaBrandKey());
        if(!StringUtils.isBlank(data)){
            //缓存存在，直接返回
            JSONArray list = JSONArray.parseArray(data);
            return list.toJavaList(SysCinemaBrand.class);
        }
        List<SysCinemaBrand> list = sysCinemaBrandMapper.findAll();
        String jsonString = JSONObject.toJSONString(list);
        redisTemplate.opsForValue().set(RedisKeyUtil.getCinemaBrandKey(),jsonString);
        return list;
    }

    @Override
    public SysCinemaBrand findById(Long id) {
        return sysCinemaBrandMapper.findById(id);
    }

    @Override
    public int add(SysCinemaBrand sysCinemaBrand) {
        return sysCinemaBrandMapper.add(sysCinemaBrand);
    }

    @Override
    public int update(SysCinemaBrand sysCinemaBrand) {
        int update = sysCinemaBrandMapper.update(sysCinemaBrand);
        redisTemplate.delete(RedisKeyUtil.getCinemaBrandKey());
        return update;
    }

    @Override
    public int delete(Long[] ids) {
        int rows = 0;
        for(Long id : ids){
            rows += sysCinemaBrandMapper.delete(id);
        }
        return rows;
    }
}
