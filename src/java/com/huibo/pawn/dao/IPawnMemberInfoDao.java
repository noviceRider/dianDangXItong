package com.huibo.pawn.dao;

import java.util.List;
import java.util.Map;

import com.bn.javax.dao.Page;
import com.huibo.pawn.bo.ChannelBo;
import com.huibo.pawn.bo.IPawnMemberInfoBo;
import com.huibo.pawn.po.IPawnMemberInfoPo;
/**
 * 
 * <p>Title: 广丰共享典当管理系统 - IPawnMemberInfoDao</p>
 *
 * <p>Description:会员档案的Dao</p>
 *
 * <p>Copyright: Copyright bnkj(c) 2016</p>
 *
 * <p>Company: 重庆汇博有限公司</p>
 *
 * @author 彭忠义 
 * @version 1.0
 */

public interface IPawnMemberInfoDao {

	//加载数据网格和搜索
	public List<IPawnMemberInfoPo> vipSearch(Page page);

	//新增会员
	public Integer vipSave(IPawnMemberInfoBo bo);

	//删除会员
	public Integer delVip(String[] dels);

	//修改填坑
	public List<IPawnMemberInfoPo> modifyVip(IPawnMemberInfoBo bo);

	//修改会员信息
	public Integer modifyVipSave(IPawnMemberInfoBo bo);

	//充值
	public Integer rechargeVipSave(IPawnMemberInfoBo bo);

	//会员序号非重复验证
	public Integer serialNumberValidation(IPawnMemberInfoBo bo);

	//修改状态
	public Integer forbidden(IPawnMemberInfoBo bo);

	//查询所有渠道
	public List<ChannelBo> getChannel(ChannelBo bo);

}
