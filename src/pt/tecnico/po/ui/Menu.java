// 
// Decompiled by Procyon v0.5.36
// 

package pt.tecnico.po.ui;

public class Menu
{
    private String _title;
    private Command<?>[] _commands;
    private Dialog _ui;
    
    public Menu(final Dialog ui, final String title, final Command<?>[] commands) {
        this._ui = ui;
        this._title = title;
        this._commands = commands;
    }
    
    public Menu(final String s, final Command<?>[] array) {
        this(Dialog.IO, s, array);
    }
    
    public String title() {
        return this._title;
    }
    
    public int size() {
        return this._commands.length;
    }
    
    public Command<?> entry(final int n) {
        return this._commands[n];
    }
    
    Command<?>[] entries() {
        return this._commands;
    }
    
    public void open() {
        this._ui.menu(this);
    }
}
