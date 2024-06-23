package com.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/albums")
@RequiredArgsConstructor
public class AlbumController {

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ALBUMS_VIEWER', 'ROLE_ALBUMS_EDITORS')")
    public ResponseEntity<String> getAllAlbums() {
        return ResponseEntity.ok("albums");
    }
}
