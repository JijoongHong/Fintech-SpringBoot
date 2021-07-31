package fintech.spring.repository;

import fintech.spring.entity.Member;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource 자동생성 repository 인터페이스 하나로
public interface MemberRepository extends PagingAndSortingRepository<Member,Long> {
}
