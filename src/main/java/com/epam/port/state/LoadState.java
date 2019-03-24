package com.epam.port.state;

import com.epam.port.entity.Dock;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value=FullState.class, name="FullState"),
        @JsonSubTypes.Type(value=EmptyState.class, name="EmptyState")
})
public interface LoadState {
    void process(Dock dock) throws InterruptedException;
}
