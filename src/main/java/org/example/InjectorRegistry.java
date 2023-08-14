package org.example;

import java.util.HashMap;
import java.util.Map;

public class InjectorRegistry {

    private Map<Class<?>, Object> values = new HashMap<>();

    public  <T> void registerInstance(Class<?> T , Object o){}
    public <T>void lookUpInstance(Class<?> T){}
}
