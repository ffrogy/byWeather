package byweather.ffrogy.springbootdeveloper.repository;

import byweather.ffrogy.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
