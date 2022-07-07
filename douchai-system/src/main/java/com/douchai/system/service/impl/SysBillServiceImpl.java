package com.douchai.system.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.douchai.system.domin.SysBill;
import com.douchai.system.domin.SysSession;
import com.douchai.system.mapper.SysBillMapper;
import com.douchai.system.mapper.SysSessionMapper;
import com.douchai.system.service.SysBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class SysBillServiceImpl implements SysBillService {

    @Autowired
    private SysBillMapper sysBillMapper;

    @Autowired
    private SysSessionMapper sysSessionMapper;

    private static final Object object= new Object();

    @Override
    public List<SysBill> findAll(SysBill sysBill) {
        return sysBillMapper.findAll(sysBill);
    }

    @Override
    public SysBill findById(Long id) {
        return sysBillMapper.findById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Object add(SysBill sysBill) {
        sysBill.setBillDate(new Date());
        int rows = sysBillMapper.add(sysBill);
        return rows > 0 ? sysBill : rows;
    }

    @Override
    public int update(SysBill sysBill) {
        SysSession session = sysSessionMapper.findById(sysBill.getSessionId());
        //场次的座位
        String sessionSeats = session.getSessionSeats();
        JSONObject sessionJsonObject = JSONObject.parseObject(sessionSeats);
        //订单的座位
        String seats = sysBill.getSeats();
        JSONArray jsonArray = JSONArray.parseArray(seats);
        for(int i =0;i<jsonArray.size();i++){
            String one = jsonArray.getString(i);
            String row = one.substring(0,one.indexOf("排"));
            String column = one.substring(one.indexOf("排")+1,one.indexOf("座"));
            JSONArray jsonArray1 = sessionJsonObject.getJSONArray(row);
            Integer state = jsonArray1.getInteger(Integer.valueOf(column)-1);
            if(state==3){
                throw new RuntimeException("座位已售！");
            }
        }
        return sysBillMapper.update(sysBill);
    }

    @Override
    public int delete(Long[] ids) {
        int rows = 0;
        for(Long id : ids){
            rows += sysBillMapper.delete(id);
        }
        return rows;
    }

    @Override
    public Double todayBoxOffice() {
        return sysBillMapper.todayBoxOffice();
    }
}
