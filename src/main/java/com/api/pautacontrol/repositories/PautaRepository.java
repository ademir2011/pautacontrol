package com.api.pautacontrol.repositories;

import com.api.pautacontrol.entities.PautaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PautaRepository extends JpaRepository<PautaEntity, UUID> {

    boolean existsBySectionDate(LocalDateTime localDateTime);

//    Optional<List<PautaEntity>> findBySectionDateContainingIgnoreCase(LocalDateTime localDateTime);

}
