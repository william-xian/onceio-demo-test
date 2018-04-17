package cn.xian.app.logic;

import java.util.HashMap;
import java.util.Map;

import cn.xian.app.holder.UserHolder;
import cn.xian.app.holder.WalletHolder;
import cn.xian.app.model.entity.UserInfo;
import top.onceio.aop.annotation.Transactional;
import top.onceio.db.dao.Cnd;
import top.onceio.db.dao.Page;
import top.onceio.exception.Failed;
import top.onceio.mvc.annocations.Def;
import top.onceio.mvc.annocations.Using;

@Def
public class UserLogic {

	@Using
	private UserHolder userHolder;
	@Using
	private WalletHolder walletHolder;
	
	public UserInfo signup(String account,String passwd) {
		UserInfo entity = new UserInfo();
		entity.setName(account);
		entity.setPasswd(passwd);
		entity = userHolder.insert(entity);
		return entity;
	}
	
	public boolean signin(String account,String passwd) {
		UserInfo uc = userHolder.fetchByName(account);
		if(uc == null) {
			Failed.throwMsg("用户%s不存在", account);
		}
		if(uc.getPasswd() != null  && uc.getPasswd().equals(passwd)) {
			return true;
		}
		return false;
	}
	
	public Page<UserInfo> find(UserInfo uc) {
		System.out.println("find:" + uc);
		Page<UserInfo> ucs = userHolder.find(new Cnd<UserInfo>(UserInfo.class));
		return ucs;
	}
	
	@Transactional
	public Map<String,Object> transfer(Long from,Long to,Integer v) {
		Map<String,Object> result = new HashMap<>();
		result.put("before-a", walletHolder.get(from));
		result.put("before-b", walletHolder.get(to));
		walletHolder.transfer(from, to, v);
		result.put("after-a", walletHolder.get(from));
		result.put("after-b", walletHolder.get(to));
		return result;
	}
}
