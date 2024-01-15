package com.example.demo.comment;

import com.example.demo.blog.Blog;
import com.example.demo.user.User;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class CommentDTO {
    private Long id;
    private Long userId;
    private String content;
    private Long blogId;
    private Timestamp dateAndTime;

    Comment convertToComment(User user, Blog blog){
        return Comment.builder()
                .id(this.id)
                .user(user)
                .content(this.content)
                .blog(blog)
                .dateAndTime(this.dateAndTime)
                .build();
    }
}

