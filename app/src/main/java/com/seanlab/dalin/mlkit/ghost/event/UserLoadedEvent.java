package com.seanlab.dalin.mlkit.ghost.event;

import com.seanlab.dalin.mlkit.ghost.model.entity.User;

public class UserLoadedEvent {

    public final User user;

    public UserLoadedEvent(User user) {
        this.user = user;
    }

}
