import javax.swing.*;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class TEST_REMOVE_FROM_FINAL {
	static JFrame mainFrame = new JFrame();
	public static void main(String[] args) {
		mainFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		mainFrame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnNewButton = new JButton("New button");
		panel.add(btnNewButton);
		
		JLabel label = new JLabel("");
		panel.add(label);
		
		JButton btnNewButton_1 = new JButton("New button");
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		panel.add(btnNewButton_2);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setValue(4135);
		progressBar.setMaximum(1351325);
		panel.add(progressBar);
		mainFrame.setVisible(true);
	}

}
