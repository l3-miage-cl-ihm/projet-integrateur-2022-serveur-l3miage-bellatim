package com.example.service;

import java.util.Optional;

import com.example.model.Media;
import com.example.repository.MediaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaService {
    
    @Autowired
    private MediaRepository mediaRepository;

    public Optional<Media> getMedia(final int id){
        return mediaRepository.findById(id);
    }

    public List<Media> getAllMedia(){
        return mediaRepository.findAll();
    }

    public void deleteMedia(final int id){
        mediaRepository.deleteById(id);
    }

    public Media saveMedia(Media media){
        return mediaRepository.save(media);
    }
}
