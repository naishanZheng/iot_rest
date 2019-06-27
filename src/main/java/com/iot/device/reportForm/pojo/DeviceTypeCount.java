package com.iot.device.reportForm.pojo;

/**
 * @author zhengnaishan
 * @date 2019/4/15 0015
 * @describe :设备类型以及对应的个数
 */
public class DeviceTypeCount {
    private int count;//设备个数
    private String name;//设备类型名

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
