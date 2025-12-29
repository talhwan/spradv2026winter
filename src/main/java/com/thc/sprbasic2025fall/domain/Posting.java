package com.thc.sprbasic2025fall.domain;

import com.thc.sprbasic2025fall.dto.DefaultDto;
import com.thc.sprbasic2025fall.dto.PostingDto;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Posting extends AuditingFileds{
    @Setter Long userId;

    @Setter String title;
    @Setter String content;
    @Setter String img;
    @Setter Integer countlike;

    protected Posting(){}
    private Posting(Long userId, String title, String content, String img, Integer countlike) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.img = img;
        this.countlike = countlike;
    }
    //이 메서드를 통해서만, 엔티티 인스턴스를 만들수 있도록 강제!!
    public static Posting of(Long userId, String title, String content, String img){
        return new Posting(userId, title, content, img, 0);
    }
    public DefaultDto.CreateResDto toCreateResDto(){
        return DefaultDto.CreateResDto.builder().id(getId()).build();
    }
    public void update(PostingDto.UpdateReqDto param){
        if(param.getDeleted() != null){ setDeleted(param.getDeleted()); }
        if(param.getTitle() != null){ setTitle(param.getTitle()); }
        if(param.getContent() != null){ setContent(param.getContent()); }
        if(param.getImg() != null){ setImg(param.getImg()); }
    }
}
