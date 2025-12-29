package com.thc.sprbasic2025fall.domain;

import com.thc.sprbasic2025fall.dto.DefaultDto;
import com.thc.sprbasic2025fall.dto.PostlikeDto;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Postlike extends AuditingFileds{
    @Setter Long postingId;
    @Setter Long userId;

    protected Postlike(){}
    private Postlike(Long postingId, Long userId){
        this.postingId = postingId;
        this.userId = userId;
    }
    //이 메서드를 통해서만, 엔티티 인스턴스를 만들수 있도록 강제!!
    public static Postlike of(Long postingId, Long userId){
        return new Postlike(postingId, userId);
    }
    public DefaultDto.CreateResDto toCreateResDto(){
        return DefaultDto.CreateResDto.builder().id(getId()).build();
    }
    public void update(PostlikeDto.UpdateReqDto param){
        if(param.getDeleted() != null){ setDeleted(param.getDeleted()); }
    }
}
