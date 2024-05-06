package com.fitcoach.fitcoach.mappers;

import com.fitcoach.fitcoach.dao.entity.Admin;
import com.fitcoach.fitcoach.dtos.AdminDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AdminMapper {
    public AdminDTO AdminToDTO(Admin admin){
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setId(admin.getId());
        adminDTO.setAvatar(adminDTO.getAvatar());
        adminDTO.setCreatedAt(admin.getCreatedAt());
        adminDTO.setFirstName(admin.getFirstName());
        adminDTO.setUpdateAt(admin.getUpdateAt());
        adminDTO.setLastName(admin.getLastName());

        return adminDTO;
    }
    public Admin DTOoAdmin(AdminDTO adminDTO){
        Admin admin = new Admin();
        admin.setId(adminDTO.getId());
        admin.setAvatar(adminDTO.getAvatar());
        admin.setCreatedAt(adminDTO.getCreatedAt());
        admin.setFirstName(adminDTO.getFirstName());
        admin.setUpdateAt(adminDTO.getUpdateAt());
        admin.setLastName(adminDTO.getLastName());

        return admin;
    }


}
