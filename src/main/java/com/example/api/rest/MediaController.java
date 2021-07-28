package com.example.api.rest;

import com.example.api.dto.MediaSaveResponseDto;
import com.example.api.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/media")
@RequiredArgsConstructor
public class MediaController {
  private final MediaService service;

  @PostMapping
  public MediaSaveResponseDto save(@RequestPart MultipartFile file) {
    return service.save(file);
  }
}
