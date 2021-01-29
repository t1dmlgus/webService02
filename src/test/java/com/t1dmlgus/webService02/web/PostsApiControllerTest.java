package com.t1dmlgus.webService02.web;

import com.t1dmlgus.webService02.domain.posts.Posts;
import com.t1dmlgus.webService02.domain.posts.PostsRepository;
import com.t1dmlgus.webService02.web.dto.PostsSaveRequestDto;
import com.t1dmlgus.webService02.web.dto.PostsUpdateRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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


    @Test
    public void Posts_수정() throws Exception{

        //given


        // 등록 조건
        String title = "타이02틀";
        String content = "컨텐02트";

        Posts savePosts = postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("t1dmlgus")
                .build());



        // 업데이트 조건
        Long updateid = savePosts.getId();
        String expectedTitle = "타이03틀";
        String expectedContent = "컨텐03트";


        // 업데이트 할 Dto 작성
        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();


        // uri
        String url = "http://localhost:" + port + "/api/v1/posts/"+ updateid;

        // 업데이트 할 객체
        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);




        //when
        
        // restTemplate로 요청한 객체를 RestponseEntity 객체로 응답받음
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        System.out.println("responseEntity.getBody() = " + responseEntity.getBody().toString());
        

        //then


        // 응답받은 객체의 응답코드가 ok인지 확인
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(responseEntity.getBody()).isGreaterThan(0L);


        List<Posts> all = postsRepository.findAll();


        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);



    }

}