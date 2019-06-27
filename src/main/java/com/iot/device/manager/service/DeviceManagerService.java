package com.iot.device.manager.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iot.base.pojo.BaseEntity;
import com.iot.base.result.IotResult;
import com.iot.base.service.BaseService;
import com.iot.base.service.IotService;
import com.iot.base.service.PageService;
import com.iot.base.utils.HttpClientUtil;
import com.iot.base.utils.JsonUtils;
import com.iot.device.manager.dao.DeviceManagerMapper;
import com.iot.device.manager.pojo.Device;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class DeviceManagerService extends IotService<DeviceManagerMapper,Device> {
	   @Autowired
	   private DeviceManagerMapper deviceManagerMapper;

	/**
	 * 根据lastMessage做判断，比lastMessage晚的则在线
	 * @param device
	 * @return
	 */
	public int getInlineCount(Device device){
    	return dao.getInlineCount(device);
	  }

	public int getOffLineCount(Device device){
		return dao.getOffLineCount(device);
	}

	public IotResult iotRegister(Device device){
		//jiayingdev01 jsj2018
        device = super.get(device);
		//登录参数
		String loginJson = "{\"serverId\":\""+"jiayingdev01"+"\",\"password\":\""+"jsj2018"+"\"}";
		//登录
		String json = HttpClientUtil.doPostJson("https://www.easy-iot.cn/idev/3rdcap/server/login", loginJson);
		Map loginResult = JsonUtils.jsonToPojo(json, Map.class);
		//获取登录token
		String accessToken = (String) loginResult.get("accessToken");

		Map headerMap = new HashMap<String, String>();
		headerMap.put("serverID", "jiayingdev01");
		headerMap.put("accessToken", accessToken);

		Map body = new HashMap();
		body.put("devSerial" , device.getDevSerial());
		body.put("name",device.getName());
		body.put("deviceType",device.getDeviceType().getEname());
		body.put("connectPointId", device.getConnectPointId());
		body.put("location",device.getLocation());
		String response = HttpClientUtil.doPostJsonWithHeader("https://www.easy-iot.cn/idev/3rdcap/devices/reg-device",
				JsonUtils.objectToJson(body), headerMap );
		Map responseMap = JsonUtils.jsonToPojo(response, Map.class);
		if(!"0".equals(responseMap.get("optResult"))){
			//响应码不为200
			return IotResult.build(501,"设备注册失败");
		}else {
			return IotResult.ok();
		}
	}



	/**
	 * 删除iot平台设备
	 * @param device
	 * @return
	 */
	public IotResult iotDelete(Device device){
	 return null;
	}

	/**
	 * 更改设备状态
	 */
	@Transactional(readOnly = false)
	public void updateStatus(Device device){
		dao.updateStatus(device);
	}
}
