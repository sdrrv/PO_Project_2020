// 
// Decompiled by Procyon v0.5.36
// 

package pt.tecnico.po.ui;

public abstract class Input<Type>
{
    private String _prompt;
    private String _regex;
    private boolean _clear;
    protected Type _value;
    
    public String prompt() {
        return this._prompt;
    }
    
    protected Input() {
        this(null, null);
    }
    
    protected Input(final String prompt, final String regex) {
        this._prompt = prompt;
        this._regex = regex;
    }
    
    public void set(final Type value) {
        this._value = value;
        this.dirty();
    }
    
    public Type value() {
        return this._value;
    }
    
    public void clear() {
        this._clear = true;
    }
    
    protected void dirty() {
        this._clear = false;
    }
    
    public boolean cleared() {
        return this._clear;
    }
    
    public abstract boolean parse(final String p0);
    
    public String regex() {
        return this._regex;
    }
    
    @Override
    public String toString() {
        return "" + this._value;
    }
}
