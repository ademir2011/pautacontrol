package com.api.pautacontrol.repositories;

import com.api.pautacontrol.entities.MinisterEntity;
import com.api.pautacontrol.entities.ProcessPartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProcessPartRepository extends JpaRepository<ProcessPartEntity, UUID> {

}

