// 
// Decompiled by Procyon v0.5.36
// 

package pt.tecnico.po.ui;

public class InputNone extends Input<Void>
{
    public InputNone(final String s) {
        super(s, null);
    }
    
    @Override
    public boolean parse(final String s) {
        return true;
    }
}
