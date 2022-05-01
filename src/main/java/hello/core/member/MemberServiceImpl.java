package hello.core.member;

public class MemberServiceImpl implements MemberService{

//	private final MemberReposiroty memberReposiroty = new MemoryMemberRepository(); 이것은 마치 배우가 직접 담당 배역을 설정
	private final MemberReposiroty memberReposiroty;

	public MemberServiceImpl(MemberReposiroty memberReposiroty) {
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
}
