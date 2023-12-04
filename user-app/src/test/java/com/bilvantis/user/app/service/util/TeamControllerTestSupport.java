package com.bilvantis.user.app.service.util;

import com.bilvantis.user.data.model.TeamDTO;

import java.util.Date;

public class TeamControllerTestSupport {
    public static TeamDTO createTeamForNomineeDTOEmployeeDTO(){
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setId(1L);
        teamDTO.setName("java");
        teamDTO.setIsActive(true);
        teamDTO.setCreatedBy("Pooja");
        teamDTO.setUpdatedBy("Pooja");
        teamDTO.setCreatedDate(new Date());
        teamDTO.setUpdatedDate(new Date());
        return teamDTO;
    }
    public static TeamDTO createTeamNominatedByEmployeeDTO(){
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setId(1L);
        teamDTO.setName("java");
        teamDTO.setIsActive(true);
        teamDTO.setCreatedBy("Pooja");
        teamDTO.setUpdatedBy("Pooja");
        teamDTO.setCreatedDate(new Date());
        teamDTO.setUpdatedDate(new Date());
        return teamDTO;
    }

    public static TeamDTO createTeamDTO(Long id) {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setId(id);
        teamDTO.setName("Digital Engineering");
        teamDTO.setIsActive(true);
        teamDTO.setCreatedBy("SYSTEM");
        teamDTO.setCreatedDate(new Date());
        teamDTO.setUpdatedBy("SYSTEM");
        teamDTO.setUpdatedDate(new Date());
        return teamDTO;
    }
}
