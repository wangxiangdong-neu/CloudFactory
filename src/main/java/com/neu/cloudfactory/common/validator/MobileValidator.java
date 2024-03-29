package com.neu.cloudfactory.common.validator;

import com.neu.cloudfactory.common.annotation.IsMobile;
import com.neu.cloudfactory.common.entity.Regexp;
import com.neu.cloudfactory.common.utils.FebsUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 校验是否为合法的手机号码
 *
 * @author wxd
 */
public class MobileValidator implements ConstraintValidator<IsMobile, String> {

    @Override
    public void initialize(IsMobile isMobile) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if (StringUtils.isBlank(s)) {
                return true;
            } else {
                String regex = Regexp.MOBILE_REG;
                return FebsUtil.match(regex, s);
            }
        } catch (Exception e) {
            return false;
        }
    }
}
