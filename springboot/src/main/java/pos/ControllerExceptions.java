package pos;

import org.postgresql.util.PSQLException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.List;

@RestControllerAdvice
@ControllerAdvice
public class ControllerExceptions extends ResponseEntityExceptionHandler {

    // esse método vai interceptar todos os erros e exceções mais comuns
    // que essas classes são responsáveis por transmitir
    @ExceptionHandler({Exception.class, RuntimeException.class, Throwable.class})
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {

        String msg = "  ";
        if ( ex instanceof MethodArgumentNotValidException) {
            List<org.springframework.validation.ObjectError> list = ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors();

            for (org.springframework.validation.ObjectError objectError : list) {
                msg += objectError.getDefaultMessage() + "\n";
                  }
        } else {
            msg = ex.getMessage();
        }

        ObjError objError = new ObjError();
        objError.setError(msg);
        objError.setCode(status.value() + "==> " + status.getReasonPhrase());

        return new ResponseEntity<>(objError, headers, status);
    }






    /* Mapeando da maioria dos erros a nivel de Banco de dados*/
    @ExceptionHandler({ DataIntegrityViolationException.class,
                        ConstraintViolationException.class,
                        PSQLException.class,
                        SQLException.class })
    protected ResponseEntity<Object> handleExceptionDataIntegraty(Exception ex) {

        String msg = " ";

        if (ex instanceof DataIntegrityViolationException){
            msg = ((DataIntegrityViolationException) ex).getCause().getCause().getMessage();
        }
        else if (ex instanceof ConstraintViolationException){
            msg = ((ConstraintViolationException) ex).getCause().getCause().getMessage();

        } else if (ex instanceof PSQLException){
            msg = ((PSQLException) ex).getCause().getCause().getMessage();

        } else if (ex instanceof SQLException){
            msg = ((SQLException) ex).getCause().getCause().getMessage();

        }
        else {
            msg = ex.getMessage(); // #Outras mensagengs de erros
        }


        ObjError objError = new ObjError();
        objError.setError(msg);
        objError.setCode(HttpStatus.INTERNAL_SERVER_ERROR + " ==> "+ HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());

        return new ResponseEntity<>(objError, HttpStatus.INTERNAL_SERVER_ERROR);
            }



}
