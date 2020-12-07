package fri.uni_lj.si.statisticsService.repository;

import fri.uni_lj.si.statisticsService.models.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StatisticsRepository extends JpaRepository<Statistic, Long> {

    Optional<List<Statistic>> findAllByUserId (Long userId);
}
