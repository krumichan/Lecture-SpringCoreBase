package jp.co.atelier.hello.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeSpringProviderTest {

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

        private final ObjectProvider<PrototypeBean> prototypeBeanProvider;

        public ClientBean(ObjectProvider<PrototypeBean> prototypeBeanProvider) {
            this.prototypeBeanProvider = prototypeBeanProvider;
        }

        public int login() {
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
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
