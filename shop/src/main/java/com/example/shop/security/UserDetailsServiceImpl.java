package com.example.shop.security;

import com.example.shop.models.Role;
import com.example.shop.models.dto.UserDto;
import com.example.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.shop.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.PasswordAuthentication;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));
        return UserSecurity.fromUser(user);
    }

    public User getUser(String email){
        User user = userRepository.findByEmail(email).orElse(null);
        return user;
    }

    public void addUser(UserDto userDto){
        User user = userFromUserDto(userDto);
        user.setRole(Role.USER);
        userRepository.save(user);
    }

    private User userFromUserDto(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(new BCryptPasswordEncoder(12).encode(userDto.getPassword()));
        return user;
    }
}