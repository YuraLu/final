package by.epam.trjava.tutorsystem.dao.pool;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtils {
    static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final Logger LOGGER = Logger.getLogger(JDBCUtils.class);

    private JDBCUtils() {
    }

    public static void close(PreparedStatement st, Connection con) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            LOGGER.warn("can't close st", e);
        }

        pool.releaseConnection(con);
    }

    public static void close(ResultSet rs, PreparedStatement st, Connection con) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            LOGGER.warn("can't close rs", e);
        }
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            LOGGER.warn("can't close st", e);
        }

        pool.releaseConnection(con);
    }

    public static void close(ResultSet rs, PreparedStatement st1, PreparedStatement st2, Connection con) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            LOGGER.warn("can't close rs", e);
        }
        try {
            if (st1 != null) {
                st1.close();
            }
        } catch (SQLException e) {
            LOGGER.warn("can't close st1", e);
        }
        try {
            if (st2 != null) {
                st2.close();
            }
        } catch (SQLException e) {
            LOGGER.warn("can't close st2", e);
        }

        pool.releaseConnection(con);
    }

    public static void close(ResultSet rs, PreparedStatement st1,
                                       PreparedStatement st2, PreparedStatement st3,
                                       Connection con) {
        close(rs, st1, st2, con);
        try {
            if (st3 != null) {
                st3.close();
            }
        } catch (SQLException e) {
            LOGGER.warn("can't close st3", e);
        }
        pool.releaseConnection(con);
    }
}
