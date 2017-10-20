package com.lsh.ofc.provider.common.util;

import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.exception.EC;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Created by huangdong on 16/8/28.
 */
public final class ValidationUtils {

    private ValidationUtils() {
    }

    public static void valid(Object obj) throws BusinessException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> cvs = validator.validate(obj);
        if (CollectionUtils.isEmpty(cvs)) {
            return;
        }
        StringBuilder builder = new StringBuilder(EC.ERROR_PARAMS.getMessage());
        for (ConstraintViolation cv : cvs) {
            builder.append("[").append(cv.getPropertyPath().toString()).append("] ").append(cv.getMessage()).append("\n");
        }
        builder.deleteCharAt(builder.length() - 1);
        String message = builder.toString();
        throw new BusinessException(EC.ERROR_PARAMS.getCode(), message);
    }
}
