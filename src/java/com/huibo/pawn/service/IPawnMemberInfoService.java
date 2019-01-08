package com.huibo.pawn.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bn.frame.util.CommonFunction;
import com.bn.javax.dao.Page;
import com.huibo.pawn.bo.ChannelBo;
import com.huibo.pawn.bo.IPawnMemberInfoBo;
import com.huibo.pawn.dao.IPawnMemberInfoDao;
import com.huibo.pawn.po.IPawnMemberInfoPo;
/**
 * 
* <p>Title: 广丰共享典当管理系统 - IPawnMemberInfoService</p>
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

@Service
public class IPawnMemberInfoService {

	@Autowired
	private IPawnMemberInfoDao iPawnMemberInfoDao;
	
	/**
	 * 加载数据网格及搜索
	 * @param po
	 * @return
	 */
	public Map<String, Object> vipSearch(IPawnMemberInfoBo po) {
		Map<String,Object> map = new HashMap<String,Object>();
		Page page = new Page(po);
		map.put("rows", iPawnMemberInfoDao.vipSearch(page));
		map.put("total", page.getTotalRecord());
		return map;
	}

	/**
	 * 新增会员
	 * @param bo
	 * @return
	 */
	public Integer vipSave(IPawnMemberInfoBo bo) {
		//注入修改人
		bo.setModifyBy(CommonFunction.getUserFromSession().getUserId());
		//注入创建人
		bo.setCreateBy(CommonFunction.getUserFromSession().getUserId());
		return iPawnMemberInfoDao.vipSave(bo);
	}

	/**
	 * 删除会员
	 * @param dels
	 * @return
	 */
	public Integer delVip(String[] dels) {
		return iPawnMemberInfoDao.delVip(dels);
	}

	/**
	 * 修改填坑
	 * @param bo
	 * @return
	 */
	public List<IPawnMemberInfoPo> modifyVip(IPawnMemberInfoBo bo) {
		return iPawnMemberInfoDao.modifyVip(bo);
	}

	/**
	 * 修改会员信息
	 * @param bo
	 * @return
	 */
	public Integer modifyVipSave(IPawnMemberInfoBo bo) {
		//注入修改人
		bo.setModifyBy(CommonFunction.getUserFromSession().getUserId());
		return iPawnMemberInfoDao.modifyVipSave(bo);
	}

	/**
	 * 充值
	 * @param bo
	 * @return
	 */
	public Integer rechargeVipSave(IPawnMemberInfoBo bo) {
		return iPawnMemberInfoDao.rechargeVipSave(bo);
	}

	/**
	 * 会员序号非重复验证
	 * @param bo
	 * @return
	 */
	public Map<String, Object> serialNumberValidation(IPawnMemberInfoBo bo) {
		int  serialNumber = 0;
		Map<String,Object> map = new HashMap<>();
		Integer count = iPawnMemberInfoDao.serialNumberValidation(bo);
		if(count == 0) {
			serialNumber = 1;
		}
		map.put("resultCode", serialNumber);
		return map;
	}

	/**
	 * 修改状态
	 * @param bo
	 * @return
	 */
	public Integer forbidden(IPawnMemberInfoBo bo) {
		return iPawnMemberInfoDao.forbidden(bo);
	}

	/**
	 * 查询所有渠道
	 * @param bo
	 * @return
	 */
	public List<ChannelBo> getChannel(ChannelBo bo) {
		
		return iPawnMemberInfoDao.getChannel(bo);
	}
}
