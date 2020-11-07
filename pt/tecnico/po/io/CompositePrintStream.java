// 
// Decompiled by Procyon v0.5.36
// 

package pt.tecnico.po.io;

import java.io.IOException;
import java.util.Iterator;
import java.util.ArrayList;
import java.io.OutputStream;
import java.util.Collection;
import java.io.PrintStream;

public class CompositePrintStream extends PrintStream
{
    private Collection<PrintStream> _streams;
    private boolean _error;
    
    public CompositePrintStream() {
        super(System.out);
        this._error = false;
        this._streams = new ArrayList<PrintStream>();
    }
    
    public final void add(final PrintStream printStream) {
        this._streams.add(printStream);
    }
    
    @Override
    public boolean checkError() {
        boolean error = this._error;
        for (final PrintStream printStream : this._streams) {
            error = (error || printStream.checkError());
        }
        return error;
    }
    
    @Override
    public void close() {
        for (final PrintStream printStream : this._streams) {
            if (printStream != System.out) {
                printStream.close();
            }
        }
    }
    
    @Override
    public void flush() {
        final Iterator<PrintStream> iterator = this._streams.iterator();
        while (iterator.hasNext()) {
            iterator.next().flush();
        }
    }
    
    @Override
    public void print(final boolean b) {
        final Iterator<PrintStream> iterator = this._streams.iterator();
        while (iterator.hasNext()) {
            iterator.next().print(b);
        }
    }
    
    @Override
    public void print(final char c) {
        final Iterator<PrintStream> iterator = this._streams.iterator();
        while (iterator.hasNext()) {
            iterator.next().print(c);
        }
    }
    
    @Override
    public void print(final char[] s) {
        final Iterator<PrintStream> iterator = this._streams.iterator();
        while (iterator.hasNext()) {
            iterator.next().print(s);
        }
    }
    
    @Override
    public void print(final double d) {
        final Iterator<PrintStream> iterator = this._streams.iterator();
        while (iterator.hasNext()) {
            iterator.next().print(d);
        }
    }
    
    @Override
    public void print(final float f) {
        final Iterator<PrintStream> iterator = this._streams.iterator();
        while (iterator.hasNext()) {
            iterator.next().print(f);
        }
    }
    
    @Override
    public void print(final int i) {
        final Iterator<PrintStream> iterator = this._streams.iterator();
        while (iterator.hasNext()) {
            iterator.next().print(i);
        }
    }
    
    @Override
    public void print(final long l) {
        final Iterator<PrintStream> iterator = this._streams.iterator();
        while (iterator.hasNext()) {
            iterator.next().print(l);
        }
    }
    
    @Override
    public void print(final Object obj) {
        final Iterator<PrintStream> iterator = this._streams.iterator();
        while (iterator.hasNext()) {
            iterator.next().print(obj);
        }
    }
    
    @Override
    public void print(final String s) {
        final Iterator<PrintStream> iterator = this._streams.iterator();
        while (iterator.hasNext()) {
            iterator.next().print(s);
        }
    }
    
    @Override
    public void println(final boolean x) {
        final Iterator<PrintStream> iterator = this._streams.iterator();
        while (iterator.hasNext()) {
            iterator.next().println(x);
        }
    }
    
    @Override
    public void println(final char x) {
        final Iterator<PrintStream> iterator = this._streams.iterator();
        while (iterator.hasNext()) {
            iterator.next().println(x);
        }
    }
    
    @Override
    public void println(final char[] x) {
        final Iterator<PrintStream> iterator = this._streams.iterator();
        while (iterator.hasNext()) {
            iterator.next().println(x);
        }
    }
    
    @Override
    public void println(final double x) {
        final Iterator<PrintStream> iterator = this._streams.iterator();
        while (iterator.hasNext()) {
            iterator.next().println(x);
        }
    }
    
    @Override
    public void println(final float x) {
        final Iterator<PrintStream> iterator = this._streams.iterator();
        while (iterator.hasNext()) {
            iterator.next().println(x);
        }
    }
    
    @Override
    public void println(final int x) {
        final Iterator<PrintStream> iterator = this._streams.iterator();
        while (iterator.hasNext()) {
            iterator.next().println(x);
        }
    }
    
    @Override
    public void println(final long x) {
        final Iterator<PrintStream> iterator = this._streams.iterator();
        while (iterator.hasNext()) {
            iterator.next().println(x);
        }
    }
    
    @Override
    public void println(final Object x) {
        final Iterator<PrintStream> iterator = this._streams.iterator();
        while (iterator.hasNext()) {
            iterator.next().println(x);
        }
    }
    
    @Override
    public void println(final String x) {
        final Iterator<PrintStream> iterator = this._streams.iterator();
        while (iterator.hasNext()) {
            iterator.next().println(x);
        }
    }
    
    @Override
    protected void setError() {
        this._error = true;
    }
    
    @Override
    public void write(final byte[] buf, final int off, final int len) {
        final Iterator<PrintStream> iterator = this._streams.iterator();
        while (iterator.hasNext()) {
            iterator.next().write(buf, off, len);
        }
    }
    
    @Override
    public void write(final int b) {
        final Iterator<PrintStream> iterator = this._streams.iterator();
        while (iterator.hasNext()) {
            iterator.next().write(b);
        }
    }
    
    @Override
    public void write(final byte[] b) throws IOException {
        final Iterator<PrintStream> iterator = this._streams.iterator();
        while (iterator.hasNext()) {
            iterator.next().write(b);
        }
    }
}
