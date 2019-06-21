package com.seanlab.dalin.mlkit.ghost.event;

import com.seanlab.dalin.mlkit.ghost.model.entity.Post;

public class PostReplacedEvent {

    public final Post newPost;

    public PostReplacedEvent(Post newPost) {
        this.newPost = newPost;
    }

}
