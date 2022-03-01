package api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author : NozjkoiTop
 * @Date : Created in 12.02.2022
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND)

public class DeveloperNotFoundException extends RuntimeException{

        public DeveloperNotFoundException(String message){
            super(message);
        }
}
