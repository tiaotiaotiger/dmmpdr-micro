package com.bocom.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bocom.dto.QueryServerDto;
import com.bocom.dto.QueryServerRespDto;
import com.bocom.entity.DataAreaType;
import com.bocom.entity.DataServer;
import com.bocom.enums.DataAreaTypeEnum;
import com.bocom.enums.DataMonitorCategoyEnum;
import com.bocom.enums.DataServerDataTypeEnum;
import com.bocom.enums.DataServerStatusEnum;
import com.bocom.enums.DataServerTypeEnum;
import com.bocom.service.DataAreaTypeService;
import com.bocom.service.DataMonitorService;
import com.bocom.service.DataServerService;
import com.bocom.util.ResponseUtil;

/**
 * ClassName:DmmpdrApiController
 * Function: 数据资源目录及元数据管理微服务
 * Date:     2017年3月6日 上午10:35:40
 * @author   chenzz
 * @since    JDK 1.7
 */
@RestController
@RequestMapping("/dmmpdr/api")
@SuppressWarnings("all")
public class DmmpdrApiController {
	private static Logger LOG = LoggerFactory
			.getLogger(DmmpdrApiController.class);
	@Autowired
	private DataServerService dataServerService;
	@Autowired
	private DataAreaTypeService dataAreaTypeService;
	@Autowired
	private DataMonitorService dataMonitorService;

	/**
	 * 添服务器信息
	 */
	@RequestMapping(value = "/server/addServer", method = { RequestMethod.POST })
	@ResponseBody
	public String addServer(@RequestBody List<DataServer> list,
			HttpSession session) {
		int[] returnVal = null;
		try {
			if (null != list && list.size() > 0) {
				Date date = new Date();
				for (DataServer server : list) {
					if (StringUtils.isNotEmpty(server.getDataType())) {
						server.setDataType(DataServerDataTypeEnum.MASTER
								.getCode() + "");
					}
					String areaTypeName = server.getAreaTypeName();
					String areaName = "";
					if (DataServerTypeEnum.APP.name().equals(areaTypeName)) {
						areaName = DataAreaTypeEnum.JWYYSJQ.getName();
					} else if (DataServerTypeEnum.SYS.name().equals(
							areaTypeName)) {
						areaName = DataAreaTypeEnum.JWYPTSJQ.getName();
					} else if (DataServerTypeEnum.BD.name()
							.equals(areaTypeName)) {
						areaName = DataAreaTypeEnum.JWYYSJQ.getName();
					}
					List<DataAreaType> areas = dataAreaTypeService
							.getByName(areaName);
					if (null != areas && areas.size() > 0) {
						server.setAreaType(areas.get(0).getId());
						server.setAreaTypeName(areas.get(0).getAreaName());
					}
					server.setStatus(DataServerStatusEnum.OFFLINE.getCode().toString());
					server.setCreateTime(date);
				}
				returnVal = dataServerService.addBatch(list);
			}
		} catch (Exception e) {
			LOG.error("DmmpdrApiController[addServer] ERROR", e);
			return ResponseUtil.fail(e.getMessage());
		}
		return ResponseUtil.success(returnVal);
	}

	/**
	 * 应用注册信息查询
	 */
	@PostMapping("/server/queryServer")
	@ResponseBody
	public String queryServer(@RequestBody QueryServerDto dto,
			HttpSession session) {
		List<QueryServerRespDto> dataList = null;
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			if (null != dto) {
				if (StringUtils.isNotEmpty(dto.getInstanceName())) {
					param.put("instanceName", dto.getInstanceName());
				}
			}
			List<DataServer> list = dataServerService.listDataByParam(param);
			if (null != list && list.size() > 0) {
				dataList = new ArrayList<QueryServerRespDto>();
				for (DataServer server : list) {
					QueryServerRespDto respData = new QueryServerRespDto();
					BeanUtils.copyProperties(server, respData);
					dataList.add(respData);
				}
			}
		} catch (Exception e) {
			LOG.error("DmmpdrApiController[queryServer] ERROR", e);
			return ResponseUtil.fail(e.getMessage());
		}
		return ResponseUtil.success(dataList);
	}

	/**
	 * 数据资源服务器更新表、数据量及字段
	 */
	@RequestMapping(value = "/server/syncTableNColumn", method = { RequestMethod.POST })
	@ResponseBody
	public String syncTableNColumn(String id, HttpSession session) {
		int result = 0;
		try {
			if (StringUtils.isNotEmpty(id)) {
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("id", id);
				List<DataServer> list = dataServerService
						.listDataByParam(param);
				dataMonitorService.updateTableNColumn(list);
				result = list.size();
			}
		} catch (Exception e) {
			LOG.error("DmmpdrApiController[syncTableNColumn] ERROR", e);
			return ResponseUtil.fail(e.getMessage());
		}
		return ResponseUtil.success(result);
	}

	/**
	 * 数据资源服务器状态
	 */
	@RequestMapping(value = "/server/syncServerStatus", method = { RequestMethod.POST })
	@ResponseBody
	public String syncServerStatus(String id, HttpSession session) {
		int result = 0;
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("id", id);
			List<DataServer> list = dataServerService.listDataByParam(param);
			Map<String, Object> ssParam = new HashMap<String, Object>();
			ssParam.put("category", DataMonitorCategoyEnum.AUTOMATIC.getCode());
			dataMonitorService.updateServerStatus(list, ssParam);
			result = list.size();
		} catch (Exception e) {
			LOG.error("DmmpdrApiController[syncServerStatus] ERROR", e);
			return ResponseUtil.fail(e.getMessage());
		}
		return ResponseUtil.success(result);
	}

	/**
	 * 数据表数据量更新
	 */
	@RequestMapping(value = "/server/syncTableCount", method = { RequestMethod.POST })
	@ResponseBody
	public String syncTableCount(String tableId, HttpSession session) {
		int result = 0;
		try {
			if (StringUtils.isNotEmpty(tableId)) {
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("tableId", tableId);
				param.put("category",
						DataMonitorCategoyEnum.AUTOMATIC.getCode());
				dataMonitorService.updateTableCount(param);
			}
		} catch (Exception e) {
			LOG.error("DmmpdrApiController[syncServerStatus] ERROR", e);
			return ResponseUtil.fail(e.getMessage());
		}
		return ResponseUtil.success(1);
	}
}
