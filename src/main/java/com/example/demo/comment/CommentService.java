package com.example.demo.comment;

import com.example.demo.blog.Blog;
import com.example.demo.blog.BlogRepository;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BlogRepository blogRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, UserRepository userRepository, BlogRepository blogRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.blogRepository = blogRepository;
    }

    public List<CommentDTO> getAllComments() {
        return commentRepository.findAll().stream().map(Comment::convertToCommentDTO).toList();
    }

    public List<CommentDTO> getCommentsByBlogId(Long blogId) {
        return commentRepository.findByBlog_Id(blogId).stream().map(Comment::convertToCommentDTO).toList();
    }

    public CommentDTO getCommentById(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Comment not found."));
        return comment.convertToCommentDTO();
    }

    public CommentDTO createComment(CommentDTO commentDTO) {
        return commentRepository.save(validateAndConvertToComment(commentDTO))
                .convertToCommentDTO();
    }

    public CommentDTO updateComment(CommentDTO commentDTO) {
        if (!commentRepository.existsById(commentDTO.getId()))
            throw new EntityNotFoundException("Comment not found");
        return commentRepository.save(validateAndConvertToComment(commentDTO))
                .convertToCommentDTO();
    }

    public void deleteComment(Long id) {
        if (!commentRepository.existsById(id))
            throw new EntityNotFoundException("Comment not found");
        commentRepository.deleteById(id);
    }

    private Comment validateAndConvertToComment(CommentDTO commentDTO) {
        User user = userRepository.findById(commentDTO.getUserId()).orElseThrow(() ->
                new EntityNotFoundException("User not found."));
        Blog blog = blogRepository.findById(commentDTO.getBlogId()).orElseThrow(() ->
                new EntityNotFoundException("Blog not found."));
        return commentDTO.convertToComment(user, blog);
    }
}