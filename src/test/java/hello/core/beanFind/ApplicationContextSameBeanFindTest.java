package hello.core.beanFind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberReposiroty;
import hello.core.member.MemberService;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindTest {
	// AppConfig.class 가 아님
	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);


	@Test
	@DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면 중복 오류가 발생한다")
	void findBeanByTypeDuplicate() {

		assertThrows(NoUniqueBeanDefinitionException.class,
				() -> ac.getBean(MemberReposiroty.class)
		);
	}

	@Test
	@DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름으로 지정하면 된다.")
	void findByBeanName() {
		Object memberRepository1 = ac.getBean("memberRepository1", MemberReposiroty.class);
		assertThat(memberRepository1).isInstanceOf(MemberReposiroty.class);
	}

	@Test
	@DisplayName("특정 타입 모두 조회하기.")
	void findAllBeanByType() {
		Map<String, MemberReposiroty> beansOfType = ac.getBeansOfType(MemberReposiroty.class);
		for (String key : beansOfType.keySet()) {
			System.out.println("key = " + key + " value : " + beansOfType.get(key));
		}
		System.out.println("beansOfType = " + beansOfType);
		assertThat(beansOfType.size()).isEqualTo(2);
	}


	/**
	 * 중복으로 테스트 하려면 AppConfig 를 손대야 하는데 그 작업은 하기 싫으니까 여기 안에다가 새롭게 config 파일을 만들자
	 * 참고로 클래스 안에 static을 사용하면 클래스 안에서만 쓰겠다는 의미
	 */
	@Configuration
	static class SameBeanConfig {

		@Bean
		public MemberReposiroty memberRepository1() {
			return new MemoryMemberRepository();
		}

		@Bean
		public MemberReposiroty memberRepository2() {
			return new MemoryMemberRepository();
		}
	}
}
