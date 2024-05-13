package repo;

import model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepo  extends JpaRepository<Livro, Long> {
}
