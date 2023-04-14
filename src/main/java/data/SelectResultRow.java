package data;


import error.Error;
import log.AppLogger;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;

public class SelectResultRow {
    private static Logger log = AppLogger.logger();
    HashMap<String, Object> rowValues = new HashMap<>();

    public SelectResultRow() {
    }

    public Boolean hasColumn(String colName) {
        return rowValues.get(colName.toUpperCase()) != null;
    }
    
    public void addColumn(String colName, Object colValue) {
        rowValues.put(colName.toUpperCase(), colValue);
    }

    public Object getColumnObject(String colName) {
        return rowValues.get(colName.toUpperCase());
    }

    public Integer getInt(String colName) {
        // null = 0
        // false = 0
        // true = 1
        // int = int

        // Handled for both boolean and integer return types
        if (rowValues.get(colName.toUpperCase()) != null) {
            Object value = rowValues.get(colName.toUpperCase());
            if (value instanceof Boolean) {
                return (Boolean) value ? 1 : 0;
            } else if (value instanceof Integer) {
                return (Integer) value;
            }
        }

        return 0;
    }

    public Object get(String colName) {
        return rowValues.get(colName.toUpperCase());
    }

    public String getString(String colName) {
        return (String) rowValues.get(colName.toUpperCase());
    }

    public Double getDouble(String colName) {
        return (Double) rowValues.get(colName.toUpperCase());
    }

    public DateTime getDateTime(String colName) {
        return new DateTime(rowValues.get(colName.toUpperCase()));
    }

    public Boolean getBoolean(String colName) {
        // null = false
        // 1 = true
        // 0 = false

        // Handled for both boolean and integer return types
        if (rowValues.get(colName.toUpperCase()) != null) {
            Object value = rowValues.get(colName.toUpperCase());
            if (value instanceof Boolean) {
                return (Boolean) value;
            } else if (value instanceof Integer) {
                return (Integer) value == 1;
            } else if (value instanceof BigDecimal) {
                return ((BigDecimal) value).intValue() == 1;
            }
        }
        return false;
    }

    public InputStream getBlobInputStream(String colName) {
        try {
            return ((Blob) rowValues.get(colName.toUpperCase() + "-Blob")).getBinaryStream();
        } catch (NullPointerException ex) {
            Error.SQL_BLOB.record().additionalInformation("Nothing returned from column lookup: " + colName.toUpperCase() + "-Blob").create(ex);
        } catch (SQLException ex) {
            Error.SQL_BLOB.record().create(ex);
        }
        return null;
    }

    public BigDecimal getBigDecimal(String colName) {
        return (BigDecimal) rowValues.get(colName.toUpperCase());
    }

    public BigInteger getBigInt(String colName) {
        return (BigInteger) rowValues.get(colName.toUpperCase());
    }

    public Long getLong(String colName) {
        return (Long) rowValues.get(colName.toUpperCase());
    }

    public String getBlobString(String colName) {
        return (String) rowValues.get(colName.toUpperCase() + "-String");
    }

    public Integer getBlobInt(String colName) {
        return Integer.parseInt((String) rowValues.get(colName.toUpperCase() + "-String"));
    }

    public Double getBlobDouble(String colName) {
        return Double.parseDouble((String) rowValues.get(colName.toUpperCase() + "-String"));
    }
}
