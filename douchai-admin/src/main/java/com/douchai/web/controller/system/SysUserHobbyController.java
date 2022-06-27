package com.douchai.web.controller.system;

import com.douchai.web.controller.BaseController;
import com.douchai.common.response.ResponseResult;
import com.douchai.system.domin.SysUserHobby;
import com.douchai.system.service.impl.SysUserHobbyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class SysUserHobbyController extends BaseController {

    @Autowired
    SysUserHobbyServiceImpl sysUserHobbyService;

    @GetMapping("/sysUserHobby")
    public ResponseResult findAll(){
        startPage();
        List<SysUserHobby> data = sysUserHobbyService.findAll();
        return getResult(data);
    }

    @GetMapping("/sysUserHobby/{id}")
    public ResponseResult findById(@PathVariable Long id){
        return getResult(sysUserHobbyService.findById(id));
    }

    @PostMapping("/sysUserHobby")
    public ResponseResult add(@Validated @RequestBody SysUserHobby sysUserHobby){
        return getResult(sysUserHobbyService.add(sysUserHobby));
    }

    @PutMapping("/sysUserHobby")
    public ResponseResult update(@Validated @RequestBody SysUserHobby sysUserHobby){
        return getResult(sysUserHobbyService.update(sysUserHobby));
    }

    @DeleteMapping("/sysUserHobby/{ids}")
    public ResponseResult delete(@PathVariable Long[] ids){
        return getResult(sysUserHobbyService.delete(ids));
    }

}
