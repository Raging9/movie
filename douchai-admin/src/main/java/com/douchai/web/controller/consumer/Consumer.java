package com.douchai.web.controller.consumer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.douchai.common.exception.DataNotFoundException;
import com.douchai.common.utils.KafkaTopics;
import com.douchai.system.domin.SysMovie;
import com.douchai.system.domin.SysMovieComment;
import com.douchai.system.domin.SysSession;
import com.douchai.system.domin.vo.SysBillVo;
import com.douchai.system.service.SysMovieService;
import com.douchai.system.service.SysSessionService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer implements KafkaTopics {

    @Autowired
    private SysMovieService sysMovieService;

    @Autowired
    private SysSessionService sysSessionService;

    @KafkaListener(topics = TOPIC_BOX_OFFICE)
    public void updateBoxOffice(ConsumerRecord<String, Object> record){
        JSONObject msg = JSONObject.parseObject(record.value().toString());
        SysSession curSession = msg.getObject("curSession", SysSession.class);
        SysMovie movie = sysMovieService.findOne(curSession.getMovieId());
        Double movieBoxOffice = movie.getMovieBoxOffice();
        String seats = msg.getString("seats");
        //订单的座位数
        int seatNum = seats.split(",").length;
        movieBoxOffice = movieBoxOffice + curSession.getSessionPrice() * seatNum;
        movie.setMovieBoxOffice(movieBoxOffice);
        sysMovieService.update(movie);
    }


    @KafkaListener(topics = TOPIC_SESSION_SEAT)
    public void updateSeat(ConsumerRecord<String, Object> record){
        JSONObject msg = JSONObject.parseObject(record.value().toString());
        SysSession curSession = msg.getObject("curSession", SysSession.class);
        if(curSession == null){
            throw new DataNotFoundException("添加订单的场次没找到");
        }
        SysBillVo sysBillVo = msg.getObject("sysBillVo", SysBillVo.class);
        //当前场次座位json信息
        String sessionSeats = curSession.getSessionSeats();
        //订单的座位字符串
        String seats = sysBillVo.getSysBill().getSeats();
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
            arr.set(Integer.parseInt(column)-1,3);
            jsonObject.put(row,arr);
        }
        curSession.setSessionSeats(jsonObject.toJSONString());
        sysSessionService.update(curSession);
    }

    @KafkaListener(topics = TOPIC_ADD_COMMENT)
    public void addComment(ConsumerRecord<String, Object> record){
        SysMovieComment sysMovieComment = JSONObject.parseObject(record.value().toString(), SysMovieComment.class);

        SysMovie movie = sysMovieService.findById(sysMovieComment.getMovieId());
        Integer movieRateNum = movie.getMovieRateNum();
        Double movieScore = movie.getMovieScore();
        movieScore = (movieScore * movieRateNum + sysMovieComment.getScore()) / (++movieRateNum);

        movie.setMovieScore(movieScore);
        movie.setMovieRateNum(movieRateNum);
        sysMovieService.update(movie);
    }

    @KafkaListener(topics = TOPIC_UPDATE_COMMENT)
    public void updateComment(ConsumerRecord<String, Object> record){
        JSONObject msg = JSONObject.parseObject(record.value().toString());
        SysMovieComment originalComment = msg.getObject("originalComment", SysMovieComment.class);
        SysMovieComment sysMovieComment = msg.getObject("sysMovieComment", SysMovieComment.class);
        //修改电影评分
        SysMovie movie = sysMovieService.findById(sysMovieComment.getMovieId());
        Integer movieRateNum = movie.getMovieRateNum();
        Double movieScore = movie.getMovieScore();
        movieScore = (movieScore * movieRateNum + sysMovieComment.getScore() - originalComment.getScore()) / movieRateNum;
        //更新评分
        movie.setMovieScore(movieScore);
        sysMovieService.update(movie);
    }
}
