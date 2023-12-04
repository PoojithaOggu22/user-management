package com.bilvantis.user.api.service;

import com.bilvantis.user.api.exception.ApplicationException;
import com.bilvantis.user.api.service.impl.TeamServiceImpl;
import com.bilvantis.user.api.util.RewardsProperties;
import com.bilvantis.user.dao.data.model.Team;
import com.bilvantis.user.dao.data.repository.TeamRepository;
import com.bilvantis.user.data.model.TeamDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static com.bilvantis.user.api.util.RewardsApiTestConstant.*;
import static com.bilvantis.user.api.util.TeamTestSupport.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Team Service Tests")
public class TeamServiceImplTest {

    @InjectMocks
    private TeamServiceImpl teamService;

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private RewardsProperties rewardsProperties;

    @Test
    @DisplayName("Test get all teams")
    public void testGetAllTeams() {

        //given
        Team team = createTeam(TEAM_ID_1);
        List<Team> mockTeams = Collections.singletonList(team);
        when(teamRepository.findAll()).thenReturn(mockTeams);

        // when
        List<TeamDTO> teamDTOList = teamService.getAllTeams();

        // then
        assertEquals(1, teamDTOList.size());
        assertEquals(1, teamDTOList.get(0).getId());
        assertEquals("Digital Engineering", teamDTOList.get(0).getName());
        assertEquals("SYSTEM", teamDTOList.get(0).getCreatedBy());
        assertEquals("SYSTEM", teamDTOList.get(0).getUpdatedBy());
        assertTrue(teamDTOList.get(0).getIsActive());
    }

    @Test
    @DisplayName("Test get all teams with empty result")
    public void testGetAllTeamsEmptyResult() {

        //given
        when(teamRepository.findAll()).thenReturn(Collections.emptyList());
        when(rewardsProperties.getTeamsNotAvailable()).thenReturn("Team Details not present in the system");

        //when
        ApplicationException exception = assertThrows(ApplicationException.class, () -> teamService.getAllTeams());

        //then
        assertEquals("Team Details not present in the system", exception.getMessage());
    }

    @Test
    @DisplayName("Test get all teams DataIntegrityViolation Exception")
    public void testGetAllTeamsEmptyResultDataIntegrityViolationException() {

        //given
        when(teamRepository.findAll()).thenThrow(new DataIntegrityViolationException("team save failed"));
        when(rewardsProperties.getExceptionErrorMessage()).thenReturn("Exception occurred in getAllTeams");

        //when
        ApplicationException exception = assertThrows(ApplicationException.class, () -> teamService.getAllTeams());

        //then
        assertEquals("team save failed", exception.getMessage());
    }

    @Test
    @DisplayName("Test get team by ID")
    public void testGetTeamById() {

        //given
        Long teamId = TEAM_ID_1;
        Team mockTeam = createTeam(teamId);
        when(teamRepository.findById(teamId)).thenReturn(Optional.of(mockTeam));

        //when
        TeamDTO teamDTO = teamService.getTeamById(teamId);

        //then
        assertEquals(1, teamDTO.getId());
        assertEquals("Digital Engineering", teamDTO.getName());
        assertEquals("SYSTEM", teamDTO.getCreatedBy());
        assertEquals("SYSTEM", teamDTO.getUpdatedBy());
        assertEquals(1, teamDTO.getId());
        assertTrue(teamDTO.getIsActive());
    }

