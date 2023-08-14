package org.example;

import java.util.HashMap;
import java.util.Objects;

public class InjectorRegistry {

        private HashMap<Class<?>, Object>value =  new HashMap<>();

        public void registerInstance(Class <?> type, Object instance){
                Objects.requireNonNull(type);
                Objects.requireNonNull(instance);
        }

        public void lookUpInstance(Class<?> type){
                Objects.requireNonNull(type);
        }
}
