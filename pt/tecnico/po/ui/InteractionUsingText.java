// 
// Decompiled by Procyon v0.5.36
// 

package pt.tecnico.po.ui;

import java.util.Iterator;
import pt.tecnico.po.io.RuntimeEOFException;
import java.io.EOFException;
import java.io.IOException;
import pt.tecnico.po.io.CompositePrintStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.BufferedReader;

public class InteractionUsingText implements Interaction
{
    private BufferedReader _in;
    private PrintStream _out;
    private PrintStream _log;
    private boolean _writeInput;
    
    public InteractionUsingText() {
        this._in = new BufferedReader(new InputStreamReader(System.in));
        this._out = System.out;
        this._log = null;
        final String property = System.getProperty("in");
        if (property != null) {
            try {
                this._in = new BufferedReader(new FileReader(property));
            }
            catch (FileNotFoundException ex) {
                this.println(ErrorMessages.inputError(ex));
            }
        }
        final String property2 = System.getProperty("out");
        if (property2 != null) {
            try {
                final PrintStream out = new PrintStream(new FileOutputStream(property2));
                if (Boolean.getBoolean("both")) {
                    final CompositePrintStream out2 = new CompositePrintStream();
                    out2.add(out);
                    out2.add(System.out);
                    this._out = out2;
                }
                else {
                    this._out = out;
                }
            }
            catch (FileNotFoundException ex2) {
                this.println(ErrorMessages.outputError(ex2));
            }
        }
        final String property3 = System.getProperty("log");
        if (property3 != null) {
            try {
                this._log = new PrintStream(new FileOutputStream(property3));
            }
            catch (FileNotFoundException ex3) {
                this.println(ErrorMessages.logError(ex3));
            }
        }
        this._writeInput = Boolean.getBoolean("writeInput");
    }
    
    public void closeDown() {
        if (System.out != this._out) {
            this._out.close();
        }
        try {
            if (System.getProperty("in") != null) {
                this._in.close();
            }
        }
        catch (IOException ex) {
            this.println(ErrorMessages.errorClosingInput(ex));
        }
        if (this._log != null) {
            this._log.close();
        }
    }
    
    @Override
    public void menu(final Menu menu) {
        int integer = 0;
        while (true) {
            this.println(menu.title());
            int i;
            for (i = 0; i < menu.size(); ++i) {
                if (menu.entry(i).isValid()) {
                    this.println(i + 1 + " - " + menu.entry(i).title());
                }
            }
            this.println(Messages.exit());
            try {
                integer = this.readInteger(Messages.selectAnOption());
                if (integer == 0) {
                    return;
                }
                if (integer < 0 || integer > i || !menu.entry(integer - 1).isValid()) {
                    this.println(Messages.invalidOption());
                }
                else {
                    menu.entry(integer - 1).performCommand();
                    if (menu.entry(integer - 1).isLast()) {
                        return;
                    }
                    continue;
                }
            }
            catch (DialogException obj) {
                this.println(menu.entry(integer - 1).title() + ": " + obj);
            }
            catch (EOFException ex) {
                this.println(ErrorMessages.errorEOF(ex));
            }
            catch (IOException ex2) {
                this.println(ErrorMessages.errorIO(ex2));
            }
            catch (NumberFormatException ex3) {
                this.println(ErrorMessages.errorInvalidNumber(ex3));
            }
            catch (RuntimeEOFException ex4) {
                this.println(ErrorMessages.errorREOF(ex4));
            }
        }
    }
    
    @Override
    public void form(final Form form) {
        try {
            for (final Input<?> input : form.entries()) {
                if (input.regex() != null) {
                    while (!input.parse(this.readString(input.prompt()))) {}
                }
                else {
                    this.println(input.prompt());
                }
            }
        }
        catch (EOFException ex) {
            this.println(ErrorMessages.errorEOF(ex));
        }
        catch (IOException ex2) {
            this.println(ErrorMessages.errorIO(ex2));
        }
        catch (NumberFormatException ex3) {
            this.println(ErrorMessages.errorInvalidNumber(ex3));
        }
        catch (RuntimeEOFException ex4) {
            this.println(ErrorMessages.errorREOF(ex4));
        }
    }
    
    @Override
    public void message(final Display display) {
        if (display.getText().length() == 0) {
            return;
        }
        if (display.getText().charAt(display.getText().length() - 1) == '\n') {
            this.print(display.getText());
        }
        else {
            this.println(display.getText());
        }
    }
    
    @Override
    public void setTitle(final String s) {
    }
    
    @Override
    public void close() {
        this.closeDown();
    }
    
    private final int readInteger(final String s) throws IOException {
        try {
            return Integer.parseInt(this.readString(s));
        }
        catch (NumberFormatException ex) {
            this.println(ErrorMessages.invalidNumber(ex));
            return Integer.parseInt(this.readString(s));
        }
    }
    
    private final String readString(final String s) throws IOException {
        if (s != null) {
            this._out.print(s);
        }
        final String line = this._in.readLine();
        if (line == null) {
            throw new RuntimeEOFException(ErrorMessages.endOfInput());
        }
        if (this._log != null) {
            this._log.println(line);
        }
        if (this._writeInput) {
            this._out.println(line);
        }
        return line;
    }
    
    private final void println(final String x) {
        this._out.println(x);
    }
    
    private final void print(final String s) {
        this._out.print(s);
    }
}
