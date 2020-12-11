package io.zenbydef.usertracker.util;

import io.zenbydef.usertracker.io.entities.oldpack.Role;
import io.zenbydef.usertracker.service.oldpack.roleservice.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class RoleManager {
    private final RoleService roleService;
    private List<Role> roles;
    private List<String> stringRoles;

    public RoleManager(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    private void setRolesSet() {
        this.roles = roleService.getRoles();
    }

    @Autowired
    private void setStringRolesSet() {
        this.stringRoles = roles.stream()
                .map(Role::getNameOfRole)
                .collect(Collectors.toList());
    }

    public List<Role> convertRoles(String[] roles) {
        return Arrays.stream(roles)
                .filter(roleName -> !roleName.equals(""))
                .map(this::getRole)
                .collect(Collectors.toList());
    }

    private Role getRole(String s) {
        Role roleToFind = null;
        for (Role role : roles) {
            if (s.equalsIgnoreCase(role.getNameOfRole())) {
                roleToFind = role;
            }
        }
        return Objects.requireNonNull(roleToFind);
    }

    public List<String> getStringRoles() {
        return stringRoles;
    }
}
