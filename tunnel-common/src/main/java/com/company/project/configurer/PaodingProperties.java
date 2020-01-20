package com.company.project.configurer;

import com.company.project.core.model.ValidateCodeProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Chen
 * @created 2019-05-30-15:57.
 */
@Configuration
public class PaodingProperties {
    //private ShiroProperties shiro = new ShiroProperties();
    private String timeFormat = "yyyy-MM-dd HH:mm:ss";
    private ValidateCodeProperties validateCode = new ValidateCodeProperties();
    private boolean openAopLog = true;

    public ValidateCodeProperties getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(ValidateCodeProperties validateCode) {
        this.validateCode = validateCode;
    }

    public String getTimeFormat() {
        return timeFormat;
    }

    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }

    public boolean isOpenAopLog() {
        return openAopLog;
    }

    public void setOpenAopLog(boolean openAopLog) {
        this.openAopLog = openAopLog;
    }
}
