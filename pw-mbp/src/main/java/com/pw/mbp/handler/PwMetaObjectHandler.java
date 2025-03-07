package com.pw.mbp.handler;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

public abstract class PwMetaObjectHandler implements MetaObjectHandler {

    private final static String ID_FIELD = "id";
    private final static String CREATE_TIME_FIELD = "createTime";
    private final static String UPDATE_TIME_FIELD = "updateTime";
    private final static String CREATE_USER_FIELD = "createUser";
    private final static String UPDATE_USER_FIELD = "updateUser";


    @Override
    public void insertFill(MetaObject metaObject) {
        try{
            setIdField(metaObject);
            setCreateTimeField(metaObject);
            setCreateUserField(metaObject);
            setUpdateTimeField(metaObject);
            setUpdateUserField(metaObject);
        }catch(Exception e){

        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        try{
            setCreateTimeField(metaObject);
            setCreateUserField(metaObject);
            setUpdateTimeField(metaObject);
            setUpdateUserField(metaObject);
        }catch(Exception e){

        }
    }

    private void setIdField(MetaObject metaObject){
        if(metaObject.hasGetter(ID_FIELD)){
            String valueStr = Convert.toStr(metaObject.getValue(ID_FIELD), "").trim();
            if(StrUtil.isBlank(valueStr)){
                metaObject.setValue(ID_FIELD, getIdField());
            }
        }
    }

    private void setCreateTimeField(MetaObject metaObject){
        if(metaObject.hasGetter(CREATE_TIME_FIELD)){
            String valueStr = Convert.toStr(metaObject.getValue(CREATE_TIME_FIELD), "").trim();
            if(StrUtil.isBlank(valueStr)){
                metaObject.setValue(CREATE_TIME_FIELD, getTimeField());
            }
        }
    }

    private void setCreateUserField(MetaObject metaObject){
        if(metaObject.hasGetter(CREATE_USER_FIELD)){
            String valueStr = Convert.toStr(metaObject.getValue(CREATE_USER_FIELD), "").trim();
            if(StrUtil.isBlank(valueStr)){
                metaObject.setValue(CREATE_USER_FIELD, getLoginUserField());
            }
        }
    }

    private void setUpdateTimeField(MetaObject metaObject){
        if(metaObject.hasGetter(UPDATE_TIME_FIELD)){
            String valueStr = Convert.toStr(metaObject.getValue(UPDATE_TIME_FIELD), "").trim();
            if(StrUtil.isBlank(valueStr)){
                metaObject.setValue(UPDATE_TIME_FIELD, getTimeField());
            }
        }
    }

    private void setUpdateUserField(MetaObject metaObject){
        if(metaObject.hasGetter(UPDATE_USER_FIELD)){
            String valueStr = Convert.toStr(metaObject.getValue(UPDATE_USER_FIELD), "").trim();
            if(StrUtil.isBlank(valueStr)){
                metaObject.setValue(UPDATE_USER_FIELD, getLoginUserField());
            }
        }
    }

    public abstract Long getIdField();

    public abstract Date getTimeField();

    public abstract Object getLoginUserField();



}
