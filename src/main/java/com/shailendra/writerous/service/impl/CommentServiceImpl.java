package com.shailendra.writerous.service.impl;


import com.shailendra.writerous.dto.CommentDto;
import com.shailendra.writerous.entity.Comment;
import com.shailendra.writerous.entity.Post;
import com.shailendra.writerous.entity.User;
import com.shailendra.writerous.mapper.CommentMapper;
import com.shailendra.writerous.repository.CommentRepository;
import com.shailendra.writerous.repository.PostRepository;
import com.shailendra.writerous.repository.UserRepository;
import com.shailendra.writerous.service.CommentService;
import com.shailendra.writerous.util.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository,
                              PostRepository postRepository,
                              UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createComment(String postUrl, CommentDto commentDto) {

        Post post = postRepository.findByUrl(postUrl).get();
        Comment comment = CommentMapper.mapToComment(commentDto);
        comment.setPost(post);
        commentRepository.save(comment);
    }

    @Override
    public List<CommentDto> findAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream()
                .map(CommentMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public List<CommentDto> findCommentsByPost() {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User createdBy = userRepository.findByEmail(email);
        Long userId = createdBy.getId();
        List<Comment> comments = commentRepository.findCommentsByPost(userId);
        return comments.stream()
                .map((comment) -> CommentMapper.mapToDto(comment))
                .collect(Collectors.toList());
    }
}