    @Test
    @DisplayName("Test get team by ID with null")
    public void testGetTeamByIdNull() {

        // given
        when(rewardsProperties.getTeamIdNotNull()).thenReturn("Team id should not be null or empty");

        // when
        ApplicationException exception = assertThrows(ApplicationException.class, () -> teamService.getTeamById(null));

        //then
        assertEquals("Team id should not be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("Test get team by ID with Unavailable data")
    public void testGetTeamWithUnavailableTeamId() {

        //given
        Long teamId = TEAM_ID_55;
        when(teamRepository.findById(teamId)).thenReturn(Optional.empty());
        when(rewardsProperties.getTeamNotAvailableForId()).thenReturn("Team is not available for given id: 55");

        //when
        ApplicationException exception = assertThrows(ApplicationException.class, () -> teamService.getTeamById(teamId));

        //then
        assertEquals("Team is not available for given id: 55", exception.getMessage());
    }

    @Test
    @DisplayName("Test get all teams DataIntegrityViolation Exception")
    public void testGetAllTeamsBYIdResultDataIntegrityViolationException() {

        //given
        Long teamId = TEAM_ID_55;
        when(teamRepository.findById(teamId)).thenThrow(new DataIntegrityViolationException("team save failed"));
        when(rewardsProperties.getExceptionErrorMessage()).thenReturn("Exception occurred in repository while fetching the data");
        when(rewardsProperties.getTeamsNotAvailable()).thenReturn("Team Details not present in the system");

        //when
        ApplicationException exception = assertThrows(ApplicationException.class, () -> teamService.getTeamById(teamId));

        //then
        assertEquals("Team Details not present in the system", exception.getMessage());
    }

    @Test
    @DisplayName("Test create team")
    public void testCreateTeam() {

        //given
        Team savedTeam = createTeam(TEAM_ID_1);
        when(teamRepository.save(any(Team.class))).thenReturn(savedTeam);
        TeamDTO mockTeamDTO = createTeamDTO(TEAM_ID_1);

        //when
        TeamDTO createdTeam = teamService.createTeam(mockTeamDTO);

        //then
        assertEquals("Digital Engineering", createdTeam.getName());
        assertEquals("SYSTEM", createdTeam.getUpdatedBy());
        assertEquals("SYSTEM", createdTeam.getCreatedBy());
        assertEquals(1, createdTeam.getId());
        assertTrue(createdTeam.getIsActive());
    }

    @Test
    @DisplayName("Test create team DataIntegrityViolation Exception")
    public void testCreateTeamDataIntegrityViolationException() {

        //given
        Long teamId = TEAM_ID_88;
        TeamDTO mockTeamDTO = createTeamDTO(teamId);
        when(teamRepository.save(any(Team.class))).thenThrow(new DataIntegrityViolationException("create team failed"));
        when(rewardsProperties.getExceptionErrorMessage()).thenReturn("Exception occurred in create team");
        when(rewardsProperties.getTeamSaveFailed()).thenReturn("Failed to save Team");

        //when
        ApplicationException exception = assertThrows(ApplicationException.class, () -> teamService.createTeam(mockTeamDTO));

        //then
        assertEquals("Failed to save Team", exception.getMessage());
    }

    @Test
    @DisplayName("Test update team")
    public void testUpdateTeam() {

        //given
        Long teamId = TEAM_ID_1;
        TeamDTO mockTeamDTO = updatingTeamDTOData(teamId);
        Team existingTeam = createExistingTeam(teamId);
        when(teamRepository.findById(teamId)).thenReturn(Optional.of(existingTeam));
        when(teamRepository.save(Mockito.any(Team.class))).thenReturn(existingTeam);

        //when
        TeamDTO updatedTeam = teamService.updateTeam(teamId, mockTeamDTO);

        //then
        assertEquals(1L, updatedTeam.getId());
        assertEquals("Cloud Data Engineering", updatedTeam.getName());
        assertEquals("SYSTEM", updatedTeam.getCreatedBy());
        assertEquals("SYSTEM", updatedTeam.getUpdatedBy());
        assertTrue(updatedTeam.getIsActive());
    }

    @Test
    @DisplayName("Test update team with ID mismatch")
    public void testUpdateTeamWithIdMismatch() {

        //given
        Long teamId = TEAM_ID_1;
        TeamDTO mockTeamDTO = updatingMismatchTeamDTOData(teamId);
        when(rewardsProperties.getTeamIdMismatch()).thenReturn("Team id given in URL 1 and payLoad 1 should match");

        //when
        ApplicationException exception = assertThrows(ApplicationException.class, () -> teamService.updateTeam(teamId, mockTeamDTO));

        //then
        assertEquals("Team id given in URL 1 and payLoad 1 should match", exception.getMessage());
    }

    @Test
    @DisplayName("Test update quarter DataIntegrityViolation Exception")
    public void testUpdateTeamDataIntegrityViolationException() {

        //given
        Long teamId = TEAM_ID_22;
        TeamDTO mockTeamDTO = updatingTeamDTOData(teamId);
        Team team = updatingTeamData(teamId);
        when(teamRepository.findById(teamId)).thenReturn(Optional.of(team));
        when(teamRepository.save(team)).thenThrow(new DataIntegrityViolationException(" update Team failed"));
        when(rewardsProperties.getExceptionErrorMessage()).thenReturn("Exception occurred in updateTeam");
        when(rewardsProperties.getTeamSaveFailed()).thenReturn("Failed to save Team");

        //when
        ApplicationException exception = assertThrows(ApplicationException.class, () -> teamService.updateTeam(teamId, mockTeamDTO));

        //then
        assertEquals("Failed to save Team", exception.getMessage());
    }

    @Test
    @DisplayName("Test delete team by ID")
    public void testDeleteTeamById() {

        //given
        Long teamId = TEAM_ID_1;
        Team existingTeam = createExistingTeam(teamId);
        when(teamRepository.findById(teamId)).thenReturn(Optional.of(existingTeam));
        when(teamRepository.save(Mockito.any(Team.class))).thenReturn(existingTeam);

        //when and then
        assertDoesNotThrow(() -> teamService.deleteTeam(teamId));
    }

    @Test
    @DisplayName("Test delete non-existent team by ID")
    public void testDeleteNonExistentTeamById() {

        //given
        Long teamId = TEAM_ID_1;
        when(teamRepository.findById(teamId)).thenReturn(Optional.empty());
        when(rewardsProperties.getTeamNotAvailableForId()).thenReturn("Team details not present in the system");

        //when
        ApplicationException exception = assertThrows(ApplicationException.class, () -> teamService.deleteTeam(teamId));

        //then
        assertEquals("Team details not present in the system", exception.getMessage());
    }

    @Test
    @DisplayName("Test delete team DataIntegrityViolation Exception by ID")
    public void testDeleteTeamDataIntegrityViolationException() {

        //given
        Long teamId = TEAM_ID_88;
        Team team = createTeam(teamId);
        when(teamRepository.findById(teamId)).thenReturn(Optional.of(team));
        when(teamRepository.save(team)).thenThrow(new DataIntegrityViolationException("Failed to save Team"));
        when(rewardsProperties.getExceptionErrorMessage()).thenReturn("Exception occurred in DeleteTeam");

        //when
        ApplicationException exception = assertThrows(ApplicationException.class, () -> teamService.deleteTeam(teamId));

        //then
        assertEquals("Failed to save Team", exception.getMessage());
    }
}