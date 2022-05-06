package com.example.Demo.Service.ServiceImp;

import com.example.Demo.Model.Role;
import com.example.Demo.Model.User;
import com.example.Demo.Model.UserRole;
import com.example.Demo.Repositories.RoleRepository;
import com.example.Demo.Repositories.UserRepository;
import com.example.Demo.Service.RoleService;
import com.example.Demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    // Thong tin role service
    @Autowired
    private RoleService roleService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        User user =  userRepository.findByEmail(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        Set<UserRole> roles = (Set<UserRole>) user.getListUserRole();
//        for (Role role : roles) {
//            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
//        }
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getEmail(), user.getPassword(), grantedAuthorities);
        User appUser = this.userRepository.findByEmail(username);
        if(appUser == null) {
            System.out.println("User not found! "+username);
            throw new UsernameNotFoundException("User " + username + " was not found in database");
        }
        else {
            System.out.println("User found! "+username);
            System.out.println("Password: " + appUser.getPassword());
        }

        List<String> roleNames = this.roleService.getRoleNames(appUser.getId());
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        if(roleNames!=null) {
            for(String role: roleNames) {
                System.out.println(role);
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantedAuthorities.add(authority);
            }
        }
//        UserDetails userDetails = (UserDetails) new User(appUser.getEmail(), appUser.getPassword(),grantedAuthorities);
//        return userDetails;
        return new org.springframework.security.core.userdetails.User(
               appUser.getEmail(), appUser.getPassword(), grantedAuthorities);
    }
}
