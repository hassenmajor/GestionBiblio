package projet.liu.cli;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import projet.liu.gui.ViewCRUD;
import projet.liu.metier.*;
import projet.liu.dao.*;

public class Controller {
	
	static DefaultListModel<Livre> modelLivre;
	static DefaultComboBoxModel<Bibliotheque> modelBiblio;
	static List<Livre> livres = new LivreDao().getAll();
	static List<Auteur> auteurs = new AuteurDao().getAll();
	static List<Telephone> tels = new TelephoneDao().getAll();
	static List<Bibliotheque> biblios = new BibliothequeDao().getAll();
	
	public static void init(ViewCRUD viewCRUD) {
		modelLivre = new DefaultListModel<Livre>() {
			@Override
			public int getSize() {
				// TODO Auto-generated method stub
				return livres.size();
			}
			@Override
			public Livre getElementAt(int index) {
				// TODO Auto-generated method stub
				return livres.get(index);
			}
			@Override
			public Livre get(int index) {
				// TODO Auto-generated method stub
				return livres.get(index);
			}
			@Override
			public void removeElementAt(int index) {
				// TODO Auto-generated method stub
				livres.remove(index);
			}
			@Override
			public Livre remove(int index) {
				// TODO Auto-generated method stub
				return livres.remove(index);
			}
			@Override
			public void addElement(Livre element) {
				// TODO Auto-generated method stub
				livres.add(element);
			}
		};
		livres.sort(new Comparator<Livre>() {
			public int compare(Livre o1, Livre o2) {
				return o1.getTitre().compareTo(o2.getTitre());
			};
		});
		viewCRUD.listLivre.setModel(modelLivre);
		modelBiblio = new DefaultComboBoxModel<Bibliotheque>() {
			@Override
			public int getSize() {
				// TODO Auto-generated method stub
				return biblios.size();
			}
			@Override
			public Bibliotheque getElementAt(int index) {
				// TODO Auto-generated method stub
				return biblios.get(index);
			}
		};
		viewCRUD.comboBiblio.setModel(modelBiblio);
		viewCRUD.comboBiblio.setSelectedIndex(0);
	}

	// CRUD
	
	public static void createLivre(ViewCRUD viewCRUD) {
		// TODO Auto-generated method stub
		Auteur auteur = new Auteur(auteurs.getLast().getIdAuteur()+1, 
							viewCRUD.textNom.getText(), 
							viewCRUD.textAdresse.getText(), 
							viewCRUD.textRegion.getText());
		Livre livre = new Livre(Integer.parseInt(viewCRUD.textISBN.getText()), 
						viewCRUD.textTitre.getText(), 
						auteur.getIdAuteur());
		//
		auteurs.add(auteur);
		livres.add(livre);
		//
		new AuteurDao().save(auteur);
		new LivreDao().save(livre);
		//
		if (viewCRUD.checkTelephone.isSelected()) {
			Telephone tel = new Telephone(Integer.parseInt(viewCRUD.textNumero.getText()), 
								viewCRUD.comboType.getSelectedItem().toString(), 
								auteur.getIdAuteur());
			tels.add(tel);
			new TelephoneDao().save(tel);
		}
		viewCRUD.listLivre.updateUI();
		viewCRUD.listLivre.setSelectedIndex(modelLivre.getSize()-1);
	}
	
	static Livre livre;
	static Auteur auteur;
	static Telephone tel;
	public static void readLivre(ViewCRUD viewCRUD) {
		// TODO Auto-generated method stub
		if (viewCRUD.listLivre.getSelectedIndex()>=livres.size() || viewCRUD.listLivre.getSelectedIndex()<0) {
			viewCRUD.textTitre.setText("");
			viewCRUD.textISBN.setText("");
			viewCRUD.textNom.setText("");
			viewCRUD.textAdresse.setText("");
			viewCRUD.textRegion.setText("");
			viewCRUD.checkTelephone.setSelected(false);
			viewCRUD.textNumero.setText("");
			viewCRUD.comboType.setSelectedItem("");
			return;
		}
		//
		livre = livres.get(viewCRUD.listLivre.getSelectedIndex());
		viewCRUD.textTitre.setText(livre.getTitre());
		viewCRUD.textISBN.setText(""+livre.getISBN());
		//
		auteur = null;
		auteurs.forEach(new Consumer<Auteur>() {
			public void accept(Auteur auteur) {
				if (auteur.getIdAuteur()==livre.getIdAuteur()) {
					Controller.auteur = auteur;
					viewCRUD.textNom.setText(auteur.getNom());
					viewCRUD.textAdresse.setText(auteur.getAdresse());
					viewCRUD.textRegion.setText(auteur.getRegion());
				}
			};
		});
		//
		tel = null;
		viewCRUD.checkTelephone.setSelected(false);
		tels.forEach(new Consumer<Telephone>() {
			public void accept(Telephone tel) {
				if (tel.getIdAuteur()==livre.getIdAuteur()) {
					Controller.tel = tel;
					viewCRUD.checkTelephone.setSelected(true);
					viewCRUD.textNumero.setText(""+tel.getNumero());
					viewCRUD.comboType.setSelectedItem(tel.getType());
				}
			};
		});
	}

	public static void updateLivre(ViewCRUD viewCRUD) {
		// TODO Auto-generated method stub
		if (viewCRUD.listLivre.getSelectedIndex()>=livres.size()) return;
		//
		livre.setTitre(viewCRUD.textTitre.getText());
		livre.setISBN(Integer.parseInt(viewCRUD.textISBN.getText()));
		new LivreDao().update(livre, new String[] {""+livre.getISBN(), livre.getTitre()});
		//
		if (tel!=null && viewCRUD.checkTelephone.isSelected()) {
			tel.setNumero(Integer.parseInt(viewCRUD.textNumero.getText()));
			tel.setType(viewCRUD.comboType.getSelectedItem().toString());
			new TelephoneDao().update(tel, new String[] {""+tel.getNumero(), tel.getType()});
		} else if (tel!=null && !viewCRUD.checkTelephone.isSelected()) {
			tels.remove(tel);
			new TelephoneDao().delete(tel);
		} else if (tel==null && viewCRUD.checkTelephone.isSelected()) {
			tel = new Telephone(Integer.parseInt(viewCRUD.textNumero.getText()), 
								viewCRUD.comboType.getSelectedItem().toString(), 
								auteur.getIdAuteur());
			tels.add(tel);
			new TelephoneDao().save(tel);
		}
		//
		auteur.setNom(viewCRUD.textNom.getText());
		auteur.setAdresse(viewCRUD.textAdresse.getText());
		auteur.setRegion(viewCRUD.textRegion.getText());
		new AuteurDao().update(auteur, new String[] {auteur.getNom(), auteur.getAdresse(), auteur.getRegion()});
		//
		viewCRUD.listLivre.updateUI();
	}

	public static void deleteLivre(ViewCRUD viewCRUD) {
		// TODO Auto-generated method stub
		if (viewCRUD.listLivre.getSelectedIndex()>=livres.size()) return;
		//
		livres.remove(livre);
		tels.remove(tel);
		auteurs.remove(auteur);
		//
		new LivreDao().delete(livre);
		new TelephoneDao().delete(tel);
		new AuteurDao().delete(auteur);
		//
		viewCRUD.listLivre.updateUI();
		Controller.readLivre(viewCRUD);
	}

}
