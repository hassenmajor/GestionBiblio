package projet.liu.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import projet.liu.cli.Controller;
import projet.liu.metier.Bibliotheque;
import projet.liu.metier.Livre;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;

public class ViewCRUD {

	public JFrame formCRUD;
	public JTextField textTitre;
	public JTextField textISBN;
	public JTextField textNom;
	public JTextField textAdresse;
	public JTextField textRegion;
	public JTextField textNumero;
	public JList<Livre> listLivre;
	public JComboBox<Bibliotheque> comboBiblio;
	public JComboBox<String> comboType;
	public JCheckBox checkTelephone;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewCRUD window = new ViewCRUD();
					window.formCRUD.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewCRUD() {
		initialize();
		Controller.init(ViewCRUD.this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		formCRUD = new JFrame();
		formCRUD.setTitle("CRUD Gestion de bibliothèque");
		formCRUD.setBounds(100, 100, 600, 380);
		formCRUD.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		formCRUD.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Gestion des livres");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setForeground(Color.BLACK);
		label.setBounds(10, 11, 127, 14);
		formCRUD.getContentPane().add(label);
		
		JPanel panelLivre = new JPanel();
		panelLivre.setBounds(10, 36, 300, 66);
		panelLivre.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		formCRUD.getContentPane().add(panelLivre);
		panelLivre.setLayout(null);
		
		JLabel panelTitre = new JLabel("Titre : ");
		panelTitre.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panelTitre.setForeground(Color.BLACK);
		panelTitre.setHorizontalAlignment(SwingConstants.RIGHT);
		panelTitre.setBounds(10, 11, 40, 14);
		panelLivre.add(panelTitre);
		
		JLabel panelISBN = new JLabel("ISBN : ");
		panelISBN.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panelISBN.setForeground(Color.BLACK);
		panelISBN.setHorizontalAlignment(SwingConstants.RIGHT);
		panelISBN.setBounds(10, 39, 40, 14);
		panelLivre.add(panelISBN);
		
		textTitre = new JTextField();
		textTitre.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textTitre.setForeground(Color.BLACK);
		textTitre.setBounds(60, 8, 230, 20);
		panelLivre.add(textTitre);
		textTitre.setColumns(10);
		
		textISBN = new JTextField();
		textISBN.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textISBN.setForeground(Color.BLACK);
		textISBN.setColumns(10);
		textISBN.setBounds(60, 36, 230, 20);
		panelLivre.add(textISBN);
		
		JPanel panelAuteur = new JPanel();
		panelAuteur.setBounds(10, 113, 300, 217);
		panelAuteur.setLayout(null);
		panelAuteur.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		formCRUD.getContentPane().add(panelAuteur);
		
		JLabel labelNom = new JLabel("Nom de l'auteur : ");
		labelNom.setFont(new Font("Tahoma", Font.PLAIN, 11));
		labelNom.setForeground(Color.BLACK);
		labelNom.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNom.setBounds(10, 11, 85, 14);
		panelAuteur.add(labelNom);
		
		JLabel panelAdresse = new JLabel("Adresse : ");
		panelAdresse.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panelAdresse.setForeground(Color.BLACK);
		panelAdresse.setHorizontalAlignment(SwingConstants.RIGHT);
		panelAdresse.setBounds(10, 39, 85, 14);
		panelAuteur.add(panelAdresse);
		
		textNom = new JTextField();
		textNom.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textNom.setForeground(Color.BLACK);
		textNom.setColumns(10);
		textNom.setBounds(105, 8, 185, 20);
		panelAuteur.add(textNom);
		
		textAdresse = new JTextField();
		textAdresse.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textAdresse.setForeground(Color.BLACK);
		textAdresse.setColumns(10);
		textAdresse.setBounds(105, 36, 185, 20);
		panelAuteur.add(textAdresse);
		
		JLabel panelRegion = new JLabel("Région : ");
		panelRegion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panelRegion.setForeground(Color.BLACK);
		panelRegion.setHorizontalAlignment(SwingConstants.RIGHT);
		panelRegion.setBounds(10, 67, 85, 14);
		panelAuteur.add(panelRegion);
		
		textRegion = new JTextField();
		textRegion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textRegion.setForeground(Color.BLACK);
		textRegion.setColumns(10);
		textRegion.setBounds(105, 64, 185, 20);
		panelAuteur.add(textRegion);
		
		checkTelephone = new JCheckBox("Téléphone de l'auteur");
		checkTelephone.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (checkTelephone.isSelected()) {
					textNumero.setEnabled(true);
					comboType.setEnabled(true);
				} else {
					textNumero.setEnabled(false);
					comboType.setEnabled(false);
				}
			}
		});
		checkTelephone.setFont(new Font("Tahoma", Font.PLAIN, 11));
		checkTelephone.setForeground(Color.BLACK);
		checkTelephone.setBounds(10, 100, 161, 23);
		panelAuteur.add(checkTelephone);
		
		textNumero = new JTextField();
		textNumero.setEnabled(false);
		textNumero.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textNumero.setForeground(Color.BLACK);
		textNumero.setBounds(10, 130, 185, 20);
		panelAuteur.add(textNumero);
		textNumero.setColumns(10);
		
		comboType = new JComboBox<String>();
		comboType.setEnabled(false);
		comboType.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboType.setForeground(Color.BLACK);
		comboType.setModel(new DefaultComboBoxModel<String>(new String[] {"Mobile", "Fixe"}));
		comboType.setSelectedIndex(0);
		comboType.setBounds(205, 129, 85, 22);
		panelAuteur.add(comboType);
		
		JButton buttonCreate = new JButton("Insérer un nouveau livre");
		buttonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.createLivre(ViewCRUD.this);
			}
		});
		buttonCreate.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonCreate.setForeground(Color.BLACK);
		buttonCreate.setBounds(105, 183, 185, 23);
		panelAuteur.add(buttonCreate);
		
		listLivre = new JList<Livre>();
		listLivre.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listLivre.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				Controller.readLivre(ViewCRUD.this);
			}
		});
		listLivre.setFont(new Font("Tahoma", Font.PLAIN, 11));
		listLivre.setForeground(Color.BLACK);
		listLivre.setBorder(new LineBorder(new Color(0, 0, 0)));
		listLivre.setBounds(320, 69, 254, 227);
		formCRUD.getContentPane().add(listLivre);
		
		comboBiblio = new JComboBox<Bibliotheque>();
		comboBiblio.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBiblio.setForeground(Color.BLACK);
		comboBiblio.setBounds(320, 36, 254, 22);
		formCRUD.getContentPane().add(comboBiblio);
		
		JButton buttonDelete = new JButton("Supprimer");
		buttonDelete.setBounds(484, 307, 90, 23);
		formCRUD.getContentPane().add(buttonDelete);
		buttonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.deleteLivre(ViewCRUD.this);
			}
		});
		buttonDelete.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonDelete.setForeground(Color.BLACK);
		
		JButton buttonUpdate = new JButton("Modifier");
		buttonUpdate.setBounds(320, 307, 85, 23);
		formCRUD.getContentPane().add(buttonUpdate);
		buttonUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.updateLivre(ViewCRUD.this);
			}
		});
		buttonUpdate.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonUpdate.setForeground(Color.BLACK);
	}
}
