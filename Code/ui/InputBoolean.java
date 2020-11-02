// 
// Decompiled by Procyon v0.5.36
// 

package pt.tecnico.po.ui;

class InputBoolean extends Input<Boolean>
{
    public InputBoolean(final String s) {
        super(s, "[ns]");
    }
    
    @Override
    public boolean parse(final String s) {
        if (s.length() == 1 && (s.charAt(0) == 's' || s.charAt(0) == 'n')) {
            this._value = (Type)Boolean.valueOf(s.charAt(0) == 's');
            this.dirty();
            return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return this.value() ? "sim" : "nao";
    }
}
