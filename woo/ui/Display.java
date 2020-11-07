// 
// Decompiled by Procyon v0.5.36
// 

package pt.tecnico.po.ui;

public class Display
{
    private Dialog _ui;
    private String _title;
    private String _text;
    
    public Display() {
        this(Dialog.IO, null);
    }
    
    public Display(final String s) {
        this(Dialog.IO, s);
    }
    
    public Display(final Dialog ui, final String title) {
        this._title = "";
        this._text = "";
        this._ui = ui;
        this._title = title;
    }
    
    public Display add(final String str) {
        this._text += str;
        return this;
    }
    
    public Display addLine(final String s) {
        return this.addNewLine(s, false);
    }
    
    public Display addNewLine(final String str, final boolean b) {
        if (b || this._text.length() > 0) {
            this._text += '\n';
        }
        this._text += str;
        return this;
    }
    
    public String getTitle() {
        return this._title;
    }
    
    public String getText() {
        return this._text;
    }
    
    public void display(final boolean b) {
        if (b || this._text.length() > 0) {
            this._ui.message(this);
        }
    }
    
    public void display() {
        this.display(false);
    }
    
    public void popup(final Object o) {
        this.add(o.toString());
        this.display();
    }
    
    public void clear() {
        this._text = "";
    }
}
