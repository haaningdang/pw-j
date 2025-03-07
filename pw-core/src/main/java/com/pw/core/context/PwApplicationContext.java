package com.pw.core.context;

import com.pw.core.context.basic.PwBasicContext;
import com.pw.core.context.config.PwConfigContext;
import com.pw.core.context.ds.PwDsContext;
import lombok.Data;
import org.springframework.core.env.ConfigurableEnvironment;

@Data
public class PwApplicationContext {

    private PwBasicContext basic;

    private PwDsContext ds;

    private PwConfigContext config;

    private static final PwApplicationContext CONTEXT = new PwApplicationContext();

    private  PwApplicationContext(){}

    public void set(ConfigurableEnvironment environment){
        this.basic = PwBasicContext.create(environment);
        this.config = PwConfigContext.create(environment);
        this.ds = PwDsContext.create(environment);
    }

    public static PwApplicationContext inst(){
        return CONTEXT;
    }


}
