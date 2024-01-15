package com.example.demo.blog;

import com.example.demo.location.Location;
import com.example.demo.user.User;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table(name = "blogs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne
    private Location location;

    private String title;

    @Lob
    private String content;

    @Nullable
    private String photoURL;

    @Nullable
    private String video;

//    @Nullable
//    private String activityRecommendations;
//
//    @Nullable
//    private String accommodationRecommendations;

//    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
//    private List<Expense> expenses;
//
//    private String prosAndCons;

    public BlogDTO convertToBlogDTO() {
        return BlogDTO.builder()
                .id(this.getId())
                .userId(this.getUser().getId())
                .locationId(this.getLocation().getId())
                .title(this.title)
                .content(this.getContent())
                .photoURL(this.getPhotoURL())
                .video(this.getVideo())
                .build();
    }
}
