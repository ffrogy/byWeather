package byweather.ffrogy.springbootdeveloper.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {
    private String email;
    private String password;
    private String gender;
    private String birth;
}
