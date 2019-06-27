package com.iot.product.smartTerminal.service;

import com.iot.base.service.PageService;
import com.iot.product.smartTerminal.dao.WarningInfoMapper;
import com.iot.product.smartTerminal.pojo.WarningInfo;
import org.springframework.stereotype.Service;

/**
 * @author zhengnaishan
 * @date 2019/4/10 0010
 * @describe :预警信息service
 */
@Service
public class WarningInfoService extends PageService<WarningInfo, WarningInfoMapper> {
}
