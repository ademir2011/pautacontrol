package com.api.pautacontrol.repositories;

import com.api.pautacontrol.entities.ProcessHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ProcessHistoryRepository extends JpaRepository<ProcessHistoryEntity, UUID> {

}

