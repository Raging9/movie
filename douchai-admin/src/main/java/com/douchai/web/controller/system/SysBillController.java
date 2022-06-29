package com.douchai.web.controller.system;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class SysBillController extends BaseController {
    
    @Autowired
    private SysBillServiceImpl sysBillService;

    @Autowired
    private SysSessionServiceImpl sysSessionService;

    @Autowired
    private SysMovieServiceImpl sysMovieService;

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
            //更新场次的座位状态
            SysSession curSession = sysSessionService.findOne(sysBillVo.getSysBill().getSessionId());
            if(curSession == null){
                throw new DataNotFoundException("添加订单的场次没找到");
            }
            //当前场次座位json信息
            String sessionSeats = curSession.getSessionSeats();
            //订单的座位字符串
            String seats = sysBillVo.getSysBill().getSeats();
            //订单的座位数
            int seatNum = seats.split(",").length;
            //订单座位json数组
            JSONArray seatsArr = JSONArray.parseArray(seats);
            //场次的座位json对象
            JSONObject jsonObject = JSONObject.parseObject(sessionSeats);
            for(int i = 0;i<seatsArr.size();i++){
                String oneSeat = seatsArr.getString(i);
                String row = oneSeat.substring(0,oneSeat.indexOf("排"));
                String column = oneSeat.substring(oneSeat.indexOf("排")+1,oneSeat.indexOf("座"));
                JSONArray arr = jsonObject.getJSONArray(row);
                //已售出
                arr.set(Integer.parseInt(column),3);
                jsonObject.put(row,arr);
            }
            curSession.setSessionSeats(jsonObject.toJSONString());
            sysSessionService.update(curSession);

            //更新电影票房
            SysMovie curMovie = sysMovieService.findOne(curSession.getMovieId());
            if(curMovie == null){
                throw new DataNotFoundException("添加订单的电影没找到");
            }
            double price = curSession.getSessionPrice();
            curMovie.setMovieBoxOffice(curMovie.getMovieBoxOffice() + seatNum * price);
            sysMovieService.update(curMovie);

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
