package com.douchai.system.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.douchai.system.domin.LoginUser;
import com.douchai.system.domin.SysUser;
import com.douchai.system.domin.vo.SysUserVo;
import com.douchai.common.utils.JwtUtil;
import com.douchai.common.utils.SaltUtils;
import com.douchai.system.mapper.SysUserMapper;
import com.douchai.system.service.SysUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUser> findAll(SysUser sysUser) {
        return sysUserMapper.findAll(sysUser);
    }

    @Override
    public SysUser findById(Long id) {
        return sysUserMapper.findById(id);
    }

    @Override
    public SysUser findByName(String userName) {
        return sysUserMapper.findByName(userName);
    }

    /**
     * 处理注册逻辑
     * @param sysUser
     * @return
     */
    @Override
    public int add(SysUser sysUser) {
        if(!isUserNameUnique(sysUser.getUserName(), -1L)){
            throw new AuthenticationException("用户名重复");
        }
        //处理密码：md5 + salt + hash散列
        String salt = SaltUtils.getSalt(8);
        Md5Hash md5Hash = new Md5Hash(sysUser.getPassword(), salt, 1024);

        sysUser.setPassword(md5Hash.toHex());
        sysUser.setSalt(salt);
        JSONObject information = new JSONObject();
        information.put("birthday","2022-06-14T16:00:00.000Z");
        information.put("condition","1");
        JSONArray jobArr = new JSONArray();
        jobArr.add(1);
        jobArr.add(11);
        information.put("job",jobArr);
        JSONArray hobbiesArr = new JSONArray();
        hobbiesArr.add(11);
        information.put("hobbies",hobbiesArr);
        information.put("autograph","没什么说明");
        String jsonString = information.toJSONString();
        sysUser.setInformation(jsonString);
        JSONArray picArr = new JSONArray();
        picArr.add("2022/06/28/normal.png");
        sysUser.setUserPicture(picArr.toJSONString());
        return sysUserMapper.add(sysUser);
    }

    @Override
    public int update(SysUser sysUser) {
        if(!isUserNameUnique(sysUser.getUserName(), sysUser.getUserId())){
            throw new AuthenticationException("用户名重复");
        }
        SysUser originUser = sysUserMapper.findById(sysUser.getUserId());
        if(originUser == null){
            throw new AuthenticationException("用户不存在");
        }

        if(!originUser.getPassword().equals(sysUser.getPassword())){
            //修改了密码
            //重新处理密码存储
            String salt = SaltUtils.getSalt(8);
            Md5Hash md5Hash = new Md5Hash(sysUser.getPassword(), salt, 1024);

            sysUser.setPassword(md5Hash.toHex());
            sysUser.setSalt(salt);
        }
        return sysUserMapper.update(sysUser);
    }

    @Override
    public int delete(Long[] ids) {
        int rows = 0;
        for (Long id : ids) {
            rows += sysUserMapper.delete(id);
        }
        return rows;
    }

    @Override
    public LoginUser login(SysUserVo sysUserVo) {
        //登录，先查询用户信息
        SysUser user = sysUserMapper.findByName(sysUserVo.getUserName());
        if(user == null){
            throw new AuthenticationException("用户名不存在");
        }

        //验证密码
        Md5Hash md5Hash = new Md5Hash(sysUserVo.getPassword(), user.getSalt(), 1024);
        if(!user.getPassword().equals(md5Hash.toHex())){
            throw new AuthenticationException("用户名或密码错误");
        }

        //设置登录用户对象
        LoginUser loginUser = findLoginUser(sysUserVo);

        //颁发token
        String token = JwtUtil.sign(user.getUserName(), user.getPassword());
        loginUser.setToken(token);
        return loginUser;
    }


    @Override
    public LoginUser findLoginUser(SysUserVo sysUserVo) {
        return sysUserMapper.findLoginUser(sysUserVo);
    }

    @Override
    public boolean isUserNameUnique(String userName, Long userId) {
        List<Long> userIds = sysUserMapper.findUsersByName(userName);
        for(Long id : userIds){
            if(id.equals(userId)){
                return true;
            }
        }
        return userIds.isEmpty();
    }
}
