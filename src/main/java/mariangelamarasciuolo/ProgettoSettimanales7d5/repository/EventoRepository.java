package mariangelamarasciuolo.ProgettoSettimanales7d5.repository;

import mariangelamarasciuolo.ProgettoSettimanales7d5.entities.Eventi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Eventi, Integer> {
}
