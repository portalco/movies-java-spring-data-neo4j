package companies.spring.data.neo4j.controller;

import java.util.Map;

import companies.spring.data.neo4j.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

 
@RestController("/")
public class CompanyController {

	final CompanyService movieService;

	@Autowired
	public CompanyController(CompanyService movieService) {
		this.movieService = movieService;
	}

	@RequestMapping("/graph")
	public Map<String, Object> graph(@RequestParam(value = "limit",required = false) Integer limit) {
		return movieService.graph(limit == null ? 100 : limit);
	}
}
