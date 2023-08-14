package org.example;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InjectorRegistryTest  {
    @Nested
    public class test1{
        @Test @Tag("test1")
        public void registry(){
            var registry = new InjectorRegistry();
            assertNotNull(registry);
        }
        @Test @Tag("test1")
        public void registerInstanceAndGetInstanceString(){
            var registry = new InjectorRegistry();
            registry.registerInstance(String.class, "hello");
            assertEquals("hello", registry.lookUpInstance(String.class));
        }
        @Test @Tag("test1")
        public void registerInstanceAndGetInstanceInteger(){
            var registry = new InjectorRegistry();
            registry.registerInstance(Integer.class, 2023);
            assertEquals(2023,registry.lookUpInstance(Integer.class));
        }
        @Test @Tag("test1")
        public void registerInstanceAndGetInstanceSameInstance(){
            record Person(String name){}

            var registry = new InjectorRegistry();
            var sahia = new Person("Sahia");
            registry.registerInstance(Person.class , sahia);
            assertEquals(sahia, registry.lookUpInstance(Person.class));

        }
        @Test @Tag("test1")
        public void registerInstanceAndGetInstanceWithAnInterface(){
            interface I{
                String hello();
            }
            class Impl implements I{
                @Override
                public String hello() {
                    return "hello";
                }
            }
                var registry = new InjectorRegistry();
                var impl = new Impl();
                registry.registerInstance(I.class, impl);
                assertEquals(impl, registry.lookUpInstance(I.class));
        }
    }

    }


