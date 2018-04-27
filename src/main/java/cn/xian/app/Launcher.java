package cn.xian.app;

import top.onceio.core.annotation.BeansIn;
import top.onceio.plugins.servlet.Webapp;


@BeansIn("cn.xian.app")
public class Launcher {
	
	public static void main(String[] args) {
		Webapp.run(Launcher.class, args);
	}
}
