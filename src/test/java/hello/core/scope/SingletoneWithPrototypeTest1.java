package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletoneWithPrototypeTest1 {

	@Test
	@DisplayName("프로토타입 2개 호출")
	void prototypeFind(){
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
		PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
		prototypeBean1.addCount();
		assertThat(prototypeBean1.getCount()).isEqualTo(1);

		PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
		prototypeBean2.addCount();
		assertThat(prototypeBean2.getCount()).isEqualTo(1);
	}

	@Test
	@DisplayName("싱글톤이 프로토타입을 호출시")
	void singletonClientUsePrototype(){
		AnnotationConfigApplicationContext ac =
				new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBean.class);
		ClientBean clientBean1 = ac.getBean(ClientBean.class);
		int count1 = clientBean1.logic();
		assertThat(count1).isEqualTo(1);

		ClientBean clientBean2 = ac.getBean(ClientBean.class);
		int count2 = clientBean2.logic();
		assertThat(count2).isEqualTo(1);
	}

	@Scope("singleton")
	static class ClientBean{
//		private final PrototypeBean prototypeBean; // 생성 시점에 주입
//
//		@Autowired // 생략 가능
//		public ClientBean(PrototypeBean prototypeBean) {
//			this.prototypeBean = prototypeBean;
//		}

		@Autowired
		private Provider<PrototypeBean> prototypeBeanProvider;


		public int logic(){
			PrototypeBean prototypeBean = prototypeBeanProvider.get();
			prototypeBean.addCount();
			int count = prototypeBean.getCount();
			return count;
		}
	}

	@Scope("prototype")
	static class PrototypeBean{
		private int count;

		public void addCount(){
			count += 1;
		}

		public int getCount() {
			return count;
		}

		@PostConstruct
		public void init(){
			System.out.println("PrototypeBean.init " + this);
		}

		@PreDestroy
		public void close(){
			System.out.println("PrototypeBean.close " + this);
		}
	}
}
