package fri.uni_lj.si.statisticsService.api;

import fri.uni_lj.si.statisticsService.models.Statistic;
import fri.uni_lj.si.statisticsService.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/statistics")
@CrossOrigin("*")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllStatistics () {
        return ResponseEntity.status(HttpStatus.OK).body(statisticsService.getAllStatistics());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Object> getStatisticById (@PathVariable("id") Long id) {
        if (id.toString().equals("")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id of statistic is a required param.");
        }

        Statistic stx = statisticsService.getStatisticById(id);

        if (stx == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(stx);
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<Object> getStatisticsByUser (@PathVariable("id") Long id) {
        if (id.toString().equals("")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id of user is a required param.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(statisticsService.getAllStatisticsByUserId(id));
    }

    @PostMapping(path = "/user/{id}")
    public ResponseEntity<Object> addNewStatistic (@RequestBody Statistic stx, @PathVariable("id") Long userId) {
        if (userId.toString().equals("")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id of user is a required param.");
        }

        stx.setUserId(userId);
        Statistic newStx = statisticsService.addStatistic(stx);

        if (newStx == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(newStx);
    }

    @PostMapping(path = "/edit/{id}")
    public ResponseEntity<Object> editStatistic (@RequestBody Statistic newStx, @PathVariable("id") Long id) {
        if (id.toString().equals("")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id of statistic is a required param.");
        }

        if (newStx == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(statisticsService.editStatistic(id, newStx));
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Object> deleteStatistic (@PathVariable("id") Long id) {
        if (id.toString().equals("")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id of statistic is a required param.");
        }

        String msg = statisticsService.deleteStatistic(id);

        if (msg.contains("not found")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(msg);
    }
}
