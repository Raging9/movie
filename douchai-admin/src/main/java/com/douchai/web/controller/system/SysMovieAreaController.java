package com.douchai.web.controller.system;

import com.douchai.web.controller.BaseController;
import com.douchai.common.response.ResponseResult;
import com.douchai.system.domin.SysMovieAge;
import com.douchai.system.domin.SysMovieArea;
import com.douchai.system.service.SysMovieAreaService;
import com.douchai.system.service.impl.SysMovieAgeServiceImpl;
import com.douchai.system.service.impl.SysMovieAreaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class SysMovieAreaController extends BaseController {
    @Autowired
    SysMovieAreaServiceImpl sysMovieAreaService;

    @GetMapping("/sysMovieArea")
    public ResponseResult findAll(){
        startPage();
        List<SysMovieArea> data = sysMovieAreaService.findAll();
        return getResult(data);
    }

    @GetMapping("/sysMovieArea/{id}")
    public ResponseResult findById(@PathVariable Long id){
        return getResult(sysMovieAreaService.findById(id));
    }

    @PostMapping("/sysMovieArea")
    public ResponseResult add(@Validated @RequestBody SysMovieArea sysMovieArea){
        return getResult(sysMovieAreaService.add(sysMovieArea));
    }


    @PutMapping("/sysMovieArea")
    public ResponseResult update(@Validated @RequestBody SysMovieArea sysMovieArea){
        return getResult(sysMovieAreaService.update(sysMovieArea));
    }

    @DeleteMapping("/sysMovieArea/{ids}")
    public ResponseResult delete(@PathVariable Long[] ids){
        return getResult(sysMovieAreaService.delete(ids));
    }
}
