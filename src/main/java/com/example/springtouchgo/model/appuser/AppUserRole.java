package com.example.springtouchgo.model.appuser;
import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum AppUserRole {
    //Normal user has always limited authority
    NORMAL_USER(Sets.newHashSet(Permissions.ADD_OWN_NEW_USER)),

    // The official admin has an authority to control large data
    ADMIN(Sets.newHashSet(
            Permissions.ADD_OWN_NEW_USER,
            Permissions.VIEW_ALL_DETAILS,
            Permissions.EDIT_ALL_SCORE,
            Permissions.MODIFY_USER_DETAILS,
            Permissions.VIEW_ALL_USER_SCORE
    )),

    // The admin trainee has the authority to view only
    ADMIN_TRAINEE(Sets.newHashSet(
            Permissions.ADD_OWN_NEW_USER,
            Permissions.VIEW_ALL_USER_SCORE,
            Permissions.VIEW_ALL_USER_SCORE
    ));

    public Set<Permissions> getPermissions() {
        return permissions;
    }

    private final  Set<Permissions> permissions;
    AppUserRole(Set<Permissions>permissions) {
        this.permissions = permissions;
    }

    // We inject the permissions into the roles because we are implementing "Authority-based-authentication"
    // in the security structure of the API

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions()
                .stream().map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_"+ this.name()));
        return permissions;
    }


}
