package com.douchai.system.service.impl;

import com.douchai.system.domin.SysSession;
import com.douchai.system.domin.vo.SysSessionVo;
import com.douchai.system.mapper.SysSessionMapper;
import com.douchai.system.service.SysSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class SysSessionServiceImpl implements SysSessionService {

    @Autowired
    private SysSessionMapper sysSessionMapper;

    @Override
    public List<SysSession> findByVo(SysSessionVo sysSessionVo) {
        return sysSessionMapper.findByVo(sysSessionVo);
    }

    @Override
    public SysSession findById(Long id) {
        return sysSessionMapper.findById(id);
    }

    @Override
    public SysSession findOne(Long id){
        return sysSessionMapper.findOne(id);
    }

    @Override
    public int add(SysSession sysSession) {
        return sysSessionMapper.add(sysSession);
    }

    @Override
    public int update(SysSession sysSession) {
        return sysSessionMapper.update(sysSession);
    }

    @Override
    public int delete(Long[] ids) {
        int rows = 0;
        for(Long id : ids){
            rows += sysSessionMapper.delete(id);
        }
        return rows;
    }

    @Override
    public List<SysSession> findByCinemaAndMovie(Long cinemaId, Long movieId) {
        List<SysSession> byCinemaAndMovie = sysSessionMapper.findByCinemaAndMovie(cinemaId, movieId);
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH)+1;
        int day = now.get(Calendar.DAY_OF_MONTH);
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        List<SysSession> sessionToday = byCinemaAndMovie.stream()
                .filter(e -> e.getSessionDate().equals(LocalDate.of(year,month,day)))
                .filter(e -> Integer.parseInt(e.getSysMovieRuntime().getBeginTime().substring(0,2))>hour||(Integer.parseInt(e.getSysMovieRuntime().getBeginTime().substring(0,2))==hour&&Integer.parseInt(e.getSysMovieRuntime().getBeginTime().substring(3,5))>=minute))
                .collect(Collectors.toList());
        List<SysSession> sessionAfter = byCinemaAndMovie.stream().filter(e -> e.getSessionDate().isAfter(LocalDate.of(year, month, day))).collect(Collectors.toList());
        List<SysSession> totalSession = Stream.of(sessionToday, sessionAfter).flatMap(Collection::stream).collect(Collectors.toList());
        return totalSession;
    }
}
