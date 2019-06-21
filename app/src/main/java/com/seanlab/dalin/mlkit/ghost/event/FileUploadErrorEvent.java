package com.seanlab.dalin.mlkit.ghost.event;

import com.seanlab.dalin.mlkit.ghost.network.ApiFailure;

@SuppressWarnings({"WeakerAccess", "unused"})
public class FileUploadErrorEvent {

    public final ApiFailure apiFailure;

    public FileUploadErrorEvent(ApiFailure apiFailure) {
        this.apiFailure = apiFailure;
    }

}
