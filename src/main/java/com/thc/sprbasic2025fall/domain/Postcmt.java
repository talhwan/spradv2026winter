package com.thc.sprbasic2025fall.domain;

import com.thc.sprbasic2025fall.dto.DefaultDto;
import com.thc.sprbasic2025fall.dto.PostcmtDto;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Postcmt extends AuditingFileds{
    @Setter Long postingId;
    @Setter Long userId;
    @Setter String content;

    protected Postcmt(){}
    private Postcmt(Long postingId, Long userId, String content){
        this.postingId = postingId;
        this.userId = userId;
        this.content = content;
    }
    //이 메서드를 통해서만, 엔티티 인스턴스를 만들수 있도록 강제!!
    public static Postcmt of(Long postingId, Long userId, String content){
        return new Postcmt(postingId, userId, content);
    }
    public DefaultDto.CreateResDto toCreateResDto(){
        return DefaultDto.CreateResDto.builder().id(getId()).build();
    }
    public void update(PostcmtDto.UpdateReqDto param){
        if(param.getDeleted() != null){ setDeleted(param.getDeleted()); }
        if(param.getContent() != null){ setContent(param.getContent()); }
    }
}
