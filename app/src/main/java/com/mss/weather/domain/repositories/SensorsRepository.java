package com.mss.weather.domain.repositories;

import android.support.annotation.NonNull;

import com.mss.weather.domain.models.Position;

import io.reactivex.Maybe;

public interface SensorsRepository {
    @NonNull
    Maybe<Position> getPosition();
}
