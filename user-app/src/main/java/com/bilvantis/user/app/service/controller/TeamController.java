package com.bilvantis.user.app.service.controller;

import com.bilvantis.user.api.service.TeamService;
import com.bilvantis.user.app.service.model.UserResponseDTO;
import com.bilvantis.user.app.service.util.UserAppConstant;
import com.bilvantis.user.app.service.util.UserRequestResponseBuilder;
import com.bilvantis.user.data.model.TeamDTO;
import com.bilvantis.user.data.util.OnCreate;
import com.bilvantis.user.data.util.OnUpdate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/team")
@Validated
public class TeamController {

    @Autowired
    @Qualifier("teamServiceImpl")
    private TeamService<TeamDTO, Long> teamService;

    @GetMapping
    public ResponseEntity<UserResponseDTO> getAllTeams() {
        return new ResponseEntity<>(UserRequestResponseBuilder.buildResponseDTO(
                teamService.getAllTeams(), UserAppConstant.SUCCESS, null),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getTeamById(@PathVariable @NotNull Long id) {
        return new ResponseEntity<>(UserRequestResponseBuilder.buildResponseDTO(
                teamService.getTeamById(id), UserAppConstant.SUCCESS, null),
                HttpStatus.OK);
    }

    @Validated(OnCreate.class)
    @PostMapping
    public ResponseEntity<TeamDTO> createTeam
            (@NotNull @Valid @RequestBody TeamDTO teamDTO) {
        return new ResponseEntity<>(teamService.createTeam(teamDTO), HttpStatus.CREATED);
    }

    @Validated(OnUpdate.class)
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateTeam
            (@NotNull @PathVariable Long id, @NotNull @Valid @RequestBody TeamDTO teamDTO) {
        return new ResponseEntity<>(UserRequestResponseBuilder.buildResponseDTO(
                teamService.updateTeam(id, teamDTO), UserAppConstant.SUCCESS, null),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteTeamById(@NotNull @PathVariable Long id) {
        teamService.deleteTeam(id);
    }
}
