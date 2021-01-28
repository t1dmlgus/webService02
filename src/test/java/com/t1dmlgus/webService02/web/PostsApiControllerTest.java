package com.t1dmlgus.webService02.web;

import com.t1dmlgus.webService02.domain.posts.Posts;
import com.t1dmlgus.webService02.domain.posts.PostsRepository;
import com.t1dmlgus.webService02.web.dto.PostsSaveRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    public void tearDown() throws Exception{

        postsRepository.deleteAll();
    }



    @Test
    public void Posts_등록()throws Exception{
        //given

        String title = "타이틀01";
        String content = "콘텐트01";

        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                                                            .title(title)
                                                            .content(content)
                                                            .author("t1dmlgus01")
                                                            .build();

//        Posts posts = Posts.builder().title(title).content(content).author("t1dmlgus01").build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        //when

        System.out.println("requestDto = " + requestDto);
        System.out.println("requestDto = " + requestDto.getTitle());



        // REST API -> POST
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);


        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);


        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);


    }

}