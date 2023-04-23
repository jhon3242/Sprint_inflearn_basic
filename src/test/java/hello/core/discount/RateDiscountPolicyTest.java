package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

	DiscountPolicy discountPolicy = new RateDiscountPolicy();

	@Test
	@DisplayName("VIP 멤버는 10% 할인 정책이 적용된다")
	void vip_o() {
		// given
		Member member = new Member(1L, "memberA", Grade.VIP);

		// when
		int discount = discountPolicy.discount(member, 10000);

		// then
		assertThat(discount).isEqualTo(1000);
	}

	@Test
	@DisplayName("VIP가 아닌 멤버는 10% 할인 정책이 적용된다")
	void vip_x() {
		// given
		Member member = new Member(1L, "memberA", Grade.BASIC);

		// when
		int discount = discountPolicy.discount(member, 10000);

		// then
		assertThat(discount).isEqualTo(0);
	}
}