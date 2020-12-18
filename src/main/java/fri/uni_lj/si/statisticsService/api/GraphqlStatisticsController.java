package fri.uni_lj.si.statisticsService.api;

import fri.uni_lj.si.statisticsService.services.GraphQLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/graphql/statistics")
@CrossOrigin("*")
public class GraphqlStatisticsController {

    @Autowired
    GraphQLService graphQLService;

    @GetMapping
    public ResponseEntity<Object> getAllByGraphql(@RequestBody String query) {
        return ResponseEntity.status(HttpStatus.OK).body(graphQLService.getGraphQL().execute(query).getData());
    }
}
