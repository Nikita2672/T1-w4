package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser(roles = {"ADMIN", "ALBUMS_VIEWER", "ALBUMS_EDITORS"})
    void testSecuredAlbumsUrlAllowed() throws Exception {
        mockMvc.perform(get("/api/albums"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"POSTS_VIEWERS", "POST_EDITORS"})
    void testSecuredAlbumsUrlForbidden() throws Exception {
        mockMvc.perform(get("/api/albums"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "POSTS_VIEWER", "POSTS_EDITORS"})
    void testSecuredPostsUrlAllowed() throws Exception {
        mockMvc.perform(get("/api/posts"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"ALBUMS_VIEWER", "ALBUMS_EDITORS"})
    void testSecuredPostsUrlForbidden() throws Exception {
        mockMvc.perform(get("/api/posts"))
                .andExpect(status().isForbidden());
    }
}
