package com.iot.base.pojo;


/**
 * @author zhengnaishan
 * @date 2019/3/8 0008
 * @describe :角色管理
 */
public class Role extends PageEntity<Role>{
    //private Office office;	// 归属机构
    private String name; 	// 角色名称
    private String ename;	// 英文名称
    private String roleType;// 权限类型
    private String dataScope;// 数据范围
    private String menuIds;//菜单id
    private String sysData; 		//是否是系统数据
   // private User user;		// 根据用户ID查询角色列表

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getDataScope() {
        return dataScope;
    }

    public void setDataScope(String dataScope) {
        this.dataScope = dataScope;
    }

    public String getSysData() {
        return sysData;
    }

    public void setSysData(String sysData) {
        this.sysData = sysData;
    }

    public String getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(String menuIds) {
        this.menuIds = menuIds;
    }
}
