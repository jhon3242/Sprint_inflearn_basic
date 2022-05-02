package hello.core.member;

import hello.core.order.OrderService;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberReposiroty {

	private static Map<Long, Member> store = new HashMap<>();

	@Override
	public void save(Member member) {
		store.put(member.getId(), member);
	}

	@Override
	public Member findbyID(Long memberID) {
		return store.get(memberID);
	}
}
