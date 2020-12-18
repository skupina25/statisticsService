package fri.uni_lj.si.statisticsService.services.datafetchers;

import fri.uni_lj.si.statisticsService.models.Statistic;
import fri.uni_lj.si.statisticsService.repository.StatisticsRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StatisticByIdDataFetcher implements DataFetcher<Statistic> {

    @Autowired
    StatisticsRepository statisticsRepository;

    @Override
    public Statistic get(DataFetchingEnvironment dataFetchingEnvironment) {

        Long id = Long.valueOf(dataFetchingEnvironment.getArgument("id").toString());

        return statisticsRepository.findById(id).orElse(null);
    }
}
