package com.example.demo.comment;

import com.example.demo.blog.Blog;
import com.example.demo.user.User;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Blog blog;

    private Timestamp dateAndTime;

    CommentDTO convertToCommentDTO(){
        return CommentDTO.builder()
                .id(this.id)
                .userId(this.user.getId())
                .content(this.content)
                .blogId(this.blog.getId())
                .dateAndTime(this.dateAndTime)
                .build();
    }
}
