package byweather.ffrogy.springbootdeveloper.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ExampleController {

    @GetMapping("/thymeleaf/example")
    // Model : 뷰, 즉 HTML로 값을 넘겨주는 객체
    public String thymeleafExample(Model model){
        Person examplePerson = new Person();
        examplePerson.setId(1L);
        examplePerson.setName("홍길동");
        examplePerson.setAge(11);
        examplePerson.setHobbies(List.of("운동", "독서"));

        // addAttribute() : 모델에 값을 저장
        model.addAttribute("person", examplePerson);
        model.addAttribute("today", LocalDate.now());

        // 이 메서드가 반환하는 것은 example.
        // 클래스에 붙은 애너테이션이 @Controller이므로 뷰의 이름을 반환하는 것
        // 즉, 스프링부트는 컨트롤러의 @Controller를 보고 반환하는 값의 이름을 가진
        // 뷰의 파일을 찾으라는 것이라고 이해하여 resource/templates 디렉터리에서 example.html 파일을 찾아 보여준다.
        return "example"; // example.html이라는 뷰 조회
    }

    @Setter
    @Getter
    class Person{
        private Long id;
        private String name;
        private int age;
        private List<String> hobbies;
    }
}
