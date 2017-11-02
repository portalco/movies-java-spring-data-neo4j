package companies.spring.data.neo4j.repositories;

import java.util.Collection;

import companies.spring.data.neo4j.domain.Company;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Michael Hunger
 * @author Mark Angrish
 */
@RepositoryRestResource(collectionResourceRel = "movies", path = "movies")
public interface CompanyRepository extends PagingAndSortingRepository<Company, Long> {

	Company findByTitle(@Param("title") String title);

	Collection<Company> findByTitleLike(@Param("title") String title);

	@Query("MATCH (m:Movie)<-[r:ACTED_IN]-(a:Person) RETURN m,r,a LIMIT {limit}")
	Collection<Company> graph(@Param("limit") int limit);
}

