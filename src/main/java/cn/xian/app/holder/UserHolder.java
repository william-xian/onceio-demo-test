package cn.xian.app.holder;

import cn.xian.app.model.entity.UserInfo;
import top.onceio.aop.annotation.Cacheable;
import top.onceio.db.dao.Cnd;
import top.onceio.db.dao.DaoHolder;
import top.onceio.mvc.annocations.Api;
import top.onceio.mvc.annocations.AutoApi;
import top.onceio.mvc.annocations.Param;

@AutoApi(UserInfo.class)
@Cacheable
public class UserHolder extends DaoHolder<UserInfo> {
	@Cacheable
	@Api
	public UserInfo fetchByName(@Param("name") String name) {
		Cnd<UserInfo> cnd = new Cnd<>(UserInfo.class);
		cnd.eq().setName(name);
		return super.fetch(null,cnd);
	}
}
