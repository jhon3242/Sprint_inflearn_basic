package hello.core.signletone;

import hello.core.AppConfig;
import hello.core.member.MemberReposiroty;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

	@Test
	void configurationTest(){
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

		MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
		OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
		MemberReposiroty memberRepository = ac.getBean("memberRepository", MemberReposiroty.class);


		MemberReposiroty memberReposiroty1 = memberService.getMemberReposiroty();
		MemberReposiroty memberReposiroty2 = orderService.getMemberReposiroty();

		System.out.println("memberReposiroty1 = " + memberReposiroty1);
		System.out.println("memberReposiroty2 = " + memberReposiroty2);
		System.out.println("memberRepository = " + memberRepository);
		Assertions.assertThat(memberReposiroty1).isSameAs(memberReposiroty2);
	}

	@Test
	void configurationDeep(){
		 ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		AppConfig bean = ac.getBean(AppConfig.class);
		System.out.println("bean = " + bean.getClass()); // bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$9db1a227
	}
}
