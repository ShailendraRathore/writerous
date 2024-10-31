package com.shailendra.writerous.mapper;

import com.shailendra.writerous.dto.PostDto;
import com.shailendra.writerous.entity.Post;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.stream.Collectors;

public class PostMapper {

    public static PostDto mapToPostDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .url(post.getUrl())
                .content(post.getContent())
                .title(post.getTitle())
                .shortDescription(post.getShortDescription())
                .createdOn(post.getCreatedOn())
                .updatedOn(post.getUpdatedOn())
                .comments(post.getComments().stream()
                        .map(CommentMapper::mapToDto)
                        .collect(Collectors.toSet()))
                .build();
    }

    @GetMapping("/admin/posts/newpost")
    public static Post mapToPost(PostDto postDto) {
        return Post.builder()
                .id(postDto.getId())
                .url(postDto.getUrl())
                .content(postDto.getContent())
                .title(postDto.getTitle())
                .shortDescription(postDto.getShortDescription())
                .createdOn(postDto.getCreatedOn())
                .updatedOn(postDto.getUpdatedOn())
                .build();
    }
}
