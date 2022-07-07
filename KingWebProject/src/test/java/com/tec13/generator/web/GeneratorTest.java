package com.tec13.generator.web;

import com.tec13.datasource.DynamicDatasource;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GeneratorTest {
    @Test
    public void testGenUser(){
        DataSource dd =   DynamicDatasource.create("org.postgresql.Driver","jdbc:postgresql://localhost:5432/kingproject?currentSchema=king_project","postgres","5221875zx");

        JdbcTemplate jt = DynamicDatasource.create(dd);

        jt.query("select * from information_schema.columns where table_name='t_sys_user'", new RowMapper<Object>() {
            @Override
            public Object mapRow(ResultSet resultSet, int i) throws SQLException {
                System.out.println(resultSet);
                return null;
            }
        });
    }
}
