package com.fundoo.lableservice.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fundoo.lableservice.entity.LableEntity;

public interface LableDao extends JpaRepository<LableEntity, UUID> {

	Optional<List<LableEntity>> findByUserId(UUID userId);

}
