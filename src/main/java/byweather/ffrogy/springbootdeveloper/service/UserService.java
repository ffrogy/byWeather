package byweather.ffrogy.springbootdeveloper.service;

import byweather.ffrogy.springbootdeveloper.domain.User;
import byweather.ffrogy.springbootdeveloper.dto.AddUserRequest;
import byweather.ffrogy.springbootdeveloper.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public long save(AddUserRequest dto){
        return userRepository.save(User.builder()
                .email(dto.getEmail())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .gender(dto.getGender())
                .birth(dto.getBirth())
                .build()).getId();
    }
}
