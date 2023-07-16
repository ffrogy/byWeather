package byweather.ffrogy.springbootdeveloper.controller;

import byweather.ffrogy.springbootdeveloper.domain.Article;
import byweather.ffrogy.springbootdeveloper.dto.AddArticleRequest;
import byweather.ffrogy.springbootdeveloper.dto.ArticleResponse;
import byweather.ffrogy.springbootdeveloper.dto.UpdateArticleRequest;
import byweather.ffrogy.springbootdeveloper.service.BlogService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController // Http Response Body에 객체 데이터를 JSON 형식으로 반환하는 컨트롤러
public class BlogApiController {

    private final BlogService blogService;

    // HTTP 메서드가 POST일 때 전달받은 URl과 동일하면 메서드로 매핑
    // 지금의 경우 /api/articles는 addArticle() 메서드에 매핑 됨
    @PostMapping("/api/articles")
    // @RequestBody는 HTTP를 요청할 때 응답에 해당하는 값을 @RequestBody가 붙은 대상 객체
    // (여기서는 AddArticleRequest)에 매핑함
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){
        Article savedArticle = blogService.save(request);
        // ResponseEntity.status().body()는 응답코드로 201, 즉 Created를 응답.
        // 요청한 자원이 성공적으로 생성되었으며 저장된 블로그 글 정보를 응답 객체에 담아 전송
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    // =======================================
    // 전체 글 조회
    // =======================================
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles(){
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(articles);
    }

    @GetMapping("/api/articles/{id}")
    // URL 경로에서 값 추출
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id){
        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id){
        blogService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id, @RequestBody UpdateArticleRequest request) {
        Article updateArticle = blogService.update(id, request);

        return ResponseEntity.ok()
                .body(updateArticle);
    }
}
