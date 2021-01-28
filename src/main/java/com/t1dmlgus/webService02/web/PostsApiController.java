package com.t1dmlgus.webService02.web;

import com.t1dmlgus.webService02.domain.posts.PostsRepository;
import com.t1dmlgus.webService02.service.PostsService;
import com.t1dmlgus.webService02.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostsApiController {


    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){

        return postsService.save(requestDto);
    }


}
