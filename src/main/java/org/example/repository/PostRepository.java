package org.example.repository;

import org.example.model.Post;
import org.example.exception.NotFoundException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// Stub
public class PostRepository {
    private static ConcurrentHashMap<Long, Post> posts = new ConcurrentHashMap<>();
    private static AtomicLong count = new AtomicLong(0);

    public List<Post> all() {
        return new ArrayList<>(posts.values());
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(posts.get(id));
    }


    public Post save(Post post) {
        if (post.getId() == 0) {
            long id = count.incrementAndGet();
            post.setId(id);
            posts.put(id, post);
        } else {
            getById(post.getId()).orElseThrow(NotFoundException::new);
            posts.put(post.getId(), post);
        }
        return post;
    }

    public void removeById(long id) {
        posts.remove(id);
    }
}