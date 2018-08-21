package top.onceio.db;

import top.onceio.core.beans.BeansEden;
import top.onceio.core.db.dao.impl.DaoHelper;
import top.onceio.core.db.jdbc.JdbcHelper;
import top.onceio.core.db.tbl.OI18n;

public class DaoBaseTest {
	protected static JdbcHelper jdbcHelper = new JdbcHelper();

	protected static DaoHelper daoHelper = new DaoHelper();
	
	public static void initDao() {
		BeansEden.get().resovle(new String[] {}, new String[] {"cn.xian.app"});
		jdbcHelper = BeansEden.get().load(JdbcHelper.class);
		daoHelper = BeansEden.get().load(DaoHelper.class);
	}
	
	//@Test
	public void createTbl() {
		initDao();
		System.out.println(OI18n.class);
	}
	
}
