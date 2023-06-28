package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.dto.GroupDto;
import com.upc.TuCine.TuCine.dto.PersonDto;
import com.upc.TuCine.TuCine.dto.save.Group.GroupSaveDto;
import com.upc.TuCine.TuCine.service.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@Tag(name = "Groups", description = "API de Groups")
@RequestMapping("/api/TuCine/v1")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @Transactional(readOnly = true)
    @GetMapping("/groups")
    @Operation(summary = "Obtener todos los grupos")
    @ApiResponse(
            responseCode = "200",
            description = "Se obtuvo la lista de grupos"
    )
    public ResponseEntity<List<GroupDto>> getAllGroups() {
        List<GroupDto> groups = groupService.getAllGroups();
        return ResponseEntity.ok(groups);
    }

    @Transactional
    @PostMapping("/groups")
    @Operation(summary = "Crear un grupo")
    @ApiResponse(
            responseCode = "201",
            description = "Se creó el grupo"
    )
    public ResponseEntity<GroupDto> createGroup(@RequestBody GroupSaveDto groupSaveDto) {
        GroupDto createdGroup = groupService.createGroup(groupSaveDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGroup);
    }

    @Transactional(readOnly = true)
    @GetMapping("/groups/{id}/person")
    @Operation(summary = "Obtener la persona creadora de un grupo")
    @ApiResponse(
            responseCode = "200",
            description = "Se obtuvo la persona creadora del grupo"
    )
    public ResponseEntity<PersonDto> getPersonByGroupId(@PathVariable Integer id) {
        PersonDto person = groupService.getPersonByGroupId(id);
        if(person == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping("/groups/{groupId}/topics/{topicId}")
    @Operation(summary = "Agregar un tema a un grupo")
    @ApiResponse(
            responseCode = "200",
            description = "Se agregó el tema al grupo"
    )
    public ResponseEntity<String > addTopicToGroup(@PathVariable("groupId") Integer groupId, @PathVariable("topicId") Integer topicId) {
        groupService.addTopicToGroup(groupId, topicId);
        return ResponseEntity.ok("Se agregó el tema al grupo" );
    }
}