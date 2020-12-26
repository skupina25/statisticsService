package fri.uni_lj.si.statisticsService.services;

import fri.uni_lj.si.statisticsService.services.datafetchers.AllStatisticsDataFetcher;
import fri.uni_lj.si.statisticsService.services.datafetchers.StatisticByIdDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

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
        InputStream schemaStream = resource.getInputStream();

        StringWriter writer = new StringWriter();
        IOUtils.copy(schemaStream, writer, "UTF-8");
        String schemaString = writer.toString();

        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schemaString);
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
