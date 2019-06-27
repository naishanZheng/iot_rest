package com.iot.base.pojo;

import com.iot.base.dao.PageMapper;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhengnaishan
 * @date 2019/3/9 0009
 * @describe : 用户
 */
public class User extends IotEntity<User> {
    private String name;//用户名
    private String number;//工号
    private String loginName;//登录名
    private String loginPassword;//密码
    private String phone;//电话
    private String userType;//用户类型
    private String photo;//用户照片
    private String loginIp;//登录ip
    private String loginDate;//最后登录时间
    private String roleIds;//角色的id
    private List<String> permissions;//用户所有权限：用户->角色->菜单->权限标识
    private List<Menu> menus;//用户的所有菜单：排序好的
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
