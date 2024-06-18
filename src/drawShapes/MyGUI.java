package drawShapes;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MyGUI extends JFrame implements ActionListener,ItemListener {
	JComboBox<String> techniques = null;
	DrawPanel drawPanel = new DrawPanel("Line");
	
	public MyGUI() {
		setTitle("My GUI");
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initComponents();
		Timer t = new Timer(100, this);
		t.start();
	}

	private void initComponents() {
		JPanel topPanel = new JPanel();
		String list[] = {"Line","Circle","Free Hand"};  // list of options
		techniques = new JComboBox<String>(list);
		techniques.addItemListener(this);
		topPanel.add(new JLabel("Technique:"));
		topPanel.add(techniques);
		JButton clrBtn = new JButton("Clear");
		topPanel.add(clrBtn);
		clrBtn.addActionListener(this);
		add(topPanel,BorderLayout.NORTH);
		add(drawPanel);	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			drawPanel.repaint();
			if(e.getActionCommand()!=null && e.getActionCommand().equals("Clear")){
				drawPanel.clear();
			}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED) {
			String scheme = e.getItem().toString();
			drawPanel.getScheme(scheme);
		}
	}

}

