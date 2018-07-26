package com.bpg.lr.serviceImpl.login;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpg.lr.login.dao.SysLogDao;
import com.bpg.lr.login.model.SysLog;
import com.bpg.lr.service.login.SysLogService;

/**
 * 系统日志处理
 * @author hanjp
 *
 */
@Service
public class SysLogServiceImpl implements SysLogService {

	@Autowired
	private SysLogDao sysLogDao;
	
	@Override
	public void deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int insert(SysLog record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(SysLog record) {
		sysLogDao.insertSelective(record);
		return 0;
	}

	@Override
	public SysLog selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(SysLog record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(SysLog record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<SysLog> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
