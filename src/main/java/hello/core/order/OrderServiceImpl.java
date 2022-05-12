package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberReposiroty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

	private MemberReposiroty memberReposiroty;
	private DiscountPolicy discountPolicy;

	@Autowired
	public OrderServiceImpl(MemberReposiroty memberReposiroty, @MainDiscountPolicy  DiscountPolicy discountPolicy) {
		this.memberReposiroty = memberReposiroty;
		this.discountPolicy = discountPolicy;
	}

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		Member member = memberReposiroty.findbyID(memberId);
		int discountPrice = discountPolicy.discount(member, itemPrice);

		return new Order(memberId, itemName, itemPrice, discountPrice );
	}

	// 테스트 용도
	public MemberReposiroty getMemberReposiroty(){
		return memberReposiroty;
	}
}
