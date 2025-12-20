
import com.example.demo.model.CompatibilityScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompatibilityScoreRepository
extends JpaRepository<CompatibilityScore, Long> {

List<CompatibilityScore> findByStudent1Id(Long student1Id);
}