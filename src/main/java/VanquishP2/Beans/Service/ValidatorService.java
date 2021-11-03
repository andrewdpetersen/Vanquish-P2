package VanquishP2.Beans.Service;

import org.springframework.stereotype.Service;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Service
public class ValidatorService {
    ValidatorFactory factory;
    Validator validator;

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

    public javax.validation.Validator getValidator() {
        return validator;
    }

    public void setValidator(javax.validation.Validator validator) {
        this.validator = validator;
    }
}
