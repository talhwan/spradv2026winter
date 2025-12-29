package com.thc.sprbasic2025fall.domain;

import com.thc.sprbasic2025fall.dto.DefaultDto;
import com.thc.sprbasic2025fall.dto.PostimgDto;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Postimg extends AuditingFileds{
    @Setter Long postingId;
    @Setter String img;

    protected Postimg(){}
    private Postimg(Long postingId, String img){
        this.postingId = postingId;
        this.img = img;
    }
    //이 메서드를 통해서만, 엔티티 인스턴스를 만들수 있도록 강제!!
    public static Postimg of(Long postingId, String img){
        return new Postimg(postingId, img);
    }
    public DefaultDto.CreateResDto toCreateResDto(){
        return DefaultDto.CreateResDto.builder().id(getId()).build();
    }
    public void update(PostimgDto.UpdateReqDto param){
        if(param.getDeleted() != null){ setDeleted(param.getDeleted()); }
        if(param.getImg() != null){ setImg(param.getImg()); }
    }
}
