package com.mss.weather.domain;

import com.mss.weather.domain.models.Position;

import io.reactivex.Maybe;

public interface SensorsRepository {
    Maybe<Position> getPosition();
}
