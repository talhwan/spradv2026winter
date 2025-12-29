package com.thc.sprbasic2025fall.dto;

import com.thc.sprbasic2025fall.domain.Posting;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

public class DefaultDto {
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class CreateResDto{
        Long id;
    }
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class UpdateReqDto{
        Long id;
        Boolean deleted;
    }
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class DetailReqDto{
        Long id;
    }
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class DetailResDto{
        Long id;
        Boolean deleted;
        LocalDateTime createdAt;
        LocalDateTime modifiedAt;
    }
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class ListReqDto{
        Boolean deleted;
    }
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class PagedListReqDto{
        Integer callpage;
        Integer perpage;
        String orderby;
        String orderway;
        Integer offset; // 실제 호출할 순번

        Boolean deleted;

        public PagedListResDto init(int listcount){
            Integer callpage = getCallpage();
            if(callpage == null){
                callpage = 1;
            }
            if(callpage < 1){
                callpage = 1;
            }
            Integer perpage = getPerpage();
            if(perpage == null){
                perpage = 10;
            }
            if(perpage < 2){
                perpage = 10;
            }
            int totalpage = listcount / perpage;
            if(listcount % perpage > 0){
                totalpage++;
            }
            if(totalpage < 1){
                totalpage = 1;
            }
            if(callpage > totalpage){
                callpage = totalpage;
            }
            int offset = perpage * (callpage - 1);

            setOffset(offset);
            setPerpage(perpage);

            return PagedListResDto.builder().callpage(getCallpage()).totalpage(totalpage).listcount(listcount).build();
        }
    }
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class PagedListResDto{
        Integer callpage;
        Integer totalpage;
        Integer listcount;
        Object list;
    }


    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class ScrollListReqDto{
        Integer perpage;
        Long cursor;
        String orderway;

        Boolean deleted;
    }
}
