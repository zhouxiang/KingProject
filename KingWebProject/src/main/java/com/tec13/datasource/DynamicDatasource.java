package com.tec13.datasource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;

public class DynamicDatasource {
    public static DataSource create(String driverClassName, String url, String username, String password){
        return DataSourceBuilder.create().driverClassName(driverClassName)
                .url(url).username(username)
                .password(password).build();
    }

    public static JdbcTemplate create(DataSource dataSource){
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(dataSource);
            jdbcTemplate.setQueryTimeout(20);
            jdbcTemplate.setFetchSize(20);
            return jdbcTemplate;
    }
}
