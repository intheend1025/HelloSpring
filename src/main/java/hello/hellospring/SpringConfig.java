package hello.hellospring;

import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberReporsitory;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {


    //    @PersistenceContext
//    private EntityManager em;
    private final MemberReporsitory memberReporsitory;

    @Autowired
    public SpringConfig(MemberReporsitory memberReporsitory) {
        this.memberReporsitory = memberReporsitory;
    }

//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }


//    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberReporsitory);
    }

//    @Bean
//    public MemberReporsitory memberReporsitory() {
//        return new MemoryMemberRepository();
//        return new JpaMemberRepository(em);
//    }
}
