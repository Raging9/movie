package com.douchai.system.mapper;

import com.douchai.system.domin.LoginUser;
import com.douchai.system.domin.SysUser;
import com.douchai.system.domin.vo.SysUserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface SysUserMapper {
    List<SysUser> findAll(SysUser sysUser);

    SysUser findById(Long id);

    SysUser findByName(String userName);

    int add(SysUser sysUser);

    int update(SysUser sysUser);

    int delete(Long id);

    LoginUser findLoginUser(SysUserVo sysUserVo);

    /**
     * 查出指定名字用户的所有id
     * @param userName
     * @return
     */
    List<Long> findUsersByName(String userName);
}
