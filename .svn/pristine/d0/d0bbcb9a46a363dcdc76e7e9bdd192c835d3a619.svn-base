package com.bpg.lr.service.baseService;

import java.util.List;


/**
 * @Title:BaseService
 * @Description:基础service接口	
 * @Author hjp	
 * @Date 2018年7月4日
 */
public abstract interface BaseService<T> {
	
	public void deleteByPrimaryKey(Long id) ;
	
	public abstract int insert(T record);

    public abstract int insertSelective(T record);

    public abstract T selectByPrimaryKey(Long id);

    public abstract int updateByPrimaryKeySelective(T record);

    public abstract int updateByPrimaryKey(T record);
    
    public abstract List<T> getAll();
	

}
