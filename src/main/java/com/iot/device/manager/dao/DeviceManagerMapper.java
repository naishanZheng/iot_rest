package com.iot.device.manager.dao;

import com.iot.base.dao.IotMapper;
import com.iot.base.dao.PageMapper;
import com.iot.device.manager.pojo.Device;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**  


* @author zns  

* @date 2018年5月31日  

* @version 1.0  
     终端dao层
*/  
public interface DeviceManagerMapper extends IotMapper<Device> {
  //根据用户id查询所有设备
  public List<Device> getAllDevices(String userId);
  //根据条件 查询设备 
  public List<Device> queryDevices(@Param("userId") String userId, @Param("device") Device device);
  //根据序列号查询设备具体信息
  public Device getDeviceDetailedInfor(@Param("devSerial") String devSerial);

  //获取设备基本信息
  public Device getDeviceBaseInfo(@Param("devSerial") String devSerial);


  //更新设备基本配置
  public void updateDeviceBaseInfo(@Param("device") Device device);
  

  //更新设备状态
  public void updateDeviceStatus(@Param("devSerial") String devSerial, @Param("status") String status);

  //清空设备的预警信息
  public void cleanWarningInfos(String devSerial);

  //获取设备的基础信息
  public Device getDeviceDetails_baseInfo(@Param("devSerial") String devSerial);
  //查询指定个数预警详情
  //public List<WarningInfo> getDeviceDetails_warningsInfo(@Param("devSerial") String devSerial, @Param("count") long count);

  int getInlineCount(Device device);
  int getOffLineCount(Device device);

  void updateStatus(Device device);
}
