package hello.itemservice.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource messageSource;

    @Test
    void helloMessage(){
        String result = messageSource.getMessage("hello", null, null);
        System.out.println(result);
        assertThat(result).isEqualTo("안녕");
    }

    @Test
    void notFOundMEssageCode(){
        assertThatThrownBy(()-> messageSource.getMessage("no_coude",null,null))
                .isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void argumentMessage(){
        String result = messageSource.getMessage("hello.name", new Object[]{"Spring"}, null);
        assertThat(result).isEqualTo("안녕 Spring");
    }

    @Test
    void defaultLang(){
        assertThat(messageSource.getMessage("hello",null,null)).isEqualTo("안녕");
        assertThat(messageSource.getMessage("hello",null, Locale.ENGLISH)).isEqualTo("hello");
    }
}
