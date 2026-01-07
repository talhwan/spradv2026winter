package com.thc.sprbasic2025fall.domain;

import com.thc.sprbasic2025fall.dto.DefaultDto;
import com.thc.sprbasic2025fall.dto.PermissionuserDto;
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
                ,@Index(columnList = "userId")
        }
        ,uniqueConstraints = {@UniqueConstraint(
        name = "UQ_permissionuser_permissionId_userId"
        ,columnNames = {"permissionId", "userId"}
)}
)
@Entity
public class Permissionuser extends AuditingFields {
    Long permissionId;
    Long userId;

    protected Permissionuser(){}
    private Permissionuser(Long permissionId, Long userId) {
        this.permissionId = permissionId;
        this.userId = userId;
    }
    public static Permissionuser of(Long permissionId, Long userId){
        return new Permissionuser(permissionId, userId);
    }
    public DefaultDto.CreateResDto toCreateResDto(){
        return DefaultDto.CreateResDto.builder().id(getId()).build();
    }
    public void update(PermissionuserDto.UpdateReqDto param){
        if(param.getDeleted() != null){ setDeleted(param.getDeleted()); }
    }
}
