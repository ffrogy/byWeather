package byweather.ffrogy.springbootdeveloper.dto;

import byweather.ffrogy.springbootdeveloper.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// DTO : 단순하게 데이터를 옮기기 위해 사용하는 전달자 역할을 하는 객체. 별도의 비즈니스 로직을 포함하지 않는다.
@NoArgsConstructor  // 기본 생성자 추가
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자 추가
@Getter
public class AddArticleRequest {

    private String title;
    private String content;

    public Article toEntity(){ // 생성자를 사용해 객체 생성
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
