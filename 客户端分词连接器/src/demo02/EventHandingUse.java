package demo02;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class EventHandingUse extends JFrame{
	public EventHandingUse()
	{
		super("使用匿名类的事件处理");
		Container c= getContentPane();
		JButton b = new JButton("单击0次");
		b.addActionListener(new ActionListener(){
			int count = 0;
			public void actionPerformed(ActionEvent e)
			{
				JButton b = (JButton)e.getSource();
				b.setText("单击"+(++count)+"次");
			}
		});
		c.add(b,BorderLayout.CENTER);
	}
	public static void main(String[] args) {
		EventHandingUse app = new EventHandingUse();
		app.setSize(260,100);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setVisible(true);
	}
}
