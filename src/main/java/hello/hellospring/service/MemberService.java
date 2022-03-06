package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberReporsitory;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

//@Service  ->config에 직접 등록
@Transactional
public class MemberService {

    private final MemberReporsitory memberReporsitory;

    @Autowired //Dependency Injection
    public MemberService(MemberReporsitory memberReporsitory) {
        this.memberReporsitory = memberReporsitory;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원X
        // Optional로 인해 ifPresent 사용가능 (null 가능성 있는 경우)
        // extract method -> ctrl+alt+shift+T (ctrl+alt+M)
        validateDuplicateMember(member); //중복회원 검증
        memberReporsitory.save(member);
       return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberReporsitory.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberReporsitory.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberReporsitory.findById(memberId);
    }
}
