package com.seanlab.dalin.mlkit.ghost.event;

import androidx.annotation.NonNull;

import com.seanlab.dalin.mlkit.ghost.network.ApiFailure;

public class ApiErrorEvent {

    public final ApiFailure apiFailure;

    public ApiErrorEvent(@NonNull ApiFailure apiFailure) {
        this.apiFailure = apiFailure;
    }

}
