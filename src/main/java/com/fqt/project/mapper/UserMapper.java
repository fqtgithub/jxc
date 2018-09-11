package com.fqt.project.mapper;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import com.fqt.project.entity.User;
/**
 * 用户持久层
 * @author eye
 *
 */
public interface UserMapper {

	@Select("select id,username,password from t_user where username=#{user.userName} and password=#{user.password}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="username",property="userName"),
		@Result(column="password",property="password"),
		@Result(column="id",property="roles",
		many=@Many(select="com.fqt.project.mapper.RoleMapper.getUserRoles",fetchType=FetchType.LAZY)
		)
	})
	User Login(User user);
}
