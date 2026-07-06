package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {

    private static final MemberRepository instance = new MemberRepository();
    private Map<Long, Member> store = new HashMap<>();
    private long sequence = 0L;


    private MemberRepository() {
    }


    public static MemberRepository getInstance() {
        return instance;
    }


    public Member save(Member member) {

        member.setId(++sequence);
        store.put(member.getId(), member);

        return member;
    }


    public List<Member> saveAll(List<Member> members) {

        members.forEach(member -> {
            member.setId(++sequence);
            store.put(member.getId(), member);
        });

        return members;
    }


    public Member findById(Long id) {

        return store.get(id);
    }


    public List<Member> findAll() {

        return new ArrayList<>(store.values());
    }


    public void clearStore() {
        store.clear();
    }
}
