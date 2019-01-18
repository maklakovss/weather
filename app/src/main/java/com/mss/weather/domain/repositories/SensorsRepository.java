package com.mss.weather.domain.repositories;

import com.mss.weather.domain.models.Position;

import io.reactivex.Maybe;

public interface SensorsRepository {
    Maybe<Position> getPosition();
}
