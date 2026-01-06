package com.thc.sprbasic2025fall.domain;

import com.thc.sprbasic2025fall.dto.DefaultDto;
import com.thc.sprbasic2025fall.dto.PermissionDto;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Permission extends AuditingFields {
    @Setter String title;
    @Setter String content;

    protected Permission(){}
    private Permission(String title, String content) {
        this.title = title;
        this.content = content;
    }
    public static Permission of(String title, String content){
        return new Permission(title, content);
    }
    public DefaultDto.CreateResDto toCreateResDto(){
        return DefaultDto.CreateResDto.builder().id(getId()).build();
    }
    public void update(PermissionDto.UpdateReqDto param){
        if(param.getDeleted() != null){ setDeleted(param.getDeleted()); }
        if(param.getTitle() != null){ setTitle(param.getTitle()); }
        if(param.getContent() != null){ setContent(param.getContent()); }
    }
}
