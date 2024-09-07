package com.sparta.ordersystem.order.management.category.controller;

import com.sparta.ordersystem.order.management.category.dto.*;
import com.sparta.ordersystem.order.management.category.service.CategoryService;

import com.sparta.ordersystem.order.management.user.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<CategoryCreateResponseDto> createCategory(@RequestBody @Valid  CategoryCreateRequestDto categoryCreateRequestDto,
                                                    @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.ok(
                categoryService.createCategory(categoryCreateRequestDto, userDetails.getUser())
        );
    }


    @GetMapping("/category/{category_id}")
    public ResponseEntity<CategoryGetResponseDto> getCategory(@PathVariable UUID category_id){
        return ResponseEntity.ok(
                categoryService.getCategory(category_id)
        );
    }

    @GetMapping("/category")
    public ResponseEntity<Page<CategoryGetResponseDto>> getAllCategories(@RequestParam("page") int page,
                                                                         @RequestParam("size") int size,
                                                                         @RequestParam("sortBy") String sortBy,
                                                                         @RequestParam("isAsc") boolean isAsc){
        return ResponseEntity.ok(
                categoryService.getAllCategory(page-1, size, sortBy, isAsc)
        );
    }


    @PatchMapping("/category/{category_id}")
    public ResponseEntity<CategoryUpdateResponseDto> updateCategory(@PathVariable UUID category_id,
                                                    @RequestBody @Valid CategoryUpdateRequestDto categoryUpdateRequestDto,
                                                    @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.ok(
                categoryService.updateCategory(category_id,categoryUpdateRequestDto, userDetails.getUser())
        );
    }


    @DeleteMapping("/category/{category_id}")
    public ResponseEntity<CategoryDeleteResponseDto> deleteCategory(@PathVariable UUID category_id,
                                                    @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.ok(
                categoryService.deleteCategory(category_id, userDetails.getUser())
        );
    }


}
