package com.seanlab.dalin.mlkit.ghost.event;

import java.util.List;

import com.seanlab.dalin.mlkit.ghost.model.entity.Setting;

public class BlogSettingsLoadedEvent {

    public final List<Setting> settings;

    public BlogSettingsLoadedEvent(List<Setting> settings) {
        this.settings = settings;
    }

}
