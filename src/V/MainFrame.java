package V;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame
	{

		private JPanel contentPane;

		/**
		 * Launch the application.
		 */
		public static void main(String[] args)
			{
				EventQueue.invokeLater(new Runnable()
					{
						public void run()
							{
								try
									{
										try {
									            // Set System L&F
									        UIManager.setLookAndFeel(
									            UIManager.getSystemLookAndFeelClassName());
									    } 
									    catch (UnsupportedLookAndFeelException e) {
									       // handle exception
									    }
									    catch (ClassNotFoundException e) {
									       // handle exception
									    }
									    catch (InstantiationException e) {
									       // handle exception
									    }
									    catch (IllegalAccessException e) {
									       // handle exception
									    }
										
										MainFrame frame = new MainFrame();
										frame.setVisible(true);
									} catch (Exception e)
									{
										e.printStackTrace();
									}
							}
					});
			}

		/**
		 * Create the frame.
		 */
		public MainFrame()
			{
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setBounds(100, 100, 746, 480);
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

				setContentPane(contentPane);
				contentPane.setLayout(null);
				
				JButton btnNewButton = new JButton("Customer");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						CustomerFrame f = new CustomerFrame();
						f.setVisible(true);
					}
				});
				btnNewButton.setBounds(47, 44, 85, 21);
				contentPane.add(btnNewButton);
				
				JButton btnProduct = new JButton("Product");
				btnProduct.setBounds(47, 79, 85, 21);
				contentPane.add(btnProduct);
				
				JButton btnNewButton_1 = new JButton("User");
				btnNewButton_1.setBounds(47, 110, 85, 21);
				contentPane.add(btnNewButton_1);
				
				JButton btnNewButton_2 = new JButton("Invoice");
				btnNewButton_2.setBounds(47, 141, 85, 21);
				contentPane.add(btnNewButton_2);
			}
	}
