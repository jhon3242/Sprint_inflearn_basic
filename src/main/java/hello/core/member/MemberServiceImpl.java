package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

//	private final MemberReposiroty memberReposiroty = new MemoryMemberRepository(); 이것은 마치 배우가 직접 담당 배역을 설정
	private final MemberReposiroty memberReposiroty;

	@Autowired
	public MemberServiceImpl(MemberReposiroty memberReposiroty) {
		System.out.println("memberReposiroty = " + memberReposiroty);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		this.memberReposiroty = memberReposiroty;
	}

	@Override
	public void join(Member member) {
		memberReposiroty.save(member);
	}

	@Override
	public Member findMember(Long memberId) {
		return memberReposiroty.findbyID(memberId);
	}

	// 테스트 용도
	public MemberReposiroty getMemberReposiroty(){
		return memberReposiroty;
	}
}
