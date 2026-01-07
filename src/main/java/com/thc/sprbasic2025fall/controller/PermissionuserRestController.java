package com.thc.sprbasic2025fall.controller;

import com.thc.sprbasic2025fall.dto.DefaultDto;
import com.thc.sprbasic2025fall.dto.PermissionuserDto;
import com.thc.sprbasic2025fall.security.PrincipalDetails;
import com.thc.sprbasic2025fall.service.PermissionuserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/permissionuser")
@RestController
public class PermissionuserRestController {

    final PermissionuserService permissionuserService;

    public Long getUserId(PrincipalDetails principalDetails){
        if(principalDetails != null && principalDetails.getUser() != null){
            return principalDetails.getUser().getId();
        }
        return null;
    }

    @PreAuthorize("hasRole('USER')")
    //@PreAuthorize("permitAll()")
    @PostMapping("")
    public ResponseEntity<DefaultDto.CreateResDto> create(@RequestBody PermissionuserDto.CreateReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(permissionuserService.create(param, getUserId(principalDetails)));
    }
    @PreAuthorize("hasRole('USER')")
    @PutMapping("")
    public ResponseEntity<Void> update(@RequestBody PermissionuserDto.UpdateReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        permissionuserService.update(param, getUserId(principalDetails));
        return ResponseEntity.ok().build();
    }
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestBody PermissionuserDto.UpdateReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        permissionuserService.delete(param, getUserId(principalDetails));
        return ResponseEntity.ok().build();
    }
    @PreAuthorize("permitAll()")
    @GetMapping("")
    public ResponseEntity<PermissionuserDto.DetailResDto> detail(DefaultDto.DetailReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(permissionuserService.detail(param, getUserId(principalDetails)));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<List<PermissionuserDto.DetailResDto>> list(PermissionuserDto.ListReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(permissionuserService.list(param, getUserId(principalDetails)));
    }
    @PreAuthorize("permitAll()")
    @GetMapping("/pagedList")
    public ResponseEntity<DefaultDto.PagedListResDto> pagedList(PermissionuserDto.PagedListReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(permissionuserService.pagedList(param, getUserId(principalDetails)));
    }
    @PreAuthorize("permitAll()")
    @GetMapping("/scrollList")
    public ResponseEntity<List<PermissionuserDto.DetailResDto>> scrollList(PermissionuserDto.ScrollListReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(permissionuserService.scrollList(param, getUserId(principalDetails)));
    }

}
