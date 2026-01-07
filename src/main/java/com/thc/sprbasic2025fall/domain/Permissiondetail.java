package com.thc.sprbasic2025fall.domain;

import com.thc.sprbasic2025fall.dto.DefaultDto;
import com.thc.sprbasic2025fall.dto.PermissiondetailDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(
        indexes = {
                @Index(columnList = "deleted")
                ,@Index(columnList = "permissionId")
                ,@Index(columnList = "target")
                ,@Index(columnList = "func")
        }
        ,uniqueConstraints = {@UniqueConstraint(
        name = "UQ_permissiondetail_permissionId_target_func"
        ,columnNames = {"permissionId", "target", "func"}
)}
)
@Entity
public class Permissiondetail extends AuditingFields {
    Long permissionId;
    String target; // 권한으로 접근할수 있는 테이블명 (예. notice)
    Integer func; // 110 입력 120 수정 200 조회

    protected Permissiondetail(){}
    private Permissiondetail(Long permissionId, String target, Integer func) {
        this.permissionId = permissionId;
        this.target = target;
        this.func = func;
    }
    public static Permissiondetail of(Long permissionId, String target, Integer func){
        return new Permissiondetail(permissionId, target, func);
    }
    public DefaultDto.CreateResDto toCreateResDto(){
        return DefaultDto.CreateResDto.builder().id(getId()).build();
    }
    public void update(PermissiondetailDto.UpdateReqDto param){
        if(param.getDeleted() != null){ setDeleted(param.getDeleted()); }
    }
}
