package spring_0403.ex1;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

public class AdminConnection implements EnvironmentAware, InitializingBean, DisposableBean{
	private Environment env;
	private String adminId;
	private String adminPw;
	
	@Override
	public void setEnvironment(Environment env) {
		//implements EnvironmentAware를 하는 순간 이것이  initializingBean 보다 더 먼저 실행된다.
		//여기서 최초의 객체를 얻을 수 있다.
		System.out.println("setEnvironment()");
		setEnv(env);//내가 외부에 설정한 파일에 있는 동일한 값을 넣는다.
	}
	
	public void setEnv(Environment env) {
		this.env = env;
	}
	
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	
	public void setAdminPw(String adminPw) {
		this.adminPw = adminPw;
	}
	
	public String getAdminId() {
		return adminId;
	}
	
	public String getAdminPw() {
		return adminPw;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet()");
		setAdminId(env.getProperty("admin.id"));//여기다 set을 한다.
		setAdminPw(env.getProperty("admin.pw"));
	}

	@Override
	public void destroy() throws Exception {//close하면 이것이 실행된다.
		System.out.println("destroy()");
	}
}
