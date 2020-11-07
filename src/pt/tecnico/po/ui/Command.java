// 
// Decompiled by Procyon v0.5.36
// 

package pt.tecnico.po.ui;

public abstract class Command<Receiver>
{
    private boolean _last;
    private String _title;
    protected final Receiver _receiver;
    protected ValidityPredicate<Receiver> _validityPredicate;
    protected final Form _form;
    protected final Display _display;
    
    public Command(final boolean b, final String s) {
        this(b, s, null);
    }
    
    public Command(final boolean last, final String title, final Receiver receiver) {
        this._validityPredicate = null;
        this._last = last;
        this._title = title;
        this._form = new Form(this._title);
        this._display = new Display(this._title);
        this._receiver = receiver;
        this._validityPredicate = new ValidityPredicate<Receiver>(receiver) {
            @Override
            public boolean isValid() {
                return true;
            }
        };
    }
    
    public Command(final boolean b, final String s, final Receiver receiver, final ValidityPredicate<Receiver> validityPredicate) {
        this(b, s, receiver);
        this._validityPredicate = validityPredicate;
    }
    
    public Command(final String s, final Receiver receiver) {
        this(false, s, receiver);
    }
    
    public Command(final String s, final Receiver receiver, final ValidityPredicate<Receiver> validityPredicate) {
        this(false, s, receiver, (ValidityPredicate<Object>)validityPredicate);
    }


    public final String title() {
        return this._title;
    }
    
    public boolean isLast() {
        return this._last;
    }
    
    public boolean isValid() {
        return this._validityPredicate.isValid();
    }
    
    public final void performCommand() throws DialogException {
        this._display.clear();
        this.execute();
    }
    
    protected abstract void execute() throws DialogException;
}
