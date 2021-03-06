package com.example.demo.controller;

import com.example.demo.model.Group;
import com.example.demo.repository.GroupRepository;
import com.example.demo.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/groups")
@CrossOrigin
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public ResponseEntity<List<Group>> getGroups(){
        return ResponseEntity.ok(groupService.findAllGroups());
    }

    @PostMapping(path = "/auto-grouping")
    public ResponseEntity<List<Group>> autoGrouping(){
        return ResponseEntity.ok(groupService.autoGrouping());
    }
}
