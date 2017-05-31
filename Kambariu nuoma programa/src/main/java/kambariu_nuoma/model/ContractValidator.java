package kambariu_nuoma.model;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Edvinas on 2017-05-27.
 */
@Component
public class ContractValidator implements Validator {

    @Override
    public boolean supports(Class<?> clasS) {
        return RentContract.class.isAssignableFrom(clasS);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "fromDate", "msg.startDateNotSpecified");
        ValidationUtils.rejectIfEmpty(errors, "toDate", "msg.endDateNotSpecified");
    }


}
