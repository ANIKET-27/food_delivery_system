package com.example.food_delivering_system.services.impl.security;
import com.example.food_delivering_system.entities.Role;
import com.example.food_delivering_system.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserInfo extends User implements UserDetails {


    Collection<? extends GrantedAuthority>  authority;


   public CustomUserInfo(User user){
       super.setUserName(user.getUserName());
       super.setPassword(user.getPassword());
       List<GrantedAuthority> authority = new ArrayList<>();


           authority.add(new SimpleGrantedAuthority(user.getRole().getRoleName().toUpperCase()));

           this.authority = authority;


   }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authority;
    }

    @Override
    public String getUsername() {
        return super.getUserName();
    }

    @Override
    public String getPassword(){
        return super.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
