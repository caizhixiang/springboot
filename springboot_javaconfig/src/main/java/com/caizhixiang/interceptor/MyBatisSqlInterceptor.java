package com.caizhixiang.interceptor;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }),
        @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
                RowBounds.class, ResultHandler.class }) })
public class MyBatisSqlInterceptor implements Interceptor {

    private static final Logger logger = LoggerFactory.getLogger(MyBatisSqlInterceptor.class);

    @SuppressWarnings("unused")
    private Properties properties;

    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = null;
        if (invocation.getArgs().length > 1) {
            parameter = invocation.getArgs()[1];
        }
        String sqlId = mappedStatement.getId();
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        Configuration configuration = mappedStatement.getConfiguration();
        Object returnValue = null;
        long start = System.currentTimeMillis();
        returnValue = invocation.proceed();
        long end = System.currentTimeMillis();
        long time = (end - start);
        if (time > 1) {
            String sql = getSql(configuration, boundSql, sqlId, time);
            logger.info(sql + ";");
        }
        return returnValue;
    }

    public static String getSql(Configuration configuration, BoundSql boundSql, String sqlId, long time) {
        String sql = showSql(configuration, boundSql);
        return String.format("%s %s [%s;] cost %s ms",
                date2str(new Date(), DatePatternEnum.yyyyMMddHHmmssSSS), sqlId, sql, time);
    }

    private static String getParameterValue(Object obj) {
        String value = null;
        if (obj instanceof String) {
            if (((String) obj).indexOf("$") >= 0) {
                return "''";
            }
            value = "'" + obj.toString() + "'";
        } else if (obj instanceof Date) {
            value = "'" + date2str((Date) obj, DatePatternEnum.yyyyMMddHHmmss) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }

        }
        return value;
    }

    public static String showSql(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        if (parameterMappings.size() > 0 && parameterObject != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));

            } else {
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    }
                }
            }
        }
        return sql;
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    private static String date2str(final Date date, final DatePatternEnum pattern) {
        return new DateTime(date).toString(pattern.getPattern());
    }
    
    enum DatePatternEnum {
        // @formatter:off
        yyyyMMddHHmmssSSS("yyyy-MM-dd HH:mm:ss.SSS"), 
        yyyyMMddHHmmss("yyyy-MM-dd HH:mm:ss"), 
        yyyyMMddHyphen("yyyy-MM-dd"), 
        yyyyMMdd("yyyyMMdd");
        // @formatter:on

        private DatePatternEnum(final String pattern) {
            this.pattern = pattern;
        }

        private String pattern;

        public String getPattern() {
            return pattern;
        }
    }

}