// 
// Decompiled by Procyon v0.5.36
// 

package pt.tecnico.po.ui;

import java.util.Iterator;
import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

public class Form
{
    private Dialog _ui;
    private String _title;
    private List<Input<?>> _entries;
    
    public Form() {
        this(Dialog.IO, null);
    }
    
    public Form(final String s) {
        this(Dialog.IO, s);
    }
    
    public Form(final Dialog ui, final String title) {
        this._entries = new ArrayList<Input<?>>();
        this._ui = ui;
        this._title = title;
    }
    
    public String title() {
        return this._title;
    }
    
    public Collection<Input<?>> entries() {
        return Collections.unmodifiableCollection((Collection<? extends Input<?>>)this._entries);
    }
    
    public Input<?> entry(final int n) {
        return this._entries.get(n);
    }
    
    public <T> Input<T> add(final Input<T> input) {
        this._entries.add(input);
        return input;
    }
    
    public Input<Boolean> addBooleanInput(final String s) {
        return this.add((Input<Boolean>)new InputBoolean(s));
    }
    
    public Input<String> addStringInput(final String s) {
        return this.add((Input<String>)new InputString(s));
    }
    
    public Input<Float> addFloatInput(final String s) {
        return this.add((Input<Float>)new InputFloat(s));
    }
    
    public Input<Integer> addIntegerInput(final String s) {
        return this.add((Input<Integer>)new InputInteger(s));
    }
    
    public void parse() {
        this.parse(true);
    }
    
    public void parse(final boolean b) {
        if (b) {
            final Iterator<Input<?>> iterator = this._entries.iterator();
            while (iterator.hasNext()) {
                iterator.next().clear();
            }
        }
        this._ui.form(this);
    }
    
    public void clear() {
        this._entries.clear();
    }
}
