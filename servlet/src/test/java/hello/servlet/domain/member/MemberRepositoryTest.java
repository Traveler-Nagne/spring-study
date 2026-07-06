package hello.servlet.domain.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemberRepositoryTest {

    private MemberRepository memberRepository = MemberRepository.getInstance();


    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }


    @Test
    void save() {

        // given
        Member lee = Member.create("lee", 34);

        // when
        Member savedMember = memberRepository.save(lee);

        // then
        Member member = memberRepository.findById(savedMember.getId());
        assertThat(member).isEqualTo(savedMember);
    }


    @Test
    void saveAll() {

        // given
        Member lee = Member.create("lee", 34);
        Member park = Member.create("park", 34);

        // when
        List<Member> savedMembers = memberRepository.saveAll(List.of(lee, park));

        // then
        List<Member> members = memberRepository.findAll();
        assertThat(members).isEqualTo(savedMembers);
    }


    @Test
    void findById() {

        // given
        Member lee = Member.create("lee", 34);
        Member savedMember = memberRepository.save(lee);

        // when
        Member member = memberRepository.findById(savedMember.getId());

        // then
        assertThat(member).isEqualTo(savedMember);
    }


    @Test
    void findAll() {

        // given
        Member lee = Member.create("lee", 34);
        Member park = Member.create("park", 34);
        List<Member> savedMembers = memberRepository.saveAll(List.of(lee, park));

        // when
        List<Member> members = memberRepository.findAll();

        // then
        assertThat(members).contains(lee, park);
    }
}