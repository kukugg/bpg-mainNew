package com.bpg.lr.baseDao;

import java.util.List;
import java.util.Map;


/**
 * @Title:BaseDao
 * @Description: BSAEDAO	
 * @Author hjp	
 * @Date 2018年7月4日
 */
public abstract interface BaseDao<T> {
	public abstract int deleteByPrimaryKey(Map<String, Object> params);

	public abstract int insert(T record);

    public abstract int insertSelective(T record);

    public abstract T selectByPrimaryKey(Map<String, Object> params);

    public abstract int updateByPrimaryKeySelective(T record);

    public abstract int updateByPrimaryKey(T record);
    
    public abstract List<T> getAll();
}
