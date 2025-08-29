package com.numbering.demo.REPO;

import com.numbering.demo.DTO.SettingsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingRepo extends JpaRepository<SettingsDTO,Long> {

}
