// 
// Decompiled by Procyon v0.5.36
// 

package pt.tecnico.po.ui;

class InputFloat extends Input<Float>
{
    public InputFloat(final String s) {
        super(s, "[0-9]+\\.[0-9]+[eE][+-][0-9]+");
    }
    
    @Override
    public boolean parse(final String s) {
        try {
            this.set(Float.parseFloat(s));
            return true;
        }
        catch (NumberFormatException ex) {
            return false;
        }
    }
}
