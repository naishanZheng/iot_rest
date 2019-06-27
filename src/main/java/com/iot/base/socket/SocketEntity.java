package com.iot.base.socket;

import java.io.Serializable;

/**
 * @author zhengnaishan
 * @date 2019/4/22 0022
 * @describe :
 */
public class SocketEntity implements Serializable {
    private String id;
    private String name;
    private static final long longserialVersionUID = 1L;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
