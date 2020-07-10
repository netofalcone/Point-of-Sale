package pos.core.exception;

import org.aspectj.bridge.MessageUtil;
import org.springframework.context.i18n.LocaleContextHolder;
import pos.core.util.MessageSourceUtil;

import java.util.Locale;

/**
 * Exceção para erros de validação e de regra de negócios
 */
public class BusinessException extends Exception{

    /**
     * Exception message error
     */
    private String message;

    /**
     *
     * @param message
     */
    public BusinessException(String message) {
        this(message, LocaleContextHolder.getLocale(), null);
    }

    /**
     *
     * @param message
     * @param locale
     * @param e
     */
    public BusinessException(String message, Locale locale, Exception e) {
        super(MessageSourceUtil.findMessage(message, locale), e);
    }
}
