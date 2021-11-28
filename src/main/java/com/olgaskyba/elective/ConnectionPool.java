package com.olgaskyba.elective;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    public static final Logger log = (Logger) LogManager.getLogger(ConnectionPool.class);

    private DataSource dataSource;
    private static ConnectionPool instance;

    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    private ConnectionPool() {
        try{
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/ConnectPull");
        } catch (NamingException e) {
            log.error("Cannot obtain a data source", e);
            throw new IllegalStateException("Cannot obtain a data source", e);
        }
    }

    public Connection getConnection(){
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            log.error("Cannot obtain a data source", e);
            throw new IllegalStateException("Cannot obtain a data source", e);
        }
        return connection;
    }
}
