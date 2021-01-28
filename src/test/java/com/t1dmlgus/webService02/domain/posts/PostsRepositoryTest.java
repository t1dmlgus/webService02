package com.t1dmlgus.webService02.domain.posts;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

}