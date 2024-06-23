package com.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_POSTS_VIEWERS', 'ROLE_POSTS_EDITORS')")
    public ResponseEntity<String> getAllPosts() {
        return ResponseEntity.ok("posts");
    }
}
