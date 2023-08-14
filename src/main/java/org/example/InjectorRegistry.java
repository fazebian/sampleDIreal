package org.example;

import java.util.HashMap;
import java.util.Objects;
import java.util.function.Supplier;

public class InjectorRegistry {

        private final HashMap<Class<?>, Supplier<?>>value =  new HashMap<>();

        public <T> void registerInstance(Class <T> type, T instance){
                Objects.requireNonNull(type);
                Objects.requireNonNull(instance);
                registerProvider(type , () -> instance);
        }

        public <T>  T lookUpInstance(Class<T> type){
                Objects.requireNonNull(type);
                var provider =lookUpProvider(type);
                return type.cast(provider.get());
        }
        public <T> void registerProvider(Class<T> type, Supplier<? extends T> provider){
                Objects.requireNonNull(type);
                Objects.requireNonNull(provider);
                var result = value.putIfAbsent(type , provider);
                if(result!= null){
                        throw new IllegalStateException("provider  of " + type.getName() + "already registered");
                }
        }

        private Supplier<?>  lookUpProvider(Class <?> type){
                var provider = value.get(type);
                if(provider == null){
                        throw new IllegalStateException("no provider of "+ type.getName() );
                }
                return provider;

        }
}
