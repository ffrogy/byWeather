package byweather.ffrogy.springbootdeveloper.service;

import byweather.ffrogy.springbootdeveloper.domain.Article;
import byweather.ffrogy.springbootdeveloper.dto.AddArticleRequest;
import byweather.ffrogy.springbootdeveloper.dto.UpdateArticleRequest;
import byweather.ffrogy.springbootdeveloper.repository.BlogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor    // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service    // 빈으로 등록
public class BlogService {

    private final BlogRepository blogRepository;

    // 블로그 글 추가 메서드
    // save() 생성자는 JpaRepository 에서 지원하는 저장 메서드로,
    // save()로 AddArticleRequest 클래스에 저장된 값들을 article 데이터베이스에 저장한다.
    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    public Article findById(long id){
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    public void delete(long id){
        blogRepository.deleteById(id);
    }

    @Transactional  // 트랜젝션 메서드 : 매칭한 메서드를 하나의 트랜잭션으로 묶는다.
    public Article update(long id, UpdateArticleRequest request){
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
        article.update(request.getTitle(), request.getContent());

        return article;
    }
}
