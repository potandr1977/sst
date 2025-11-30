package com.poc.sst.primitive;

public abstract class EventHandler<TRequest, TResponse> {

    public abstract TResponse handle(TRequest request);

    protected void CheckForRights()
    {
        // Gonna check the rights and throw exception if zero rights.
    }
}
