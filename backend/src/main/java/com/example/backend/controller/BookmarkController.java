package com.example.backend.controller;

import com.example.backend.dto.ApiResponse;
import com.example.backend.dto.SchemeDTO;
import com.example.backend.service.UserBookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
@CrossOrigin
public class BookmarkController {

    private final UserBookmarkService bookmarkService;

    @PostMapping("/{schemeId}")
    public ResponseEntity<ApiResponse<String>> addBookmark(
            @PathVariable Long schemeId, Authentication authentication) {
        bookmarkService.addBookmark(authentication.getName(), schemeId);
        return ResponseEntity.ok(ApiResponse.success("Scheme bookmarked successfully", "ID: " + schemeId));
    }

    @DeleteMapping("/{schemeId}")
    public ResponseEntity<ApiResponse<String>> removeBookmark(
            @PathVariable Long schemeId, Authentication authentication) {
        bookmarkService.removeBookmark(authentication.getName(), schemeId);
        return ResponseEntity.ok(ApiResponse.success("Bookmark removed successfully", "ID: " + schemeId));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<SchemeDTO>>> getBookmarks(Authentication authentication) {
        List<SchemeDTO> bookmarks = bookmarkService.getBookmarkedSchemes(authentication.getName());
        return ResponseEntity.ok(ApiResponse.success("Bookmarks retrieved successfully", bookmarks));
    }
}
