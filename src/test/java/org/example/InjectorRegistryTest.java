package org.example;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Target;
import java.util.List;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static org.junit.jupiter.api.Assertions.*;

public class InjectorRegistryTest  {
    @Nested
    public class test1{

        @Test @Tag("test1")
        public void registry(){
            var registry = new InjectorRegistry();
            assertNotNull(registry);
        }
        @Test @Tag("test1")
        public void atInjectTargetMethodAndConstructorAndRetentionIsRuntime() {
            assertEquals(List.of(METHOD, CONSTRUCTOR), List.of(Inject.class.getAnnotation(Target.class).value()));
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
        @Nested
        public class test2{

        @Test @Tag("test2")
            public void registerInstanceAndGetInstancePreciseSignature(){
                var registry =  new InjectorRegistry();
                registry.registerInstance(String.class , "hello");
                String instance = registry.lookUpInstance(String.class);
                assertEquals("hello", instance);
            }
        }
        @Nested
        public class test3{
        @Test @Tag("test3")
            public void registerProvider(){
                record Bar(){}

            var registry = new InjectorRegistry();
            registry.registerProvider(Bar.class, Bar::new);

            var instance1 =registry.lookUpInstance(Bar.class);
            var instance2 = registry.lookUpInstance(Bar.class);

            assertNotSame(instance1,instance2);
            assertEquals(instance1, instance2);

            }
            @Test @Tag("test3")
            public void registerProviderWithAnInterface(){
                interface I{
                    String hello();
                }
                record Impl() implements I{
                    @Override
                    public String hello() {
                        return "hello";
                    }
                }
                var registry= new InjectorRegistry();
                registry.registerProvider(I.class,Impl::new );

                var instance1 = registry.lookUpInstance(I.class);
                var instance2 =registry.lookUpInstance(I.class);

                assertNotSame(instance1, instance2);
                assertEquals(instance1, instance2);
            }
        }
    }


