package com.project.project.controllers;

import com.project.project.dto.ChronicleDto;
import com.project.project.dto.FamilyDto;
import com.project.project.mappers.ChronicleMapper;
import com.project.project.repositories.ChronicleRepository;
import com.project.project.repositories.FamilyRepository;
import com.project.project.services.ChronicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/chronicle")
@CrossOrigin()
public class ChronicleController {


    @Autowired
    private ChronicleMapper chronicleMapper;
    @Autowired
    private ChronicleRepository chronicleRepository;
    @Autowired
    private FamilyRepository familyRepository;
    @Autowired
    private ChronicleService chronicleService;


    @RequestMapping(value = "/family/{familyId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<ChronicleDto> getChroniclesOfFamily(@PathVariable("familyId") Long familyId) {
        return chronicleMapper.objectToDto(chronicleRepository.findAllByFamilyId(familyRepository.findById(familyId).get()));
    }

    @RequestMapping(value = "/id/{chronicleId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ChronicleDto getChronicleById(@PathVariable("chronicleId") Long chronicleId) {
        return chronicleMapper.objectToDto(chronicleRepository.findById(chronicleId).get());
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ChronicleDto createChronicle(@RequestBody ChronicleDto chronicleDto) {
        return chronicleMapper.objectToDto(chronicleService.createChronicle(chronicleDto));
    }
}
