package com.api.pautacontrol.repositories;

import com.api.pautacontrol.entities.ProcessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProcessRepository extends JpaRepository<ProcessEntity, UUID> {
    boolean existsByNumber(String number);
}
