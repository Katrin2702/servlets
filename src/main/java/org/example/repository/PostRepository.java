package org.example.repository;

import org.example.model.Post;
import org.example.exception.NotFoundException;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

// Stub
public class PostRepository {
    private final List<Post> listPost = new CopyOnWriteArrayList<>();
    private int size = 0;

    public List<Post> all() {
        return listPost;
    }

    public Optional<Post> getById(long id) {
        return listPost.stream()
                .filter(x -> x.getId() == id)
                .findAny();
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            post.setId(++size);
            listPost.add(post);
        } else {
            final var found = getById(post.getId()).orElseThrow(NotFoundException::new);
            final var index = listPost.indexOf(found);
            listPost.remove(found);
            listPost.add(index, post);
        }
        return post;
    }

    public void removeById(long id) {
        final var post = getById(id).orElseThrow(NotFoundException::new);
        listPost.remove(post);
    }
}