package com.thc.sprbasic2025fall.controller;

import com.thc.sprbasic2025fall.dto.DefaultDto;
import com.thc.sprbasic2025fall.dto.PermissionDto;
import com.thc.sprbasic2025fall.security.PrincipalDetails;
import com.thc.sprbasic2025fall.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/permission")
@RestController
public class PermissionRestController {

    final PermissionService permissionService;

    public Long getUserId(PrincipalDetails principalDetails){
        if(principalDetails != null && principalDetails.getUser() != null){
            return principalDetails.getUser().getId();
        }
        return null;
    }

    @PreAuthorize("hasRole('USER')")
    //@PreAuthorize("permitAll()")
    @PostMapping("")
    public ResponseEntity<DefaultDto.CreateResDto> create(@RequestBody PermissionDto.CreateReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(permissionService.create(param, getUserId(principalDetails)));
    }
    @PreAuthorize("hasRole('USER')")
    @PutMapping("")
    public ResponseEntity<Void> update(@RequestBody PermissionDto.UpdateReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        permissionService.update(param, getUserId(principalDetails));
        return ResponseEntity.ok().build();
    }
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestBody PermissionDto.UpdateReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        permissionService.delete(param, getUserId(principalDetails));
        return ResponseEntity.ok().build();
    }
    @PreAuthorize("permitAll()")
    @GetMapping("")
    public ResponseEntity<PermissionDto.DetailResDto> detail(DefaultDto.DetailReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(permissionService.detail(param, getUserId(principalDetails)));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<List<PermissionDto.DetailResDto>> list(PermissionDto.ListReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(permissionService.list(param, getUserId(principalDetails)));
    }
    @PreAuthorize("permitAll()")
    @GetMapping("/pagedList")
    public ResponseEntity<DefaultDto.PagedListResDto> pagedList(PermissionDto.PagedListReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(permissionService.pagedList(param, getUserId(principalDetails)));
    }
    @PreAuthorize("permitAll()")
    @GetMapping("/scrollList")
    public ResponseEntity<List<PermissionDto.DetailResDto>> scrollList(PermissionDto.ScrollListReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(permissionService.scrollList(param, getUserId(principalDetails)));
    }

}
