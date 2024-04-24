package com.project.eventify.service;
/*import com.project.eventify.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserAuthService(UserService userService) {
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getByEmail(email);
        if(user == null) {
            System.out.println("User not found : " + email);
            throw new UsernameNotFoundException("Not Found 404");
        }
        return new UserPrincipal(user);
    }


}*/
