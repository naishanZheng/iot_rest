package com.iot.device.manager.pojo;

import com.iot.base.pojo.IotEntity;

/**
 * @author zhengnaishan
 * @date 2019/4/6 0003
 * @describe :设备pojo
 */

public class Device extends IotEntity {
  private String devType;//产品型号
  private String endUserInfo; //例：13670852182
  private String optResult;   //0
  private String isPublished; //0
  private String latitude;    //24.405173
  private String endUserName; //zengjy
  private String lastMessageTime = "";  //2018-05-16 19:29:42
  private String protocolType;   //CoAP
  private String hasSimCard;   //1
  private String devSerial;   //序列号863703036590433
  private String serviceMode; //PSM
  private String name;      //设备名称
  private String location; //
  private String connectPointId; //连接端id ctc-nanjing-iot-137
  private String longitude;    //116.596684
  private DeviceType deviceType;//设备类型
  private Integer status;//设备状态
	public String getDevType() {
	return devType;
}
public void setDevType(String devType) {
	this.devType = devType;
}
public String getEndUserInfo() {
	return endUserInfo;
}
public void setEndUserInfo(String endUserInfo) {
	this.endUserInfo = endUserInfo;
}
public String getOptResult() {
	return optResult;
}
public void setOptResult(String optResult) {
	this.optResult = optResult;
}
public String getIsPublished() {
	return isPublished;
}
public void setIsPublished(String isPublished) {
	this.isPublished = isPublished;
}
public String getLatitude() {
	return latitude;
}
public void setLatitude(String latitude) {
	this.latitude = latitude;
}
public String getEndUserName() {
	return endUserName;
}
public void setEndUserName(String endUserName) {
	this.endUserName = endUserName;
}
public String getLastMessageTime() {
	return lastMessageTime;
}
public void setLastMessageTime(String lastMessageTime) {
	this.lastMessageTime = lastMessageTime;
}
public String getProtocolType() {
	return protocolType;
}
public void setProtocolType(String protocolType) {
	this.protocolType = protocolType;
}
public String getHasSimCard() {
	return hasSimCard;
}
public void setHasSimCard(String hasSimCard) {
	this.hasSimCard = hasSimCard;
}
public String getDevSerial() {
	return devSerial;
}
public void setDevSerial(String devSerial) {
	this.devSerial = devSerial;
}
public String getServiceMode() {
	return serviceMode;
}
public void setServiceMode(String serviceMode) {
	this.serviceMode = serviceMode;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getLocation() {
	return location;
}
public void setLocation(String location) {
	this.location = location;
}
public String getConnectPointId() {
	return connectPointId;
}
public void setConnectPointId(String connectPointId) {
	this.connectPointId = connectPointId;
}
public String getLongitude() {
	return longitude;
}
public void setLongitude(String longitude) {
	this.longitude = longitude;
}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
