package com.fqt.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fqt.project.entity.Log;
import com.fqt.project.mapper.LogMapper;
import com.fqt.project.service.LogService;

/**
 * 日志服务层
 * @author eye
 *
 */
@Service("logService")
public class LogServiceImpl implements LogService {
	
	@Autowired
	private LogMapper logMapper;

	@Override
	public void insertLog(Log log) {
		// TODO Auto-generated method stub
		logMapper.insertLog(log);
	}

}
