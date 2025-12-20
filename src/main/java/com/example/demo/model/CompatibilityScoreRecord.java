import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CompatibilityScoreRecord {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private Long studentAId;
private Long studentBId;
private Double score;
private LocalDateTime computedAt = LocalDateTime.now();

public Long getId() {
return id;
}

public void setId(Long id) {
this.id = id;
}

public Long getStudentAId() {
return studentAId;
}

public void setStudentAId(Long studentAId) {
this.studentAId = studentAId;
}

public Long getStudentBId() {
return studentBId;
}

public void setStudentBId(Long studentBId) {
this.studentBId = studentBId;
}

public Double getScore() {
return score;
}

public void setScore(Double score) {
this.score = score;
}

public LocalDateTime getComputedAt() {
return computedAt;
}
}