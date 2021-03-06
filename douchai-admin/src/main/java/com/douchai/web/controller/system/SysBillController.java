package com.douchai.web.controller.system;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.douchai.common.utils.KafkaTopics;
import com.douchai.system.service.SysBillService;
import com.douchai.web.controller.BaseController;
import com.douchai.common.exception.DataNotFoundException;
import com.douchai.common.response.ResponseResult;
import com.douchai.system.domin.SysBill;
import com.douchai.system.domin.SysMovie;
import com.douchai.system.domin.SysSession;
import com.douchai.system.domin.vo.SysBillVo;
import com.douchai.system.service.impl.SysBillServiceImpl;
import com.douchai.system.service.impl.SysMovieServiceImpl;
import com.douchai.system.service.impl.SysSessionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class SysBillController extends BaseController {
    
    @Autowired
    private SysBillService sysBillService;

    @Autowired
    private SysSessionServiceImpl sysSessionService;

    @Autowired
    private SysMovieServiceImpl sysMovieService;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @GetMapping("/sysBill")
    public ResponseResult findAll(SysBill sysBill){
        startPage();
        List<SysBill> data = sysBillService.findAll(sysBill);
        return getResult(data);
    }

    @GetMapping("/sysBill/{id}")
    public ResponseResult findById(@PathVariable Long id){
        return getResult(sysBillService.findById(id));
    }

    @PostMapping("/sysBill")
    public ResponseResult add(@Validated @RequestBody SysBill sysBill){
        Object obj = sysBillService.add(sysBill);
        if(obj instanceof Integer){
            return getResult((Integer) obj);
        }
        return getResult(obj);
    }

    @PutMapping("/sysBill")
    public ResponseResult update(@RequestBody SysBillVo sysBillVo){
        int rows = sysBillService.update(sysBillVo.getSysBill());
        if(rows > 0 && sysBillVo.getSysBill().getBillState()){
            //????????????
            SysSession curSession = sysSessionService.findOne(sysBillVo.getSysBill().getSessionId());
            //??????????????????
            String seats = sysBillVo.getSysBill().getSeats();
            //???????????????????????????
            JSONObject msgSeat = new JSONObject();
            msgSeat.put("curSession",curSession);
            msgSeat.put("sysBillVo",sysBillVo);
            kafkaTemplate.send(KafkaTopics.TOPIC_SESSION_SEAT,msgSeat.toJSONString());
            //??????????????????
            JSONObject msgMoney = new JSONObject();
            msgMoney.put("curSession",curSession);
            msgMoney.put("seats",seats);
            kafkaTemplate.send(KafkaTopics.TOPIC_BOX_OFFICE,msgMoney.toJSONString());
        }
        return getResult(rows);
    }

    @DeleteMapping("/sysBill/{ids}")
    public ResponseResult delete(@PathVariable Long[] ids){
        return getResult(sysBillService.delete(ids));
    }

    @GetMapping("todayBoxOffice")
    public ResponseResult todayBoxOffice(){
        return getResult(sysBillService.todayBoxOffice());
    }
    
}
