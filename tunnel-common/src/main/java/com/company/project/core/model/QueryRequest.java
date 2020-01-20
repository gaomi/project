package com.company.project.core.model;

import com.google.common.base.MoreObjects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Chen
 * @created 2019-05-22-11:32.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QueryRequest implements Serializable {

    private static final long serialVersionUID = -4869594085374385813L;
    private int pageSize;
    private int pageNum;
    private Map params;


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("pageSize", pageSize)
                .add("pageNum", pageNum)
                .toString();
    }

}
