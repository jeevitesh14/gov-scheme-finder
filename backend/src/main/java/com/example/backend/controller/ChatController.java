package com.example.backend.controller;

import com.example.backend.dto.ApiResponse;
import com.example.backend.dto.SchemeDTO;
import com.example.backend.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chatbot")
@CrossOrigin
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/query")
    public ResponseEntity<ApiResponse<List<SchemeDTO>>> queryChatbot(@RequestBody Map<String, String> request) {
        String query = request.get("query");
        if (query == null || query.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Query cannot be empty"));
        }
        
        List<SchemeDTO> results = chatService.querySchemes(query);
        return ResponseEntity.ok(ApiResponse.success("Results found", results));
    }
}
