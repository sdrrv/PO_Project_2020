// 
// Decompiled by Procyon v0.5.36
// 

package pt.tecnico.po.ui;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.util.Map;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import java.awt.Component;
import javax.swing.JFrame;

public class InteractionUsingSwing extends JFrame implements Interaction
{
    private static final long serialVersionUID = 201608231515L;
    
    public InteractionUsingSwing() {
        super("Programa\u00e7\u00e3o com Objectos");
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setDefaultCloseOperation(3);
        this.setResizable(true);
        this.setVisible(true);
    }
    
    @Override
    public void close() {
        this.dispose();
    }
    
    @Override
    public void menu(final Menu menu) {
        int access$100;
        do {
            final SwingPanel swingPanel = new SwingPanel(menu);
            this.add(swingPanel);
            this.pack();
            swingPanel.await();
            access$100 = swingPanel.option();
            this.remove(swingPanel);
            if (access$100 == 0) {
                break;
            }
            try {
                menu.entry(access$100 - 1).performCommand();
            }
            catch (DialogException obj) {
                this.message(menu.entry(access$100 - 1).title() + ": " + obj, menu.title());
            }
        } while (!menu.entry(access$100 - 1).isLast());
    }
    
    @Override
    public void form(final Form form) {
        final SwingPanel swingPanel = new SwingPanel(form);
        this.add(swingPanel);
        this.pack();
        do {
            swingPanel.await();
        } while (!swingPanel.parse());
        this.remove(swingPanel);
    }
    
    @Override
    public void message(final Display display) {
        this.message(display.getText(), display.getTitle());
    }
    
    private void message(final String s, final String s2) {
        final SwingPanel swingPanel = new SwingPanel(s, s2);
        this.add(swingPanel);
        this.pack();
        swingPanel.await();
        this.remove(swingPanel);
    }
    
    public class SwingPanel extends JPanel implements ActionListener
    {
        private static final long serialVersionUID = 201608231505L;
        private int _opt;
        private boolean _end;
        private Map<Input<?>, JTextField> _ins;
        private final Object _lock;
        
        @Override
        public void actionPerformed(final ActionEvent actionEvent) {
            synchronized (this._lock) {
                this._opt = Integer.parseInt(actionEvent.getActionCommand());
                this._end = true;
                this._lock.notifyAll();
            }
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
            synchronized (this._lock) {
                while (!this._end) {
                    try {
                        this._lock.wait();
                    }
                    catch (InterruptedException x) {
                        System.out.println(x);
                    }
                }
            }
        }
        
        private int option() {
            return this._opt;
        }
        
        private boolean parse() {
            boolean b = true;
            for (final Input<?> input : this._ins.keySet()) {
                if (input.regex() != null && !input.parse(this._ins.get(input).getText())) {
                    this._ins.get(input).setText("");
                    b = false;
                }
            }
            return b;
        }
        
        SwingPanel(final Menu menu) {
            super(new GridLayout(menu.size() + 3, 1));
            this._lock = new Object();
            final int[] array = { 49, 50, 51, 52, 53, 54, 55, 56, 57 };
            this.add(new JLabel(menu.title(), 0));
            this.add(new JLabel(""));
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
        
        SwingPanel(final Form form) {
            super(new GridLayout(form.entries().size() + 2, 2));
            this._lock = new Object();
            this._ins = new HashMap<Input<?>, JTextField>();
            int n = 1;
            if (form.title() != null) {
                this.add(new JLabel(""));
                this.add(new JLabel(form.title()));
            }
            for (final Input<?> input : form.entries()) {
                final JLabel label;
                this.add(label = new JLabel(input.prompt(), 11));
                if (input.regex() != null) {
                    final JTextField textField = input.cleared() ? new JTextField(10) : new JTextField(input.toString(), 10);
                    this.add(textField);
                    label.setLabelFor(textField);
                    this._ins.put(input, textField);
                    if (n == 0) {
                        continue;
                    }
                    n = 0;
                    textField.setFocusAccelerator('1');
                }
                else {
                    this.add(new JLabel(""));
                }
            }
            this.add(new JLabel(""));
            final JButton comp = new JButton("OK");
            this.add(comp);
            comp.addActionListener(this);
            comp.setActionCommand("0");
            comp.setMnemonic(10);
        }
        
        SwingPanel(final String str, final String text) {
            this._lock = new Object();
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
