package com.api.pautacontrol.repositories;

import com.api.pautacontrol.entities.PautaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PautaRepository extends JpaRepository<PautaEntity, UUID> {
}
