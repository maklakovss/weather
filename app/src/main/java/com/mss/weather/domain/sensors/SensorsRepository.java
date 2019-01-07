package com.mss.weather.domain.sensors;

import com.mss.weather.domain.sensors.models.Position;

import io.reactivex.Maybe;

public interface SensorsRepository {
    Maybe<Position> getPosition();
}
