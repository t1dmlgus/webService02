package com.t1dmlgus.webService02.web;

import com.t1dmlgus.webService02.domain.posts.PostsRepository;
import com.t1dmlgus.webService02.service.PostsService;
import com.t1dmlgus.webService02.web.dto.PostsResponseDto;
import com.t1dmlgus.webService02.web.dto.PostsSaveRequestDto;
import com.t1dmlgus.webService02.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {


    private final PostsService postsService;

    // 등록
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        System.out.println("api 넘어옴");
        return postsService.save(requestDto);
    }



    // 수정
    @PutMapping("/api/v1/posts/{id}")
    public Long update (@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){


        return postsService.update(id, requestDto);
    }

    // 조회
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){

        return postsService.findById(id);
    }

    // 삭제
    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){

        postsService.delete(id);
        return id;
    }

}
