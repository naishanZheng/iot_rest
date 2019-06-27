package com.iot.base.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhengnaishan
 * @date 2019/3/8 0008
 * @describe :角色里用到的菜单目录
 */
public class Role_Menu {
    private String title;//目录名
    private String value;//目录id
    private Boolean checked;//是否选中
    private List<Role_Menu> data = new ArrayList<Role_Menu>();//子节点

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public List<Role_Menu> getData() {
        return data;
    }

    public void setData(List<Role_Menu> data) {
        this.data = data;
    }
}
