package com.example.news.utils;

import android.app.IntentService;
import android.content.Intent;
import androidx.annotation.Nullable;

class NotificationService extends IntentService {

    public NotificationService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
