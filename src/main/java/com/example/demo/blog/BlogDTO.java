package com.example.demo.blog;

import com.example.demo.location.Location;
import com.example.demo.user.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
@Builder
public class BlogDTO {
    private Long id;
    private Long userId;
    private Long locationId;
    private String title;
    private String content;
    @Nullable
    private String photoURL;
    @Nullable
    private String video;

    public Blog convertToBlog(User user, Location location) {
        return Blog.builder()
                .id(this.id)
                .user(user)
                .location(location)
                .title(this.title)
                .content(this.content)
                .photoURL(this.photoURL)
                .video(this.video)
                .build();
    }
}
