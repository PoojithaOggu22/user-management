package com.bilvantis.user.api.service.impl;

import com.bilvantis.user.api.exception.ApplicationException;
import com.bilvantis.user.api.service.TeamService;
import com.bilvantis.user.api.util.UserProperties;
import com.bilvantis.user.api.util.TeamSupport;
import com.bilvantis.user.dao.data.model.Team;
import com.bilvantis.user.dao.data.repository.TeamRepository;
import com.bilvantis.user.data.model.TeamDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("teamServiceImpl")
@Slf4j
public class TeamServiceImpl implements TeamService<TeamDTO, Long> {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserProperties userProperties;

    /**
     * Retrieves a list  teams
     *
     * @return A list TeamDTO
     */
    @Override
    public List<TeamDTO> getAllTeams() {
        try {
            List<Team> teams = teamRepository.findAll();
            if (CollectionUtils.isNotEmpty(teams)) {
                return teams.stream()
                        .map(TeamSupport::convertTeamEntityToTeamDto)
                        .collect(Collectors.toList());
            } else {
                throw new ApplicationException(userProperties.getTeamsNotAvailable());
            }
        } catch (DataAccessException e) {
            log.error(String.format(userProperties.getExceptionErrorMessage(), this.getClass().getSimpleName(),
                    e.getStackTrace()[0].getMethodName()), e);
            throw new ApplicationException(e.getMessage());
        }
    }

    /**
     * Retrieves a specific team
     *
     * @param id Id
     * @return TeamDTO
     */
    @Override
    public TeamDTO getTeamById(Long id) {
        try {
            // Check if the team ID is not null
            if (Objects.nonNull(id)) {
                Optional<Team> optionalTeam = teamRepository.findById(id);
                // Check if the team exists in the database
                if (optionalTeam.isPresent()) {
                    return TeamSupport.convertTeamEntityToTeamDto(optionalTeam.get());
                }
                throw new ApplicationException(String.format(userProperties.getTeamNotAvailableForId(), id));
            }
            throw new ApplicationException(String.format(userProperties.getTeamIdNotNull(), id));
        } catch (DataAccessException e) {
            log.error(String.format(userProperties.getExceptionErrorMessage(), this.getClass().getSimpleName(),
                    e.getStackTrace()[0].getMethodName()), e);
            throw new ApplicationException(userProperties.getTeamsNotAvailable(), e);
        }
    }

    /**
     * Creates a new team
     *
     * @param teamDTO TeamDTO
     * @return TeamDTO
     */
    @Override
    public TeamDTO createTeam(TeamDTO teamDTO) {
        try {
            Team team = TeamSupport.convertTeamDtoToTeamEntity(teamDTO);
            return TeamSupport.convertTeamEntityToTeamDto(teamRepository.save(team));
        } catch (DataAccessException e) {
            log.error(String.format(userProperties.getExceptionErrorMessage(), this.getClass().getSimpleName(),
                    e.getStackTrace()[0].getMethodName()), e);
            throw new ApplicationException(userProperties.getTeamSaveFailed(), e);
        }
    }

    /**
     * Updates an existing team's information
     *
     * @param id Id
     * @param teamDTO TeamDTO
     * @return The updated TeamDTO
     */
    @Override
    public TeamDTO updateTeam(Long id, TeamDTO teamDTO) {
        try {
            // Check if the URL ID and payload ID match
            if (id.compareTo(teamDTO.getId()) != 0) {
                throw new ApplicationException(String.format(userProperties.getTeamIdMismatch(), id, teamDTO.getId()));
            }
            Team existingTeam = teamRepository.findById(id)
                    .orElseThrow(() -> new ApplicationException(String.format(userProperties.getTeamsNotAvailable())));
            TeamSupport.updateTeamFromTeamDTO(existingTeam, teamDTO);
            return TeamSupport.convertTeamEntityToTeamDto(teamRepository.save(existingTeam));
        } catch (DataAccessException e) {
            log.error(String.format(userProperties.getExceptionErrorMessage(), this.getClass().getSimpleName(),
                    e.getStackTrace()[0].getMethodName()), e);
            throw new ApplicationException(userProperties.getTeamSaveFailed(), e);
        }
    }

    /**
     * soft delete a team
     *
     * @param id Id
     */
    @Override
    public void deleteTeam(Long id) {
        try {
            Optional<Team> optionalTeam = teamRepository.findById(id);
            // Check if the team data exists in the database
            if (optionalTeam.isPresent()) {
                Team team = optionalTeam.get();
                team.setIsActive(false);
                teamRepository.save(team);
            } else {
                throw new ApplicationException(String.format(userProperties.getTeamNotAvailableForId(), id));
            }
        } catch (DataAccessException e) {
            log.error(String.format(userProperties.getExceptionErrorMessage(), this.getClass().getSimpleName(), e.getStackTrace()[0].getMethodName()), e);
            throw new ApplicationException(e.getMessage());
        }
    }

}
