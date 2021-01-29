package com.t1dmlgus.webService02.web;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IndexControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void index_로딩(){

        //given


        //when

        String body = this.restTemplate.getForObject("/", String.class);
        //System.out.println("body = " + body);

        //then
        Assertions.assertThat(body).contains("스프링 부트 웹서비02스");


    }

}