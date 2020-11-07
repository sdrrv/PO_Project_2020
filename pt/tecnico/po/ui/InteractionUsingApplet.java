// 
// Decompiled by Procyon v0.5.36
// 

package pt.tecnico.po.ui;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.util.HashMap;
import javax.swing.JButton;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.util.Map;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JApplet;

public class InteractionUsingApplet extends JApplet implements Interaction
{
    private static final long serialVersionUID = 201609171556L;
    private static InteractionUsingApplet _appletInstance;
    
    public static InteractionUsingApplet getAppletInstance() {
        return InteractionUsingApplet._appletInstance;
    }
    
    public InteractionUsingApplet() {
        if (InteractionUsingApplet._appletInstance == null) {
            InteractionUsingApplet._appletInstance = this;
        }
    }
    
    private void main() {
        final String parameter = this.getParameter("mainClass");
        if (parameter == null) {
            this.add(new JLabel("no 'mainClass' <param> for this Applet"));
            return;
        }
        String[] split = new String[0];
        final String parameter2 = this.getParameter("mainArgs");
        if (parameter2 != null) {
            split = parameter2.split(";");
        }
        try {
            Class.forName(parameter).getMethod("main", String[].class).invoke(null, split);
        }
        catch (Exception obj) {
            this.add(new JLabel("ERROR: " + obj));
        }
    }
    
    @Override
    public void init() {
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    InteractionUsingApplet.this.main();
                }
            }, "bank.app.App").start();
        }
        catch (Exception obj) {
            System.err.println("main() error: " + obj);
        }
        try {
            Thread.sleep(100L);
        }
        catch (Exception ex) {}
    }
    
    @Override
    public void close() {
        super.destroy();
    }
    
    @Override
    public void setTitle(final String name) {
        this.setName(name);
    }
    
    @Override
    public void menu(final Menu menu) {
        int access$200;
        do {
            final AppletPanel appletPanel = new AppletPanel(menu);
            this.add(appletPanel);
            appletPanel.await();
            access$200 = appletPanel.option();
            this.remove(appletPanel);
            if (access$200 == 0) {
                break;
            }
            try {
                menu.entry(access$200 - 1).performCommand();
            }
            catch (DialogException obj) {
                this.message(menu.entry(access$200 - 1).title() + ": " + obj, menu.title());
            }
        } while (!menu.entry(access$200 - 1).isLast());
    }
    
    @Override
    public void form(final Form form) {
        final AppletPanel appletPanel = new AppletPanel(form);
        this.add(appletPanel);
        do {
            appletPanel.await();
        } while (!appletPanel.parse());
        this.remove(appletPanel);
    }
    
    @Override
    public void message(final Display display) {
        this.message(display.getText(), display.getTitle());
    }
    
    private void message(final String s, final String s2) {
        final AppletPanel appletPanel = new AppletPanel(s, s2);
        this.add(appletPanel);
        appletPanel.await();
        this.remove(appletPanel);
    }
    
    public class AppletPanel extends JPanel implements ActionListener
    {
        private static final long serialVersionUID = 201608221459L;
        private int _opt;
        private boolean _end;
        private Map<Input<?>, JTextField> _inputs;
        
        @Override
        public void actionPerformed(final ActionEvent actionEvent) {
            this._opt = Integer.parseInt(actionEvent.getActionCommand());
            this._end = true;
            try {
                Thread.sleep(100L);
            }
            catch (Exception ex) {}
        }
        
        private synchronized void sleep(final int n) {
            try {
                Thread.sleep(n);
            }
            catch (InterruptedException x) {
                System.out.println(x);
            }
        }
        
        private synchronized void await() {
            this._end = false;
            while (!this._end) {
                this.sleep(1);
            }
        }
        
        private int option() {
            return this._opt;
        }
        
        private boolean parse() {
            boolean b = true;
            for (final Input<?> input : this._inputs.keySet()) {
                if (input.regex() != null && !input.parse(this._inputs.get(input).getText())) {
                    this._inputs.get(input).setText("");
                    b = false;
                }
            }
            return b;
        }
        
        AppletPanel(final Menu menu) {
            super(new GridLayout(menu.size() + 3, 1));
            final JLabel comp = new JLabel("");
            final int[] array = { 49, 50, 51, 52, 53, 54, 55, 56, 57 };
            this.add(new JLabel(menu.title(), 0));
            this.add(comp);
            for (int i = 0; i < menu.size(); ++i) {
                if (menu.entry(i).isValid()) {
                    final JButton button;
                    this.add(button = new JButton(i + 1 + " - " + menu.entry(i).title()));
                    button.addActionListener(this);
                    button.setActionCommand("" + (i + 1));
                    if (i < 9) {
                        button.setMnemonic(array[i]);
                    }
                }
                else {
                    this.add(new JLabel(i + 1 + " - " + menu.entry(i).title(), 0));
                }
            }
            final JButton button2;
            this.add(button2 = new JButton(Messages.exit()));
            button2.addActionListener(this);
            button2.setActionCommand("0");
            button2.setMnemonic(48);
        }
        
        AppletPanel(final Form form) {
            super(new GridLayout(form.entries().size() + 2, 2));
            this._inputs = new HashMap<Input<?>, JTextField>();
            final JLabel comp = new JLabel("");
            if (form.title() != null) {
                this.add(comp);
                this.add(new JLabel(form.title()));
            }
            int n = 1;
            for (final Input<?> input : form.entries()) {
                final JLabel comp2 = new JLabel(input.prompt(), 11);
                this.add(comp2);
                if (input.regex() != null) {
                    final JTextField textField = input.cleared() ? new JTextField(10) : new JTextField(input.toString(), 10);
                    this.add(textField);
                    comp2.setLabelFor(textField);
                    this._inputs.put(input, textField);
                    if (n == 0) {
                        continue;
                    }
                    n = 0;
                    textField.setFocusAccelerator('1');
                }
                else {
                    this.add(comp);
                }
            }
            this.add(comp);
            final JButton comp3 = new JButton("OK");
            this.add(comp3);
            comp3.addActionListener(this);
            comp3.setActionCommand("0");
            comp3.setMnemonic(10);
        }
        
        AppletPanel(final String str, final String text) {
            this.setLayout(new BorderLayout());
            if (text != null) {
                this.add(new JLabel(text, 0));
            }
            final JTextArea view = new JTextArea(5, 20);
            view.setEditable(false);
            view.append(str);
            this.add(new JScrollPane(view), "Center");
            final JButton comp = new JButton("OK");
            this.add(comp, "South");
            comp.addActionListener(this);
            comp.setActionCommand("0");
            comp.setMnemonic(10);
        }
    }
}
