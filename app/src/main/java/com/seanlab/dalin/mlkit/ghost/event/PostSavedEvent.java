package com.seanlab.dalin.mlkit.ghost.event;

import com.seanlab.dalin.mlkit.ghost.model.entity.Post;

public class PostSavedEvent {

    public final Post post;

    public PostSavedEvent(Post post) {
        this.post = post;
    }

}
