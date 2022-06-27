package com.douchai.web.controller.system;

import com.douchai.web.controller.BaseController;
import com.douchai.common.response.ResponseResult;
import com.douchai.system.domin.SysHall;
import com.douchai.system.domin.SysSession;
import com.douchai.system.domin.vo.SysSessionVo;
import com.douchai.system.service.impl.SysHallServiceImpl;
import com.douchai.system.service.impl.SysSessionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
public class SysHallController extends BaseController {

    @Autowired
    private SysHallServiceImpl sysHallService;

    @Autowired
    private SysSessionServiceImpl sysSessionService;

    @GetMapping("/sysHall")
    public ResponseResult findAll(SysHall sysHall){
        startPage();
        return getResult(sysHallService.findAll(sysHall));
    }

    @GetMapping("/sysHall/{cinemaId}/{hallId}")
    public ResponseResult findByPrimaryKey(@PathVariable Long cinemaId, @PathVariable Long hallId){
        SysHall sysHall = new SysHall();
        sysHall.setCinemaId(cinemaId);
        sysHall.setHallId(hallId);
        return getResult(sysHallService.findByCinemaIdAndHallId(sysHall));
    }

    @PostMapping("/sysHall")
    public ResponseResult add(@Validated @RequestBody SysHall sysHall){
        return getResult(sysHallService.add(sysHall));
    }

    @PutMapping("/sysHall")
    public ResponseResult update(@Validated @RequestBody SysHall sysHall){
        //查出原有影厅信息
        SysHall orgHall = sysHallService.findByCinemaIdAndHallId(sysHall);
        int rows = sysHallService.update(sysHall);
        if(rows > 0){
            //修改成功
            if(sysHall.getRowNums() != orgHall.getRowNums() || sysHall.getSeatNumsRow() != orgHall.getSeatNumsRow() || sysHall.getSeatNums() != orgHall.getSeatNums()) {
                //同步更新对应场次的座位
                SysSessionVo sysSessionVo = new SysSessionVo();
                sysSessionVo.setCinemaId(sysHall.getCinemaId());
                sysSessionVo.setHallId(sysHall.getHallId());
                //查出该影厅的所有场次
                List<SysSession> sessions = sysSessionService.findByVo(sysSessionVo);
                if (!CollectionUtils.isEmpty(sessions)) {
                    //存在场次则更新座位信息
                    for (SysSession session : sessions) {
                        session.setSessionSeats(sysHall.getSeatState());
                        sysSessionService.update(session);
                    }
                }
            }
        }
        return getResult(rows);
    }

    @PostMapping("/sysHall/delete")
    public ResponseResult delete(@RequestBody SysHall[] sysHalls){
        return getResult(sysHallService.delete(sysHalls));
    }

}
