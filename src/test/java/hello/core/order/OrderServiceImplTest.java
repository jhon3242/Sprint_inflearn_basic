package hello.core.order;

import hello.core.discount.FIxDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberReposiroty;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

	/*@Test
	void createOrder(){
		MemoryMemberRepository memberReposiroty = new MemoryMemberRepository();
		memberReposiroty.save(new Member(1L, "memberA", Grade.VIP));
		
		OrderServiceImpl orderService = new OrderServiceImpl(memberReposiroty, new FIxDiscountPolicy());
		Order order = orderService.createOrder(1L, "iteamA", 1000);
		Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
	}*/

}