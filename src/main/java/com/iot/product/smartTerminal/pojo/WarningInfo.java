package com.iot.product.smartTerminal.pojo;

import com.iot.base.pojo.PageEntity;

public class WarningInfo extends PageEntity<WarningInfo> {
 private String warningInfoId;
 private String warning_key;
 private String warning_msg;
 private String warning_time;
 private String devSerial;
public String getWarning_keys() {
	return warning_key;
}
public void setWarning_keys(String warning_keys) {
	this.warning_key = warning_keys;
}
public String getWarning_msg() {
	return warning_msg;
}
public void setWarning_msg(String warning_msg) {
	this.warning_msg = warning_msg;
}
public String getWarningInfoId() {
	return warningInfoId;
}
public void setWarningInfoId(String warningInfoId) {
	this.warningInfoId = warningInfoId;
}
public String getWarning_key() {
	return warning_key;
}
public void setWarning_key(String warning_key) {
	this.warning_key = warning_key;
}
public String getWarning_time() {
	return warning_time;
}
public void setWarning_time(String warning_time) {
	this.warning_time = warning_time;
}

	public String getDevSerial() {
		return devSerial;
	}

	public void setDevSerial(String devSerial) {
		this.devSerial = devSerial;
	}
}
