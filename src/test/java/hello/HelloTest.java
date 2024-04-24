package hello;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.StaticApplicationContext;

class HelloTest {

    @Test
    void test1() {
        StaticApplicationContext context = new StaticApplicationContext();
        context.registerSingleton("hello", Hello.class);
        Hello hello = context.getBean("hello", Hello.class);
        assertThat(hello).isNotNull();
    }

    @Test
    void test2() {
        StaticApplicationContext context = new StaticApplicationContext();
        context.registerSingleton("hello1", Hello.class);

        Hello hello1 = context.getBean("hello1", Hello.class);
        assertThat(hello1).isNotNull();

        BeanDefinition helloDefinition = new RootBeanDefinition(Hello.class);
        helloDefinition.getPropertyValues().addPropertyValue("name", "Spring");

        context.registerBeanDefinition("hello2", helloDefinition);

        Hello hello2 = context.getBean("hello2", Hello.class);
        assertThat(hello2).isNotNull();
        assertThat(hello2.sayHello()).isEqualTo("Hello Spring");

        assertThat(hello1).isNotSameAs(hello2);
        assertThat(context.getBeanFactory().getBeanDefinitionCount()).isEqualTo(2);
    }

    @Test
    void test3() {
        StaticApplicationContext context = new StaticApplicationContext();
        context.registerBeanDefinition("printer", new RootBeanDefinition(StringPrinter.class));
    }
}
