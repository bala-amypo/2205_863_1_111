package com.example.demo.security;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

@Override
public UserDetails loadUserByUsername(String username) {
return new User(
username,
"{noop}password",
List.of()
);
}
}