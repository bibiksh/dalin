package com.seanlab.dalin.mlkit.ghost.event;

import com.seanlab.dalin.mlkit.ghost.model.entity.Post;

public class PostSyncedEvent {

    public final Post post;

    public PostSyncedEvent(Post post) {
        this.post = post;
    }

}
