package com.huibo.pawn.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.huibo.pawn.bo.ChannelBo;
import com.huibo.pawn.bo.IPawnMemberInfoBo;
import com.huibo.pawn.po.IPawnMemberInfoPo;
import com.huibo.pawn.service.IPawnMemberInfoService;

/**
 * 
 * <p>Title: 广丰共享典当管理系统 - IPawnMemberInfoController</p>
 *
 * <p>Description:会员档案的Controller</p>
 *
 * <p>Copyright: Copyright bnkj(c) 2016</p>
 *
 * <p>Company: 重庆汇博有限公司</p>
 *
 * @author 彭忠义 
 * @version 1.0
 */

@Controller
public class IPawnMemberInfoController {

	@Autowired
	private IPawnMemberInfoService iPawnMemberInfoService;
	
	/**
	 * 加载数据网格及条件搜索
	 */
	@RequestMapping("/vipSearch")
	public Map<String,Object> vipSearch(IPawnMemberInfoBo po){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			map = iPawnMemberInfoService.vipSearch(po);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(map);
		return map;
	}
	
	/**
	 * 新增会员
	 */
	@RequestMapping("/vipSave")
	public Map<String,Object> vipSave(IPawnMemberInfoBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", iPawnMemberInfoService.vipSave(bo)) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 删除会员
	 */
	@RequestMapping("/delVip")
	public Map<String,Object> delVip(@RequestParam("dels[]") String[] dels){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", iPawnMemberInfoService.delVip(dels));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(dels);
		return map;
	}
	
	/**
	 * 修改填坑
	 */
	@RequestMapping("/modifyVip")
	public Map<String,Object> modifyVip(IPawnMemberInfoBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", iPawnMemberInfoService.modifyVip(bo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(map);
		return map;
	}
	
	/**
	 * 修改会员信息
	 */
	@RequestMapping("/modifyVipSave")
	public Map<String,Object> modifyVipSave(IPawnMemberInfoBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", iPawnMemberInfoService.modifyVipSave(bo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 充值
	 */
	@RequestMapping("/rechargeVipSave")
	public Map<String,Object> rechargeVipSave(IPawnMemberInfoBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", iPawnMemberInfoService.rechargeVipSave(bo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 会员序号非重复验证
	 */
	@RequestMapping("/serialNumberValidation")
	public Map<String,Object> serialNumberValidation(IPawnMemberInfoBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map = iPawnMemberInfoService.serialNumberValidation(bo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 修改状态
	 */
	@RequestMapping("/forbidden")
	public Map<String,Object> forbidden(IPawnMemberInfoBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", iPawnMemberInfoService.forbidden(bo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 查询所有的渠道
	 */
	@RequestMapping("/getChannel")
	public Map<String,Object> getChannel(ChannelBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", iPawnMemberInfoService.getChannel(bo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
