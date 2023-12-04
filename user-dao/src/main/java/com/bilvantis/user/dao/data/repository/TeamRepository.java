package com.bilvantis.user.dao.data.repository;

import com.bilvantis.user.dao.data.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {
}
