package fri.uni_lj.si.statisticsService.services.datafetchers;

import fri.uni_lj.si.statisticsService.models.Statistic;
import fri.uni_lj.si.statisticsService.repository.StatisticsRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllStatisticsDataFetcher implements DataFetcher<List<Statistic>> {

    @Autowired
    StatisticsRepository statisticsRepository;

    @Override
    public List<Statistic> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return statisticsRepository.findAll();
    }
}
