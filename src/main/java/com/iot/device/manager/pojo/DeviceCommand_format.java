package com.iot.device.manager.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iot.base.pojo.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhengnaishan
 * @date 2019/4/17 0017
 * @describe :以键值对方式显示到前端页面
 */
public class DeviceCommand_format {
   public class Entity {
        private String key;
        private Object value;
        public Entity(String key , Object value){
            this.key = key;
            this.value = value;
        }

       public String getKey() {
           return key;
       }

       public void setKey(String key) {
           this.key = key;
       }

       public Object getValue() {
           return value;
       }

       public void setValue(Object value) {
           this.value = value;
       }
   }

    private User createBy;
    private List<Entity> params = new ArrayList<Entity>();
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    private int status;
    public User getCreateBy() {
        return createBy;
    }

    public void setCreateBy(User createBy) {
        this.createBy = createBy;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Entity> getParams() {
        return params;
    }

    public void setParams(List<Entity> params) {
        this.params = params;
    }

    public void addEntity(Entity entity){
        this.params.add(entity);
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
