package com.poc.sst.primitive;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Entity {

    @Setter(AccessLevel.PROTECTED)
    private String id;

    @Setter(AccessLevel.PROTECTED)
    private Long timeStamp;
}
