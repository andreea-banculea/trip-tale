package com.example.demo.blog;

import com.example.demo.location.Location;
import com.example.demo.location.LocationRepository;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;

    @Autowired
    public BlogService(BlogRepository blogRepository, UserRepository userRepository, LocationRepository locationRepository) {
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
    }

    public List<BlogDTO> getAllBlogs() {
        return blogRepository.findAll().stream().map(Blog::convertToBlogDTO).toList();
    }

    public BlogDTO getBlogById(Long id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Blog not found."));
        return blog.convertToBlogDTO();
    }

    public BlogDTO createBlog(BlogDTO blogDTO) {
        return blogRepository.save(validateAndConvertToBlog(blogDTO))
                .convertToBlogDTO();
    }

    public BlogDTO updateBlog(BlogDTO blogDTO) {
        if (!blogRepository.existsById(blogDTO.getId()))
            throw new EntityNotFoundException("Blog not found");
        return blogRepository.save(validateAndConvertToBlog(blogDTO))
                .convertToBlogDTO();
    }

    public void deleteBlog(Authentication authentication, Long id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Blog not found."));
        if (authentication.getName().equals(blog.getUser().getEmail())) {
            blogRepository.deleteById(id);
        }
    }

    private Blog validateAndConvertToBlog(BlogDTO blogDTO) {
        User user = userRepository.findById(blogDTO.getUserId()).orElseThrow(() ->
                new EntityNotFoundException("User not found."));
        Location location = locationRepository.findById(blogDTO.getLocationId()).orElseThrow(() ->
                new EntityNotFoundException("Location not found."));
        return blogDTO.convertToBlog(user, location);
    }
}