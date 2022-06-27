package com.douchai.web.controller.system;

import com.douchai.web.controller.BaseController;
import com.douchai.common.response.ResponseResult;
import com.douchai.system.domin.SysHallCategory;
import com.douchai.system.service.impl.SysHallCategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
public class SysHallCategoryController extends BaseController {
    @Autowired
    private SysHallCategoryServiceImpl sysHallCategoryService;

    @GetMapping("/sysHallCategory")
    public ResponseResult findAll(){
        startPage();
        List<SysHallCategory> data = sysHallCategoryService.findAll();
        return getResult(data);
    }

    @GetMapping("/sysHallCategory/{id}")
    public ResponseResult findById(@PathVariable Long id){
        return getResult(sysHallCategoryService.findById(id));
    }

    @PostMapping("/sysHallCategory")
    public ResponseResult add(@Validated @RequestBody SysHallCategory sysHallCategory){
        return getResult(sysHallCategoryService.add(sysHallCategory));
    }


    @PutMapping("/sysHallCategory")
    public ResponseResult update(@Validated @RequestBody SysHallCategory sysHallCategory){
        return getResult(sysHallCategoryService.update(sysHallCategory));
    }

    @DeleteMapping("/sysHallCategory/{ids}")
    public ResponseResult delete(@PathVariable Long[] ids){
        return getResult(sysHallCategoryService.delete(ids));
    }
}
