package com.shop.exception;

import java.io.PrintStream;
import java.io.PrintWriter;


/**
 * 数据库异常 
 */
public class DBException extends RuntimeException {

	private static final long serialVersionUID = 1177034872038174089L;
	
	 /**
     * @serial
     */
    Throwable nested = null;

    /**
     * 
     */
    public DBException() {
        super();
    }

    /**
     * 
     * @param s 异常信息
     */
    public DBException(String s) {
        super(s);
    }

    /**
     * 异常信息源
     * @param nested
     */
    public DBException (Throwable nested) {
        super(nested.toString());
        this.nested = nested;
    }

    /**
     * 
     * @param s
     * @param nested
     */
    public DBException (String s, Throwable nested) {
        super(s);
        this.nested = nested;
    }

    /**
     * 
     * @return
     */
    public Throwable getNested() {
        return nested;
    }

    protected String getTagName() {
        return "db-exception";
    }
    
    
    public String toString() {
        StringBuilder buf = new StringBuilder (super.toString());
        if (nested != null) {
            buf.append (" (");
            buf.append (nested.toString());
            buf.append (")");
        }
        return buf.toString();
    }

    public void printStackTrace() {
        super.printStackTrace();
        if (nested != null) {
            System.err.print("Nested:");
            nested.printStackTrace();
        }
    }

    public void printStackTrace(PrintStream ps) {
        super.printStackTrace(ps);
        if (nested != null) {
            ps.print("Nested:");
            nested.printStackTrace(ps);
        }
    }

    public void printStackTrace(PrintWriter pw) {
        super.printStackTrace(pw);
        if (nested != null) {
            pw.print("Nested:");
            nested.printStackTrace(pw);
        }
    }

}
