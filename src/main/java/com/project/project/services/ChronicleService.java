package com.project.project.services;

import com.project.project.dto.ChronicleDto;
import com.project.project.dto.GalleryDto;
import com.project.project.mappers.ChronicleMapper;
import com.project.project.mappers.GalleryMapper;
import com.project.project.models.Chronicle;
import com.project.project.models.Gallery;
import com.project.project.repositories.ChronicleRepository;
import com.project.project.repositories.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChronicleService {

    @Autowired
    private ChronicleRepository chronicleRepository;

    @Autowired
    private GalleryRepository galleryRepository;

    @Autowired
    private GalleryMapper galleryMapper;

    @Autowired
    private ChronicleMapper chronicleMapper;

    @Transactional
    public Chronicle createChronicle(ChronicleDto chronicleDto) {
        GalleryDto galleryDto = new GalleryDto();
        galleryDto.setFamilyId(chronicleDto.getFamilyId());
        galleryDto.setName(chronicleDto.getName() + "Gallery");
        galleryDto.setOwnerId(chronicleDto.getAuthorId());
        Gallery gallery = galleryRepository.save(galleryMapper.dtoToObject(galleryDto));
        chronicleDto.setGalleryId(gallery.getId());
        return chronicleRepository.save(chronicleMapper.dtoToObject(chronicleDto));
    }


}
