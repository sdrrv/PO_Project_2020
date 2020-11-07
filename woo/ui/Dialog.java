// 
// Decompiled by Procyon v0.5.36
// 

package pt.tecnico.po.ui;

import java.io.IOException;

public class Dialog
{
    private static final String ACTION_SWING = "swing";
    private static final String ACTION_TEXT = "text";
    public static final Dialog IO;
    private Interaction _subsystem;
    
    private Dialog() {
        try {
            final String property = System.getProperty("ui");
            if (property == null) {
                try {
                    System.in.available();
                    this._subsystem = new InteractionUsingText();
                }
                catch (IOException ex) {
                    this._subsystem = new InteractionUsingSwing();
                }
            }
            else if (property.equalsIgnoreCase("swing")) {
                this._subsystem = new InteractionUsingSwing();
            }
            else if (property.equalsIgnoreCase("text")) {
                this._subsystem = new InteractionUsingText();
            }
            else {
                this._subsystem = new InteractionUsingText();
            }
        }
        catch (SecurityException ex2) {
            this._subsystem = InteractionUsingApplet.getAppletInstance();
        }
    }
    
    public Dialog(final Interaction subsystem) {
        this._subsystem = subsystem;
    }
    
    public void setTitle(final String title) {
        this._subsystem.setTitle(title);
    }
    
    public void menu(final Menu menu) {
        this._subsystem.menu(menu);
    }
    
    public void form(final Form form) {
        this._subsystem.form(form);
    }
    
    public void message(final Display display) {
        this._subsystem.message(display);
    }
    
    public void close() {
        this._subsystem.close();
    }
    
    static {
        IO = new Dialog();
    }
}
