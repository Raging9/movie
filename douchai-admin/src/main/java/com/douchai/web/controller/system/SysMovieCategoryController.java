package com.douchai.web.controller.system;

import com.douchai.web.controller.BaseController;
import com.douchai.common.response.ResponseResult;
import com.douchai.system.domin.SysMovieArea;
import com.douchai.system.domin.SysMovieCategory;
import com.douchai.system.service.impl.SysMovieCategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class SysMovieCategoryController extends BaseController {

    @Autowired
    private SysMovieCategoryServiceImpl sysMovieCategoryService;

    @GetMapping("/sysMovieCategory")
    public ResponseResult findAll(){
        startPage();
        List<SysMovieCategory> data = sysMovieCategoryService.findAll();
        return getResult(data);
    }

    @GetMapping("/sysMovieCategory/{id}")
    public ResponseResult findById(@PathVariable Long id){
        return getResult(sysMovieCategoryService.findById(id));
    }

    @PostMapping("/sysMovieCategory")
    public ResponseResult add(@Validated @RequestBody SysMovieCategory sysMovieCategory){
        return getResult(sysMovieCategoryService.add(sysMovieCategory));
    }


    @PutMapping("/sysMovieCategory")
    public ResponseResult update(@Validated @RequestBody SysMovieCategory sysMovieCategory){
        return getResult(sysMovieCategoryService.update(sysMovieCategory));
    }

    @DeleteMapping("/sysMovieCategory/{ids}")
    public ResponseResult delete(@PathVariable Long[] ids){
        return getResult(sysMovieCategoryService.delete(ids));
    }
}
