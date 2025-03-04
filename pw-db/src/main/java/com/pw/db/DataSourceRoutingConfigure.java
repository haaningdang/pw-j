package com.pw.db;

import com.pw.db.context.DataSourceContext;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DataSourceRoutingConfigure extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContext.get();
    }

}
