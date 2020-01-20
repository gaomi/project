package com.company.project.module.data.service;
import com.company.project.module.data.model.TdDuctDetail;
import com.company.project.core.Service;


/**
 * Created by paodingsoft.chen on 2019/08/12.
 */
public interface TdDuctDetailService extends Service<TdDuctDetail> {
    void updateByCon(TdDuctDetail duct);
}
