// 
// Decompiled by Procyon v0.5.36
// 

package pt.tecnico.po.ui;

class InputString extends Input<String>
{
    public InputString(final String s) {
        super(s, ".*");
    }
    
    @Override
    public boolean parse(final String s) {
        this.set(s);
        return ((String)this._value).matches(this.regex());
    }
}
