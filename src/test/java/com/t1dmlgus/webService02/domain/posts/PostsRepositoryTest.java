package com.t1dmlgus.webService02.domain.posts;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;


    @Test
    public void 게시글_불러오기(){

        //given

        String title = "테스트 게시01글";
        String content = "테스트 본01문";

        postsRepository.save(Posts.builder()
                .title("테스트 게시01글")
                .content("테스트 본01문")
                .author("t1dmlgus")
                .build());


        //when

        List<Posts> postsList = postsRepository.findAll();


        //then

        Posts posts = postsList.get(0);

        assertThat(posts.getTitle()).isEqualTo("테스트 게시01글");
        assertThat(posts.getContent()).isEqualTo("테스트 본01문");
        


    }

    @Test
    public void BaseTimeEntity_등록(){

        //given

                // 현재 시간 LodalDateTime

        LocalDateTime now = LocalDateTime.now();
       // String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        System.out.println(">>>>>>save111 = " + now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        
        postsRepository.save(Posts.builder()
                .title("타이03틀")
                .content("타이03틀")
                .author("t1dmlgu03s")
                .build());

        System.out.println(">>>>>>saved222 = " + now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        //when

        List<Posts> postsList = postsRepository.findAll();

        //then

        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>>>>>>>>posts.getCreatedDate()"
                + posts.getCreatedDate() + "posts.modifiedDate ="+ posts.getModifiedDate());


        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }


}