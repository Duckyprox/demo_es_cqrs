package com.example.demo_cqrs_es.es.mediator;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;

@Component
public class MediatorImpl implements Mediator {
    private final AbstractApplicationContext context;

    @Autowired
    public MediatorImpl(final AbstractApplicationContext context) {
        this.context = context;
    }

    @Override
    public <T> T send(Request<T> request) {
        if (request == null) {
            throw new NullPointerException("The given request object cannot be null");
        }

        // Types used to find the exact handler that is required to handle the given request.
        // We are able to search all application beans by type from the Spring ApplicationContext.
        final Class requestType = request.getClass();
        final Class<T> responseType = (Class<T>) ((ParameterizedType) requestType.getGenericInterfaces()[0]).getActualTypeArguments()[0];

        // Retrieve RequestHandler beans based on request and response types.
        final String[] beanNames = context.getBeanNamesForType(ResolvableType.forClassWithGenerics(RequestHandler.class, requestType, responseType));

        // There must be a registered handler.
        if (beanNames.length == 0) {
            throw new IllegalStateException("No handlers seemed to be registered to handle the given request. Make sure the handler is defined and marked as a spring component");
        }

        // There may not be more than one handler.
        if (beanNames.length > 1) {
            throw new IllegalStateException("More than one handlers found. Only one handler per request is allowed.");
        }

        final RequestHandler<Request<T>, T> requestHandler = (RequestHandler<Request<T>, T>) context.getBean(beanNames[0]);

        return requestHandler.handle(request);
    }
}
