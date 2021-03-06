package fri.uni_lj.si.statisticsService.services;

import fri.uni_lj.si.statisticsService.models.Statistic;
import fri.uni_lj.si.statisticsService.repository.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StatisticsService {

    @Autowired
    private StatisticsRepository statisticsRepository;

    @Retryable(maxAttempts = 2, backoff = @Backoff(delay = 100))
    public List<Statistic> getAllStatistics() {
        return statisticsRepository.findAll();
    }

    @Recover
    public List<Statistic> getAllStatisticsRecover() {
        List<Statistic> recoverList = new ArrayList<>();
        Statistic recoverStatistic = new Statistic();
        recoverStatistic.setNumOfToDo(-1L);
        recoverStatistic.setNumOfInProgress(-1L);
        recoverStatistic.setNumOfCompleted(-1L);
        recoverStatistic.setUserId(-2L);
        recoverList.add(recoverStatistic);
        return recoverList;
    }

    public Statistic getStatisticById (Long id) {
        Optional<Statistic> stx = statisticsRepository.findById(id);

        if (stx.isPresent()) {
            return stx.get();
        }

        return null;
    }

    public List<Statistic> getAllStatisticsByUserId (Long userId) {
        Optional<List<Statistic>> stxByUser = statisticsRepository.findAllByUserId(userId);

        if (stxByUser.isPresent()) {
            return stxByUser.get();
        }

        return null;
    }

    public Statistic addStatistic (Statistic stx) {
        stx.setTimestampCreated(Timestamp.valueOf(LocalDateTime.now()));
        return statisticsRepository.save(stx);
    }

    public Statistic editStatistic (Long id, Statistic newStx) {
        Optional<Statistic> optStx = statisticsRepository.findById(id);

        if (optStx.isPresent()) {
            Statistic stx = optStx.get();

            if (stx.getUserId() != null && newStx.getUserId() != null) {
                stx.setUserId(newStx.getUserId());
            }

            if (stx.getNumOfCompleted() != null) {
                stx.setNumOfCompleted(newStx.getNumOfCompleted());
            }

            if (stx.getNumOfInProgress() != null) {
                stx.setNumOfInProgress(newStx.getNumOfInProgress());
            }

            if (stx.getNumOfToDo() != null) {
                stx.setNumOfToDo(newStx.getNumOfToDo());
            }

            stx.setTimestampCreated(Timestamp.valueOf(LocalDateTime.now()));

            return statisticsRepository.save(stx);
        }

        return null;
    }

    public String deleteStatistic (Long id) {
        Optional <Statistic> optStx = statisticsRepository.findById(id);

        if (optStx.isPresent()) {
            statisticsRepository.deleteById(id);
            return String.format("Statistic with id %d was successfully deleted.", id);
        }

        return String.format("Statistic with id %d not found.", id);
    }
}
