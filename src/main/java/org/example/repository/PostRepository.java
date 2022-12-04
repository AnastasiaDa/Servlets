package org.example.repository;

import org.example.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

// Stub
public class PostRepository {

    private Map<Long, Post> posts = new ConcurrentHashMap<>();
    private AtomicLong idCount = new AtomicLong(0);

    public List<Post> all() {
        return new ArrayList<>(posts.values());
    }

    public Optional<Post> getById(long id) {
        if (posts.containsKey(id)) {
            return Optional.of(posts.get(id));
        } else {
            return Optional.empty();
        }
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            long newId = idCount.incrementAndGet();
            post.setId(newId);
            posts.put(newId, post);
        } else {
            if (getById(post.getId()).isPresent()) {
                posts.put(post.getId(), post);
            } else {
                return null;
            }
        }
        return post;
    }

    public void removeById(long id) {
        posts.remove(id);
    }
}
