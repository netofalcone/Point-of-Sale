package pos.core.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageSourceUtil {
    public static String findMessage(String message, Locale local) {
        ResourceBundle messageSource = ResourceBundle.getBundle("messages", local);
        return messageSource.getString(message);
    }
}
