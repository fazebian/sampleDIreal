package org.example;

import java.util.HashMap;
import java.util.Objects;

public class InjectorRegistry {

        private final HashMap<Class<?>, Object>value =  new HashMap<>();

        public void registerInstance(Class <?> type, Object instance){
                Objects.requireNonNull(type);
                Objects.requireNonNull(instance);
                var result = value.putIfAbsent(type , instance);
                if(result!= null){
                        throw new IllegalStateException("instance of " + type.getName() + "already registered");
                }
        }

        public Object lookUpInstance(Class<?> type){
                Objects.requireNonNull(type);

                var instance = value.get(type);
                if(instance == null){
                        throw new IllegalStateException("no instance of "+ type.getName());
                }
                return instance;
        }
}
