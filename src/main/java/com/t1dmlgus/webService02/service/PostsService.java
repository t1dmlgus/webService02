package com.t1dmlgus.webService02.service;

import com.t1dmlgus.webService02.domain.posts.Posts;
import com.t1dmlgus.webService02.domain.posts.PostsRepository;
import com.t1dmlgus.webService02.web.dto.PostsResponseDto;
import com.t1dmlgus.webService02.web.dto.PostsSaveRequestDto;
import com.t1dmlgus.webService02.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;


    // 등록
    @Transactional
    public Long save(PostsSaveRequestDto requestDto){

        return postsRepository.save(requestDto.toEntity()).getId();
    }


    // 수정
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {


        // 레퍼지토리에서 id에 맞는 Posts를 가져옴
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));


        // 비즈니스 로직
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;

    }

    // 조회
    public PostsResponseDto findById(Long id) {


        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);

    }



    
    
}
