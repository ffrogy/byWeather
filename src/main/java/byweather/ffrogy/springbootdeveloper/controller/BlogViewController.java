package byweather.ffrogy.springbootdeveloper.controller;

import byweather.ffrogy.springbootdeveloper.domain.Article;
import byweather.ffrogy.springbootdeveloper.dto.ArticleListViewResponse;
import byweather.ffrogy.springbootdeveloper.dto.ArticleViewResponse;
import byweather.ffrogy.springbootdeveloper.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model){
        List<ArticleListViewResponse> articles = blogService.findAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();
        model.addAttribute("articles",articles);

        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String getArticles(@PathVariable Long id, Model model){
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }

    @GetMapping("/new-article")
    // id 키를 가진 쿼리 파라미터의 값을 id 변수에 매핑(id는 없을 수도 있음)
    public String newArticle(@RequestParam(required = false) Long id, Model model){
        if (id == null) {   // id가 없으면 생성이므로 기본 생성자를 이용해 빈 ArticleViewResponse 객체를 만든다.
            model.addAttribute("article", new ArticleViewResponse());
        } else {    // id가 있으면 수정이므로 findById를 이용해 기존 값을 가져온다.
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }

        return "newArticle";
    }
}
