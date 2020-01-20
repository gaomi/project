package com.company.project.module.sys.dao;

import com.company.project.core.Mapper;
import com.company.project.module.sys.model.SysUser;
import com.company.project.module.sys.model.dto.SysUserRoleDto;

import java.util.List;
import java.util.Map;

public interface SysUserMapper extends Mapper<SysUser> {
    List<SysUser> findUserWithDept(Map map);

    List<SysUserRoleDto> findUserWithRole(String id);

    SysUser findUserProfile(SysUser user);

    Boolean updateDisabled(SysUser adminUser);

    /**
     * 手机端修改密码
     *
     * @param param
     */
    void updateUserPassword(Map param);

/*
    @Select("SELECT * FROM users")
    @Results({
            @Result(property = "userSex",  column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nickName", column = "nick_name")
    })
    List<SysUser> getAll();

    @Select("SELECT * FROM users WHERE id = #{id}")
    @Results({
            @Result(property = "userSex",  column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nickName", column = "nick_name")
    })
    SysUser getOne(Long id);

    @Insert("INSERT INTO users(userName,passWord,user_sex) VALUES(#{userName}, #{passWord}, #{userSex})")
    void insert(SysUser user);

    @Update("UPDATE users SET userName=#{userName},nick_name=#{nickName} WHERE id =#{id}")
    void update(SysUser user);

    @Delete("DELETE FROM users WHERE id =#{id}")
    void delete(Long id);*/
}