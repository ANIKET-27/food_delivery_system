package com.example.food_delivering_system.services.impl.security;

import com.example.food_delivering_system.dto.Request.CreateUserDTO;
import com.example.food_delivering_system.entities.Role;
import com.example.food_delivering_system.entities.User;
import com.example.food_delivering_system.repository.UserRepository;
import com.example.food_delivering_system.services.impl.Convetor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Data
public class UserDetailServiceImpl implements UserDetailsService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserDetailServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // private static final Logger log = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
/////
     //   log.debug("Entering in loadUserByUsername Method...");
        User user = userRepository.findByUserName(username);
        if(user == null){
            //       log.error("Username not found: " + username);
            throw new UsernameNotFoundException("Could not found user..!!");
        }
    //      log.info("User Authenticated Successfully..!!!");
        return new CustomUserInfo(user);
    }

    public User checkIfUserAlreadyExist(CreateUserDTO userInfoDto){
        return userRepository.findByUserName(userInfoDto.getUserName());
    }

    public Boolean signupUser(CreateUserDTO dto){
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        if(Objects.nonNull(checkIfUserAlreadyExist(dto))){
            return false;
        }


        User user = Convetor.userDtoToUser(dto);

            user.setAvailable(false);
            user.setRole(Role.builder().roleId(2).build());

        try {
            userRepository.save(user);
        }
        catch (Exception ex) {
            System.out.println(ex);
            throw new RuntimeException("User Not Created");
        }
        return true;
    }

    public Boolean signupDriver(CreateUserDTO dto){
        //        ValidationUtil.validateUserAttributes(userInfoDto);
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        if(Objects.nonNull(checkIfUserAlreadyExist(dto))){

            return false;
        }


        //String userId = UUID.randomUUID().toString();
        //userRepository.save(new UserInfo(userId, userInfoDto.getUsername(), userInfoDto.getPassword(), new HashSet<>()));
        // pushEventToQueue

        User user = Convetor.userDtoToUser(dto);

        user.setAvailable(true);
        user.setRole(Role.builder().roleId(3).build());

        try {

            userRepository.save(user);
        }
        catch (Exception ex) {
            throw new RuntimeException("User Not Created");
        }
        return true;
    }

    public Boolean signupAdmin(CreateUserDTO dto){
        //        ValidationUtil.validateUserAttributes(userInfoDto);
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        if(Objects.nonNull(checkIfUserAlreadyExist(dto))){

            return false;
        }


        //String userId = UUID.randomUUID().toString();
        //userRepository.save(new UserInfo(userId, userInfoDto.getUsername(), userInfoDto.getPassword(), new HashSet<>()));
        // pushEventToQueue

        User user = Convetor.userDtoToUser(dto);

        user.setAvailable(false);
        user.setRole(Role.builder().roleId(1).build());

        try {

            userRepository.save(user);
        }
        catch (Exception ex) {
            throw new RuntimeException("User Not Created");
        }
        return true;
    }
}
