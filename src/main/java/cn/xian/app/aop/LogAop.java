package cn.xian.app.aop;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import net.sf.cglib.proxy.MethodProxy;
import top.onceio.aop.ProxyAction;
import top.onceio.aop.annotation.Aop;
import top.onceio.util.OUtils;

@Aop(pattern = ".*",order="1")
public class LogAop extends ProxyAction{
	
	private static final Logger LOGGER = Logger.getLogger(LogAop.class);

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		LOGGER.debug(String.format("%s.%s(%s)", obj.getClass().getName(), method.getName(), OUtils.toJSON(args)));
		if (next() != null) {
			return next().intercept(obj, method, args, proxy);
		} else {
			return proxy.invokeSuper(obj, args);
		}
	}
	
}
