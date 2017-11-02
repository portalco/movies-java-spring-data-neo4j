package companies.spring.data.neo4j.services;

import java.util.*;

import companies.spring.data.neo4j.domain.Company;
import companies.spring.data.neo4j.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import companies.spring.data.neo4j.repositories.CompanyRepository;

@Service
public class CompanyService {

	@Autowired CompanyRepository movieRepository;

	private Map<String, Object> toD3Format(Collection<Company> movies) {
		List<Map<String, Object>> nodes = new ArrayList<>();
		List<Map<String, Object>> rels = new ArrayList<>();
		int i = 0;
		Iterator<Company> result = movies.iterator();
		while (result.hasNext()) {
			Company movie = result.next();
			nodes.add(map("title", movie.getTitle(), "label", "movie"));
			int target = i;
			i++;
			for (Role role : movie.getRoles()) {
				Map<String, Object> actor = map("title", role.getPerson().getName(), "label", "actor");
				int source = nodes.indexOf(actor);
				if (source == -1) {
					nodes.add(actor);
					source = i++;
				}
				rels.add(map("source", source, "target", target));
			}
		}
		return map("nodes", nodes, "links", rels);
	}

	private Map<String, Object> map(String key1, Object value1, String key2, Object value2) {
		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put(key1, value1);
		result.put(key2, value2);
		return result;
	}

	@Transactional(readOnly = true)
	public Map<String, Object>  graph(int limit) {
		Collection<Company> result = movieRepository.graph(limit);
		return toD3Format(result);
	}
}
