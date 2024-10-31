package com.shailendra.writerous.service.impl;

import com.shailendra.writerous.dto.PostDto;
import com.shailendra.writerous.entity.Post;
import com.shailendra.writerous.entity.User;
import com.shailendra.writerous.mapper.PostMapper;
import com.shailendra.writerous.repository.PostRepository;
import com.shailendra.writerous.repository.UserRepository;
import com.shailendra.writerous.service.PostService;
import com.shailendra.writerous.util.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository,
                           UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }
    @Override
    public List<PostDto> findAllPosts() {
        List<Post> postList = postRepository.findAll();
        return postList.stream().map(PostMapper::mapToPostDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> findPostsByUser() {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User createdBy = userRepository.findByEmail(email);
        Long userId = createdBy.getId();
        List<Post> posts = postRepository.findPostsByUser(userId);
        return posts.stream()
                .map((post) -> PostMapper.mapToPostDto(post))
                .collect(Collectors.toList());
    }

    @Override
    public void createPost(PostDto postDto) {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User user = userRepository.findByEmail(email);
        Post post = PostMapper.mapToPost(postDto);
        post.setCreatedBy(user);
        postRepository.save(post);
    }


    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).get();
        return PostMapper.mapToPostDto(post);
    }

    @Override
    public void updatePost(PostDto postDto) {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User createdBy = userRepository.findByEmail(email);
        Post post = PostMapper.mapToPost(postDto);
        post.setCreatedBy(createdBy);
        postRepository.save(post);
    }

    @Override
    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public PostDto getPostByUrl(String postUrl) {
        Post post = postRepository.findByUrl(postUrl).get();
        return PostMapper.mapToPostDto(post);
    }

    @Override
    public List<PostDto> searchPosts(String query) {
        List<Post> postsList = postRepository.searchPosts(query);
        return postsList.stream().map(PostMapper::mapToPostDto).collect(Collectors.toList());
    }
}
