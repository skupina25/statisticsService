package fri.uni_lj.si.statisticsService.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/statistics")
@CrossOrigin("*")
public class StatisticsController {

    @GetMapping
    public String getTest() {
        return "Hello stranger";
    }
}
