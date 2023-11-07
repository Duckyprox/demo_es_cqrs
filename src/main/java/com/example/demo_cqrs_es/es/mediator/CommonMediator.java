package com.example.demo_cqrs_es.es.mediator;

public class CommonMediator {
    private final Mediator mediator;

    public CommonMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public <T> T send(Request<T> request) {
        return mediator.send(request);
    }
}
