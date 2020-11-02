// 
// Decompiled by Procyon v0.5.36
// 

package pt.tecnico.po.ui;

public abstract class ValidityPredicate<Receiver>
{
    protected Receiver _receiver;
    
    public ValidityPredicate(final Receiver receiver) {
        this._receiver = receiver;
    }
    
    public abstract boolean isValid();
}
