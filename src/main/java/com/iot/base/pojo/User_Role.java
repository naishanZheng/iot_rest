package com.iot.base.pojo;

/**
 * @author zhengnaishan
 * @date 2019/3/10 0010
 * @describe :用户与角色关联
 */
public class User_Role {
    private String roleId;//角色id
    private String RoleName;//角色名
    private boolean checked;//是否选中

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String roleName) {
        RoleName = roleName;
    }

    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
