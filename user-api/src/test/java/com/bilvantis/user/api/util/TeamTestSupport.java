package com.bilvantis.user.api.util;

import com.bilvantis.user.dao.data.model.Team;
import com.bilvantis.user.data.model.TeamDTO;

import java.util.Date;

import static com.bilvantis.user.api.util.RewardsApiTestConstant.TEAM_ID_55;

public class TeamTestSupport {

    public static Team createTeam(Long id) {
        Team team = new Team();
        team.setId(id);
        team.setName("Digital Engineering");
        team.setIsActive(true);
        team.setCreatedBy("SYSTEM");
        team.setCreatedDate(new Date());
        team.setUpdatedBy("SYSTEM");
        team.setUpdatedDate(new Date());
        return team;
    }
    public static Team createExistingTeam(Long teamId) {
        Team team = new Team();
        team.setId(teamId);
        team.setName("Heritage");
        team.setIsActive(true);
        team.setCreatedBy("SYSTEM");
        team.setCreatedDate(new Date());
        team.setUpdatedBy("SYSTEM");
        team.setUpdatedDate(new Date());
        return team;
    }
    public static Team updatingTeamData(Long teamId) {
        Team team = new Team();
        team.setId(teamId);
        team.setName("Cloud Data Engineering");
        team.setCreatedBy("SYSTEM");
        team.setUpdatedBy("SYSTEM");
        team.setIsActive(true);
        team.setCreatedDate(new Date());
        team.setUpdatedDate(new Date());
        return team;
    }
    public static TeamDTO updatingTeamDTOData(Long teamId) {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setId(teamId);
        teamDTO.setName("Cloud Data Engineering");
        teamDTO.setCreatedBy("SYSTEM");
        teamDTO.setUpdatedBy("SYSTEM");
        teamDTO.setIsActive(true);
        teamDTO.setCreatedDate(new Date());
        teamDTO.setUpdatedDate(new Date());
        return teamDTO;
    }
    public static TeamDTO updatingMismatchTeamDTOData(Long teamId) {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setId(TEAM_ID_55);
        teamDTO.setName("Cloud Data Engineering");
        teamDTO.setCreatedBy("SYSTEM");
        teamDTO.setUpdatedBy("SYSTEM");
        teamDTO.setIsActive(true);
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
