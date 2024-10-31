package com.shailendra.writerous.controller;

import com.shailendra.writerous.dto.CommentDto;
import com.shailendra.writerous.dto.PostDto;
import com.shailendra.writerous.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BlogController {

    PostService postService;

    public BlogController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public String viewPosts(Model model) {
        List<PostDto> postDtoList = postService.findAllPosts();
        model.addAttribute("postsResponse", postDtoList);
        return "blog/view_posts";
    }

    @GetMapping("/post/{postUrl}")
    public String showPost(@PathVariable String postUrl,
                           Model model) {
        PostDto postDto = postService.getPostByUrl(postUrl);
        CommentDto commentDto = new CommentDto();
        model.addAttribute("post", postDto);
        model.addAttribute("comment", commentDto);
        return "blog/blog_post";
    }
    @GetMapping("/page/search")
    public String searchPosts(@RequestParam String query,
                              Model model) {
        List<PostDto> postDtoList = postService.searchPosts(query);
        model.addAttribute("postsResponse", postDtoList);
        return "blog/view_posts";
    }
}
