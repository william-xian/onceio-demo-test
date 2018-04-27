package cn.xian.app.holder;

import cn.xian.app.model.entity.Wallet;
import top.onceio.core.aop.annotation.Transactional;
import top.onceio.core.db.dao.DaoHolder;
import top.onceio.core.db.dao.tpl.UpdateTpl;
import top.onceio.core.exception.Failed;
import top.onceio.core.mvc.annocations.AutoApi;

@AutoApi(Wallet.class)
public class WalletHolder extends DaoHolder<Wallet> {
	
	@Transactional
	public void transfer(Long from,Long to,Integer v) {
		UpdateTpl<Wallet> subtpl = new UpdateTpl<>(Wallet.class);
		subtpl.sub().setBalance(v);
		subtpl.set().setId(from);
		int cnt = updateByTpl(subtpl);
		if(cnt != 1) {
			Failed.throwMsg("Wallet Id：%d is not found", from);
		}
		UpdateTpl<Wallet> addtpl = new UpdateTpl<>(Wallet.class);
		addtpl.add().setBalance(v);
		addtpl.set().setId(to);
		cnt = updateByTpl(addtpl);
		if(cnt != 1) {
			Failed.throwMsg("Wallet Id: %d is not found", from);
		}
	}
}
