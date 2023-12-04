package com.bilvantis.user.api.util;

import com.bilvantis.user.dao.data.model.Team;
import com.bilvantis.user.data.model.TeamDTO;

public class TeamSupport {

    public static void updateTeamFromTeamDTO(Team team, TeamDTO teamDTO){
        team.setId(teamDTO.getId());
        team.setName(teamDTO.getName());
        team.setIsActive(teamDTO.getIsActive());
        team.setCreatedBy(teamDTO.getCreatedBy());
        team.setUpdatedBy(teamDTO.getUpdatedBy());
        team.setUpdatedDate(teamDTO.getUpdatedDate());
    }
    public static Team convertTeamDtoToTeamEntity(TeamDTO teamDTO){
        Team team =new Team();
        team.setId(teamDTO.getId());
        team.setName(teamDTO.getName());
        team.setIsActive(teamDTO.getIsActive());
        team.setCreatedBy(teamDTO.getCreatedBy());
        team.setCreatedDate(teamDTO.getCreatedDate());
        team.setUpdatedBy(teamDTO.getUpdatedBy());
        team.setUpdatedDate(teamDTO.getUpdatedDate());
        return team;
    }
    public static TeamDTO convertTeamEntityToTeamDto(Team team){
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setId(team.getId());
        teamDTO.setName(team.getName());
        teamDTO.setIsActive(team.getIsActive());
        teamDTO.setCreatedBy(team.getCreatedBy());
        teamDTO.setCreatedDate(team.getCreatedDate());
        teamDTO.setUpdatedBy(team.getUpdatedBy());
        teamDTO.setUpdatedDate(team.getUpdatedDate());
        return teamDTO;
    }
}
