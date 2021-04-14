package jp.co.atelier.hello.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeJavaProviderTest {

    @Test
    void singletonClientUserPrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
                PrototypeBean.class, ClientBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.login();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.login();
        assertThat(count2).isEqualTo(1);
    }

    static class ClientBean {

        // ObjectProvider 는 Spring 에 종속적이지만
        // , javax.inject.Provider 는 java 의 표준으로, Spring 의 의존에서 벗어난다.
        // ( 물론 별도의 library 를 땡겨 써야한다... )
        // → implementation 'javax.inject:javax.inject:1'
        private final Provider<PrototypeBean> prototypeBeanProvider;

        public ClientBean(Provider<PrototypeBean> prototypeBeanProvider) {
            this.prototypeBeanProvider = prototypeBeanProvider;
        }

        public int login() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
