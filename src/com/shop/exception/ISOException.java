package com.shop.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * 自定义异常 ISO 
 */
public class ISOException extends Exception{

    private static final long serialVersionUID = -777216335204861186L;
    /**
     * @serial
     */
    Throwable nested = null;

    /**
     * Constructs an <code>ISOException</code> with no detail message. 
     */
    public ISOException() {
        super();
    }

    /**
     * Constructs an <code>ISOException</code> with the specified detail 
     * message. 
     *
     * @param   s   the detail message.
     */
    public ISOException(String s) {
        super(s);
    }

    /**
     * Constructs an <code>ISOException</code> with a nested
     * exception
     * @param nested another exception
     */
    public ISOException (Throwable nested) {
        super(nested.toString());
        this.nested = nested;
    }

    /**
     * Constructs an <code>ISOException</code> with a detail Message nested
     * exception
     * @param   s   the detail message.
     * @param nested another exception
     */
    public ISOException (String s, Throwable nested) {
        super(s);
        this.nested = nested;
    }

    /**
     * @return nested exception (may be null)
     */
    public Throwable getNested() {
        return nested;
    }

    protected String getTagName() {
        return "iso-exception";
    }
    public void dump (PrintStream p, String indent) {
        String inner = indent + "  ";
        p.println (indent + "<"+getTagName()+">");
        p.println (inner  + getMessage());
        if (nested != null) {
            if (nested instanceof ISOException) 
                ((ISOException)nested).dump (p, inner);
            else {
                p.println (inner + "<nested-exception>");
                p.print   (inner);
                nested.printStackTrace (p);
                p.println (inner + "</nested-exception>");
            }
        }
        p.print (inner);
        printStackTrace (p);
        p.println (indent + "</"+getTagName()+">");
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
