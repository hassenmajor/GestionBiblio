package projet.liu.dao;

import java.sql.*;
import java.util.List;

import projet.liu.jdbc.DBConnection;
import projet.liu.metier.Associer;

public class AssocierDao implements Dao<Associer> {
	
	private Connection conn;
	public AssocierDao() {
		try {
			conn = DBConnection.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void save(Associer t) {
		String requet = "INSERT INTO Associer(idBibliotheque, idAuteur) VALUES("+t.getIdBibliotheque()+", '"+t.getIdAuteur()+"')";
		System.out.println(requet);
		try {
			Statement pstm = conn.createStatement();
			int rs = pstm.executeUpdate(requet);
			if (rs>0)
				System.out.println("\tAssociation enregistr�e !\n");
			else
				throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... enregistrement �chou�");
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(Associer t, String[] params) {
		
	}
	
	@Override
	public void delete(Associer t) {
		String requet = "DELETE FROM Associer WHERE idBibliotheque = " + t.getIdBibliotheque() + "AND idAuteur = " + t.getIdAuteur();
		System.out.println(requet);
		try {
			Statement pstm = conn.createStatement();
			int rs = pstm.executeUpdate(requet);
			if (rs>0)
				System.out.println("\tAssociation supprim�e !\n");
			else throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... suppression �chou�e");
			e.printStackTrace();
		}
		
	}

	public Associer get(int idBibliotheque, int idAuteur) {
		Associer association = null;
		String requet = "SELECT * FROM Associer WHERE idbibliotheque = "+idBibliotheque+" AND idAuteur = "+idAuteur;
		System.out.println(requet);
		try {
			Statement pstm = conn.createStatement();
			ResultSet rs = pstm.executeQuery(requet);
			if (rs.next()) {
				association = new Associer(idBibliotheque, idAuteur);
				System.out.println(association.toString());
				System.out.println();
			}
			else throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... �l�ment introuvable");
			e.printStackTrace();
		}
		return association;
	}
	
	@Override
	public Associer get(long id) {
		return null;
	}
	
	@Override
	public List<Associer> getAll() {
		return null;
	}
	
}
