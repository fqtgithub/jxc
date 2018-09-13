package com.fqt.project.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.fqt.project.entity.Log;

/**
 * 日志持久层
 * @author eye
 *
 */
public interface LogMapper {

	/**
	 * 添加日志
	 * @param log
	 */
	@Insert("insert into t_log(content,time,type,userid) values(#{log.content},str_to_date(#{log.time},'%Y-%m-%d %H:%i:%s'),#{log.type},#{log.user.id})")
	void insertLog(@Param("log")Log log);
}
