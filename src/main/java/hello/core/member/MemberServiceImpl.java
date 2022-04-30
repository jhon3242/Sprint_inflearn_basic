package hello.core.member;

public class MemberServiceImpl implements MemberService{

	private final MemberReposiroty memberReposiroty = new MemoryMemberRepository();

	@Override
	public void join(Member member) {
		memberReposiroty.save(member);
	}

	@Override
	public Member findMember(Long memberId) {
		return memberReposiroty.findbyID(memberId);
	}
}
