package fri.uni_lj.si.statisticsService.services;

import fri.uni_lj.si.statisticsService.services.datafetchers.AllStatisticsDataFetcher;
import fri.uni_lj.si.statisticsService.services.datafetchers.StatisticByIdDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
public class GraphQLService {

    @Value("classpath:statistics.graphql")
    Resource resource;

    private GraphQL graphQL;

    @Autowired
    private AllStatisticsDataFetcher allStatisticsDataFetcher;

    @Autowired
    private StatisticByIdDataFetcher statisticByIdDataFetcher;

    @PostConstruct
    private void loadSchema() throws IOException {
        File schemaFile = resource.getFile();
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring runtimeWiring = RuntimeWiring
                .newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                            .dataFetcher("allStatistics", allStatisticsDataFetcher)
                            .dataFetcher("statisticById", statisticByIdDataFetcher)
                )
                .build();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }
}
