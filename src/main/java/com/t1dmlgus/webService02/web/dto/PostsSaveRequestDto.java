package com.t1dmlgus.webService02.web.dto;

import com.t1dmlgus.webService02.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;


    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }


    // ** 객체 생성
    public Posts toEntity(){

        return Posts.builder()
                    .title(title)
                    .content(content)
                    .author(author)
                    .build();

    }

}
