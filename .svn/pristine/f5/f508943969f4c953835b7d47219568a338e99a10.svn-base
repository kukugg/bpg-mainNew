package com.bpg.lr.login.model;

import java.util.Date;
import java.util.List;

public class SysRole {
    private Long roleId;

    private String roleName;

    private String remark;

    private Long deptId;

    private Date createTime;
    
    private List<SysMenu> sysMenus;  // 多权限
    
    private List<SysUser> userInfos; //多用户  
    
    private List<Long> menuIdList; //权限菜单id列表
    

    public List<Long> getMenuIdList() {
		return menuIdList;
	}

	public void setMenuIdList(List<Long> menuIdList) {
		this.menuIdList = menuIdList;
	}

	public List<SysMenu> getSysMenus() {
		return sysMenus;
	}

	public void setSysMenus(List<SysMenu> sysMenus) {
		this.sysMenus = sysMenus;
	}

	public List<SysUser> getUserInfos() {
		return userInfos;
	}

	public void setUserInfos(List<SysUser> userInfos) {
		this.userInfos = userInfos;
	}

	public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	@Override
	public String toString() {
		return "SysRole [roleId=" + roleId + ", roleName=" + roleName + ", remark=" + remark + ", deptId=" + deptId
				+ ", createTime=" + createTime +",menuIdList=" + menuIdList+ "]";
	}
    
}