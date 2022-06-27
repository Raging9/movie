package com.douchai.web.controller.system;

import com.douchai.web.controller.BaseController;
import com.douchai.common.response.ResponseResult;
import com.douchai.system.domin.SysCinema;
import com.douchai.system.domin.SysSession;
import com.douchai.system.domin.vo.SysCinemaVo;
import com.douchai.system.service.impl.SysCinemaServiceImpl;
import com.douchai.system.service.impl.SysSessionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
public class SysCinemaController extends BaseController {

    @Autowired
    private SysCinemaServiceImpl sysCinemaService;

    @Autowired
    private SysSessionServiceImpl sysSessionService;

    @GetMapping("/sysCinema")
    public ResponseResult findAll(SysCinemaVo sysCinemaVo){
        startPage();
        return getResult(sysCinemaService.findAll(sysCinemaVo));
    }

    @GetMapping("/sysCinema/{id}")
    public ResponseResult findById(@PathVariable Long id){
        return getResult(sysCinemaService.findById(id));
    }

    @PostMapping("/sysCinema")
    public ResponseResult add(@Validated @RequestBody SysCinema sysCinema){
        return getResult(sysCinemaService.add(sysCinema));
    }

    @PutMapping("/sysCinema")
    public ResponseResult update(@Validated @RequestBody SysCinema sysCinema){
        return getResult(sysCinemaService.update(sysCinema));
    }

    @DeleteMapping("/sysCinema/{ids}")
    public ResponseResult delete(@PathVariable Long[] ids){
        return getResult(sysCinemaService.delete(ids));
    }

    @GetMapping(value = {"/sysCinema/find/{cinemaId}/{movieId}", "/sysCinema/find/{cinemaId}"})
    public ResponseResult findCinemaById(@PathVariable Long cinemaId, @PathVariable(required = false) Long movieId){
        SysCinema cinema = sysCinemaService.findCinemaById(cinemaId);
        if(movieId == null || movieId == 0){
            movieId = cinema.getSysMovieList().size() > 0 ? cinema.getSysMovieList().get(0).getMovieId() : 0;
        }
        List<SysSession> sessions = null;
        if(movieId != null && movieId != 0){
            sessions = sysSessionService.findByCinemaAndMovie(cinemaId, movieId);
        }

        HashMap<String, Object> response = new HashMap<>();
        response.put("cinema", cinema);
        response.put("sessions", sessions);
        return getResult(response);

    }

}
