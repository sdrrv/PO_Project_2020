// 
// Decompiled by Procyon v0.5.36
// 

package pt.tecnico.po.ui;

public abstract class DialogException extends Exception
{
    static final long serialVersionUID = 200610291601L;
    
    @Override
    public abstract String getMessage();
    
    @Override
    public final String toString() {
        return ErrorMessages.invalidOperation(this.getMessage());
    }
}
