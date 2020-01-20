package com.company.project.module.api.model;


import com.company.project.module.api.enums.ActionEnum;
import com.google.common.base.Strings;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperParam<T> {

    private T data;

    private String state;

    /**
     * 检查是否匹配(大小写不敏感)
     * @param actionEnum
     * @return
     */
    public boolean checkAction(ActionEnum actionEnum) {
        if(Strings.isNullOrEmpty(getState()))
        {return false;}
        if(getState().equalsIgnoreCase(actionEnum.getCode()))
        {return true;}
        return false;
    }
}
