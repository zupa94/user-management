package com.mzupancic.usermanagement.service;

import com.mzupancic.usermanagement.exception.PrivilegeAlreadyExistException;
import com.mzupancic.usermanagement.model.Privilege;
import com.mzupancic.usermanagement.repository.PrivilegeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class PrivilegeService {

    private PrivilegeRepository privilegeRepository;

    @Transactional
    public void addPrivilege(Privilege privilege) {
        Privilege newPrivilege = new Privilege();

        if (privilegeRepository.existsByName(privilege.getName().toUpperCase())) {
            throw new PrivilegeAlreadyExistException(String.format("Privilege %s already exist.", privilege.getName()));
        }
        newPrivilege.setName(privilege.getName().toUpperCase());
        newPrivilege.setDescription(privilege.getDescription());

        privilegeRepository.save(newPrivilege);
    }
}
