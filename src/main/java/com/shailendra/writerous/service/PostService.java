package com.shailendra.writerous.service;

import com.shailendra.writerous.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> findAllPosts();
    List<PostDto> findPostsByUser();
    void createPost(PostDto postDto);
    PostDto getPostById(Long id);
    void updatePost(PostDto post);
    void deletePostById(Long id);
    PostDto getPostByUrl(String postUrl);
    List<PostDto> searchPosts(String query);
}
