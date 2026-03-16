package com.example.backend.controller;

import com.example.backend.dto.ApiResponse;
import com.example.backend.dto.SchemeDTO;
import com.example.backend.entity.CasteType;
import com.example.backend.entity.CategoryType;
import com.example.backend.entity.GenderType;
import com.example.backend.service.SchemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schemes")
@RequiredArgsConstructor
@CrossOrigin
public class SchemeController {

    private final SchemeService schemeService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<SchemeDTO>>> getSchemes(
            @RequestParam(required = false) CategoryType category,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) GenderType gender,
            @RequestParam(required = false) CasteType caste,
            @RequestParam(required = false) Boolean disability,
            @RequestParam(required = false) Boolean bpl,
            @RequestParam(required = false) Boolean widow,
            @RequestParam(required = false) Boolean minority,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,desc") String[] sort) {

        Sort sortOrder = Sort.by(sort[0]);
        if (sort.length > 1 && sort[1].equalsIgnoreCase("asc")) {
            sortOrder = sortOrder.ascending();
        } else {
            sortOrder = sortOrder.descending();
        }

        Pageable pageable = PageRequest.of(page, size, sortOrder);
        Page<SchemeDTO> schemes = schemeService.getFilteredSchemes(
                category, state, gender, caste, disability, bpl, widow, minority, pageable);
        
        return ResponseEntity.ok(ApiResponse.success("Schemes retrieved successfully", schemes));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<SchemeDTO>> createScheme(@RequestBody SchemeDTO schemeDTO) {
        return ResponseEntity.ok(ApiResponse.success("Scheme created successfully", schemeService.createScheme(schemeDTO)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SchemeDTO>> updateScheme(@PathVariable Long id, @RequestBody SchemeDTO schemeDetails) {
        return ResponseEntity.ok(ApiResponse.success("Scheme updated successfully", schemeService.updateScheme(id, schemeDetails)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteScheme(@PathVariable Long id) {
        schemeService.deleteScheme(id);
        return ResponseEntity.ok(ApiResponse.success("Scheme deleted successfully", "Deleted ID: " + id));
    }
}
