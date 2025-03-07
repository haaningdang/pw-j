package com.pw.application.config.db.mbplus;

import com.pw.core.util.IdGenerator;
import com.pw.mbp.handler.PwMetaObjectHandler;

import java.util.Date;

public class PwDefaultMetaObjectHandler extends PwMetaObjectHandler {

    @Override
    public Long getIdField() {
        return IdGenerator.snow();
    }

    @Override
    public Date getTimeField() {
        return new Date();
    }

    @Override
    public Object getLoginUserField() {
        return null;
    }

}
