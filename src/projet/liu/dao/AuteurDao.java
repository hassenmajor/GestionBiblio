package projet.liu.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import projet.liu.jdbc.DBConnection;
import projet.liu.metier.Auteur;

public class AuteurDao implements Dao<Auteur> {
	private Connection conn;
	public AuteurDao() {
		try {
			conn = DBConnection.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public Auteur get(long id) {
		Auteur auteur = null;
		String requet = "SELECT * FROM Auteur WHERE idAuteur = " + (int) id;
		System.out.println(requet);
		try {
			Statement pstm = conn.createStatement();
			ResultSet rs = pstm.executeQuery(requet);
			if (rs.next()) {
				int idAuteur = rs.getInt("idAuteur");
				String nom = rs.getString("nom");
				String adresse = rs.getString("adresse");
				String region = rs.getString("region");
				auteur = new Auteur(idAuteur, nom, adresse, region);
				System.out.println(auteur.toString());
				System.out.println();
			}
			else throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... �l�ment introuvable");
			e.printStackTrace();
		}
		return auteur;
	}
	@Override
	public List<Auteur> getAll() {
		Auteur auteur = null;
		ArrayList<Auteur> arrayList = new ArrayList<>();
		String requet = "SELECT * FROM Auteur";
		System.out.println();
		System.out.println(requet);
		try {
			Statement pstm = conn.createStatement();
			ResultSet rs = pstm.executeQuery(requet);
			if (rs.next())
				do {
					int idAuteur = rs.getInt("idAuteur");
					String nom = rs.getString("nom");
					String adresse = rs.getString("adresse");
					String region = rs.getString("region");
					auteur = new Auteur(idAuteur, nom, adresse, region);
					arrayList.add(auteur);
					System.out.println(auteur.toString());
				} while (rs.next());
			else throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... �l�ments introuvables");
			e.printStackTrace();
		}
		return arrayList;
	}
	@Override
	public void save(Auteur t) {
		String requet = "INSERT INTO Auteur(idAuteur, nom, adresse, region) VALUES("+t.getIdAuteur()+", '"+t.getNom()+"', '"+t.getAdresse()+"', '"+t.getRegion()+"')";
		System.out.println(requet);
		try {
			Statement pstm = conn.createStatement();
			int rs = pstm.executeUpdate(requet);
			if (rs>0)
				System.out.println("\tAuteur enregistr� !\n");
			else
				throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... enregistrement �chou�");
			e.printStackTrace();
		}
	}
	@Override
	public void update(Auteur t, String[] params) {
		String requet = "UPDATE Auteur SET nom='"+params[0]+"', "
						+ "adresse='"+params[1]+"', "
						+ "region='"+params[2]+"' "
						+ "WHERE idAuteur = " + (int) t.getIdAuteur();
		System.out.println(requet);
		try {
			Statement pstm = conn.createStatement();
			int rs = pstm.executeUpdate(requet);
			if (rs>0)
				System.out.println("\tAuteur modifi� !\n");
			else throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... modification �chou�e");
			e.printStackTrace();
		}
	}
	@Override
	public void delete(Auteur t) {
		String requet = "DELETE FROM Auteur WHERE idAuteur = " + (int) t.getIdAuteur();
		System.out.println(requet);
		try {
			Statement pstm = conn.createStatement();
			int rs = pstm.executeUpdate(requet);
			if (rs>0)
				System.out.println("\tAuteur supprim� !\n");
			else throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... suppression �chou�e");
			e.printStackTrace();
		}
	}
	
}
