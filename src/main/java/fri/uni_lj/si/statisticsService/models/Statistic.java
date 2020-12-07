package fri.uni_lj.si.statisticsService.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "statistics_table")
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Long userId;

    @Column
    private Long numOfCompleted;

    @Column
    private Long numOfInProgress;

    @Column
    private Long numOfToDo;

    @Column
    private Timestamp timestampCreated;


    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getNumOfCompleted() {
        return numOfCompleted;
    }

    public void setNumOfCompleted(Long numOfCompleted) {
        this.numOfCompleted = numOfCompleted;
    }

    public Long getNumOfInProgress() {
        return numOfInProgress;
    }

    public void setNumOfInProgress(Long numOfInProgress) {
        this.numOfInProgress = numOfInProgress;
    }

    public Long getNumOfToDo() {
        return numOfToDo;
    }

    public void setNumOfToDo(Long numOfToDo) {
        this.numOfToDo = numOfToDo;
    }

    public Timestamp getTimestampCreated() {
        return timestampCreated;
    }

    public void setTimestampCreated(Timestamp timestampCreated) {
        this.timestampCreated = timestampCreated;
    }
}
