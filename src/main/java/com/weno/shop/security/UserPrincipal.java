package com.weno.shop.security;

import com.weno.shop.entity.Member;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
public class UserPrincipal implements UserDetails {

    private Long id;

    private String userId;

    private String password;

    private String name;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(Long id, String userId, String password, String name, Collection<? extends GrantedAuthority> authorities){
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.authorities = authorities;
    }


    public static UserPrincipal create(Member member){

        String role = String.valueOf(member.getRole());

        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));

        return new UserPrincipal(
                member.getId(),
                member.getUserId(),
                member.getPassword(),
                member.getName(),
                authorities
        );
        
    }




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
