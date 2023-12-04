package com.bilvantis.user.api.service;

import com.bilvantis.user.data.model.TeamDTO;

import java.io.Serializable;
import java.util.List;

public interface TeamService<I extends TeamDTO,ID extends Serializable>{

    I createTeam(I teamDTO);

    List<I> getAllTeams();

    I getTeamById(ID id);

    I updateTeam(ID id, I teamDTO);

    void deleteTeam(ID id);
}
