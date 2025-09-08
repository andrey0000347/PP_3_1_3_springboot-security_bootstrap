package org.example.springboot.service;

import org.example.springboot.model.Role;
import org.example.springboot.repository.RoleRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepo roleRepo;

    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<Role> getAllRoles() {
        return roleRepo.findAll();
    }

}
