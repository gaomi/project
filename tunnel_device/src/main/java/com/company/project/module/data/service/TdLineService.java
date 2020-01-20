package com.company.project.module.data.service;
import com.company.project.module.data.model.TdLine;
import com.company.project.core.Service;

import java.util.List;


/**
 * Created by paodingsoft.chen on 2019/08/07.
 */
public interface TdLineService extends Service<TdLine> {

    List getLineList();
}
