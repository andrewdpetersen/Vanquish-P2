package Service;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Service
public class UserValidator {
    ValidatorFactory factory;
    Validator validator;

    @Bean
    public void Validator(){
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public ValidatorFactory getFactory() {
        return factory;
    }

    public void setFactory(ValidatorFactory factory) {
        this.factory = factory;
    }

    public Validator getValidator() {
        return validator;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }
}
