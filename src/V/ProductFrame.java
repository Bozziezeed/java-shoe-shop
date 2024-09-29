package V;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import M.CustomerDB;
import M.CustomerManager;
import M.ProductDB;
import M.ProductManager;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProductFrame extends JFrame
	{

		private JPanel contentPane;
		private JTable table;
		private JTextField textField_id;
		private JTextField textField_name;
		private JTextField textField_price_per_unit;
		private JTextField textField_description;
		private ImagePanel imagePanel;
		ArrayList<ProductDB> list;


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
										UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

										ProductFrame frame = new ProductFrame();
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
		public ProductFrame()
			{
				setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				setBounds(100, 100, 916, 552);
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

				setContentPane(contentPane);
				contentPane.setLayout(null);

				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(0, 10, 574, 472);
				contentPane.add(scrollPane);
				
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(table.getSelectedRowCount() < 1) {
							return ;
						}
						int index = table.getSelectedRow();
						BufferedImage img = list.get(index).product_image;
								if(img !=null) {
									imagePanel.setImage(img);
								}
					}
				});
				scrollPane.setViewportView(table);

				JLabel lblProductId = new JLabel("product id");
				lblProductId.setBounds(591, 10, 85, 13);
				contentPane.add(lblProductId);

				textField_id = new JTextField();
				textField_id.setEditable(false);
				textField_id.setColumns(10);
				textField_id.setBackground(Color.YELLOW);
				textField_id.setBounds(693, 10, 141, 19);
				contentPane.add(textField_id);

				JLabel lblProductName = new JLabel("product name");
				lblProductName.setBounds(591, 39, 85, 13);
				contentPane.add(lblProductName);

				textField_name = new JTextField();
				textField_name.setColumns(10);
				textField_name.setBounds(693, 39, 141, 19);
				contentPane.add(textField_name);

				JLabel lblPricePerUnit = new JLabel("price per unit");
				lblPricePerUnit.setBounds(591, 68, 85, 13);
				contentPane.add(lblPricePerUnit);

				textField_price_per_unit = new JTextField();
				textField_price_per_unit.setColumns(10);
				textField_price_per_unit.setBounds(693, 68, 141, 19);
				contentPane.add(textField_price_per_unit);

				JLabel lblProductDescription = new JLabel("product description");
				lblProductDescription.setBounds(591, 101, 110, 13);
				contentPane.add(lblProductDescription);

				textField_description = new JTextField();
				textField_description.setColumns(10);
				textField_description.setBounds(693, 101, 141, 19);
				contentPane.add(textField_description);

				JButton btnSaveNew = new JButton("SAVE NEW");
				btnSaveNew.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						if(!textField_price_per_unit.getText().trim().matches("[+-]?\\d*(\\.\\d+)?")) {
							JOptionPane.showMessageDialog(ProductFrame.this, "Number only Please !!");
							textField_price_per_unit.requestFocus();
							textField_price_per_unit.selectAll();
						}
						ProductDB x = new ProductDB();
						x.product_id =0;
						x.product_name = textField_name.getText().trim();
						x.price_per_unit = Double.parseDouble(textField_price_per_unit.getText().trim());
						x.product_description =  textField_description.getText().trim();
						x.product_image = (BufferedImage)imagePanel.getImage();
						
						ProductManager.saveProduct(x);
						
						load();
						textField_id.setText("");
						textField_name.setText("");
						textField_price_per_unit.setText("");
						textField_description.setText("");
						
						JOptionPane.showMessageDialog(ProductFrame.this, "Finish!");
					}
				});
				btnSaveNew.setBounds(591, 385, 85, 21);
				contentPane.add(btnSaveNew);

				JButton btnEdit = new JButton("EDIT");
				btnEdit.setBounds(591, 415, 85, 21);
				contentPane.add(btnEdit);

				JButton btnDelete = new JButton("DELETE");
				btnDelete.setBounds(591, 446, 85, 21);
				contentPane.add(btnDelete);

				JButton btnBrowse = new JButton("Browse");
				btnBrowse.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
							{
								JFileChooser fileChooser = new JFileChooser(); // create filechooser
								fileChooser.addChoosableFileFilter(new OpenFileFilter("jpeg", "Photo in JPEG format"));
								fileChooser.addChoosableFileFilter(new OpenFileFilter("jpg", "Photo in JPG format"));
								fileChooser.addChoosableFileFilter(new OpenFileFilter("png", "PNG image"));
								fileChooser
										.addChoosableFileFilter(new OpenFileFilter("svg", "Scalable Vector Graphic"));
								int returnVal = fileChooser.showOpenDialog(ProductFrame.this);
								if(returnVal == JFileChooser.APPROVE_OPTION) {
									System.out.println(returnVal);
									File f = fileChooser.getSelectedFile();
									try {
										BufferedImage bimg = ImageIO.read(f);
										imagePanel.setImage(bimg);
									}catch(IOException e1) {
										
									}
								
								}
							}
					});
				btnBrowse.setBounds(591, 152, 85, 49);
				contentPane.add(btnBrowse);
				
				imagePanel = new ImagePanel();
				imagePanel.setBounds(693, 152, 199, 207);
				contentPane.add(imagePanel);
				
				load();
			}
		
		public void load()
			{
				list = ProductManager.getAllProducts();
				DefaultTableModel model = new DefaultTableModel();

				model.addColumn("product_id");
				model.addColumn("product_name");
				model.addColumn("price_per_unit");
				model.addColumn("product_description");
			

				for (ProductDB c : list)
					{
						model.addRow(new Object[]
							{ c.product_id, c.product_name, c.price_per_unit, c.product_description });
					}

				table.setModel(model);
			}
	}

class OpenFileFilter extends FileFilter
	{

		String description = "";
		String fileExt = "";

		public OpenFileFilter(String extension)
			{
				fileExt = extension;
			}

		public OpenFileFilter(String extension, String typeDescription)
			{
				fileExt = extension;
				this.description = typeDescription;
			}

		@Override
		public boolean accept(File f)
			{
				if (f.isDirectory())
					return true;
				return (f.getName().toLowerCase().endsWith(fileExt));
			}

		@Override
		public String getDescription()
			{
				return description;
			}
	};

