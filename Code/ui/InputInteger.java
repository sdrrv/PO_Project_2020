// 
// Decompiled by Procyon v0.5.36
// 

package pt.tecnico.po.ui;

class InputInteger extends Input<Integer>
{
    public InputInteger(final String s) {
        super(s, "[0-9]+");
    }
    
    @Override
    public boolean parse(final String s) {
        try {
            this.set(Integer.parseInt(s));
        }
        catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }
}
