package com.learnwithmohit.service.impl;

import com.learnwithmohit.entity.Role;
import com.learnwithmohit.repository.RoleRepository;
import com.learnwithmohit.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role createNewRole(Role role) {
        return roleRepository.save(role);
    }
}
