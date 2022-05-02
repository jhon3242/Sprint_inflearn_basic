package hello.core.order;

import hello.core.CoreApplication;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FIxDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberReposiroty;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

//	private final MemberReposiroty memberReposiroty = new MemoryMemberRepository();
//	private final DiscountPolicy discountPolicy = new FIxDiscountPolicy();
//	private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
	private final MemberReposiroty memberReposiroty;
	private final DiscountPolicy discountPolicy;

	public OrderServiceImpl(MemberReposiroty memberReposiroty, DiscountPolicy discountPolicy) {
		this.memberReposiroty = memberReposiroty;
		this.discountPolicy = discountPolicy;
	}

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		Member member = memberReposiroty.findbyID(memberId);
		int discountPrice = discountPolicy.discount(member, itemPrice);

		return new Order(memberId, itemName, itemPrice, discountPrice );
	}
}
