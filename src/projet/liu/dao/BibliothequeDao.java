package projet.liu.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import projet.liu.jdbc.DBConnection;
import projet.liu.metier.Bibliotheque;

public class BibliothequeDao implements Dao<Bibliotheque> {
	
	private Connection conn;
	public BibliothequeDao() {
		try {
			conn = DBConnection.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Bibliotheque get(long id) {
		Bibliotheque bibliotheque = null;
		String requet = "SELECT * FROM Bibliotheque WHERE idBibliotheque = " + (int) id;
		System.out.println(requet);
		try {
			Statement pstm = conn.createStatement();
			ResultSet rs = pstm.executeQuery(requet);
			if (rs.next()) {
				int idBibliotheque = rs.getInt("idBibliotheque");
				String emplacement = rs.getString("emplacement");
				bibliotheque = new Bibliotheque(idBibliotheque, emplacement);
				System.out.println(bibliotheque.toString());
				System.out.println();
			}
			else throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... �l�ment introuvable");
			e.printStackTrace();
		}
		return bibliotheque;
	}
	
	@Override
	public List<Bibliotheque> getAll() {
		Bibliotheque bibliotheque = null;
		ArrayList<Bibliotheque> arrayList = new ArrayList<>();
		String requet = "SELECT * FROM Bibliotheque";
		System.out.println();
		System.out.println(requet);
		try {
			Statement pstm = conn.createStatement();
			ResultSet rs = pstm.executeQuery(requet);
			if (rs.next())
				do {
					int idBibliotheque = rs.getInt("idBibliotheque");
					String emplacement = rs.getString("emplacement");
					bibliotheque = new Bibliotheque(idBibliotheque, emplacement);
					arrayList.add(bibliotheque);
					System.out.println(bibliotheque.toString());
				} while (rs.next());
			else throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... �l�ments introuvables");
			e.printStackTrace();
		}
		return arrayList;
	}
	
	@Override
	public void save(Bibliotheque t) {
		String requet = "INSERT INTO Bibliotheque(idBibliotheque, emplacement) VALUES("+t.getIdBibliotheque()+", '"+t.getEmplacement()+"')";
		System.out.println(requet);
		try {
			Statement pstm = conn.createStatement();
			int rs = pstm.executeUpdate(requet);
			if (rs>0)
				System.out.println("\tBibliotheque enregistr�e !\n");
			else
				throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... enregistrement �chou�");
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(Bibliotheque t, String[] params) {
		String requet = "UPDATE Bibliotheque SET "
						+ "emplacement='"+params[0]+"' "
						+ "WHERE idBibliotheque = " + (int) t.getIdBibliotheque();
		System.out.println(requet);
		try {
			Statement pstm = conn.createStatement();
			int rs = pstm.executeUpdate(requet);
			if (rs>0)
				System.out.println("\tBibliotheque modifi�e !\n");
			else throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... modification �chou�e");
			e.printStackTrace();
		}
	}
	
	@Override
	public void delete(Bibliotheque t) {
		String requet = "DELETE FROM Bibliotheque WHERE idBibliotheque = " + (int) t.getIdBibliotheque();
		System.out.println(requet);
		try {
			Statement pstm = conn.createStatement();
			int rs = pstm.executeUpdate(requet);
			if (rs>0)
				System.out.println("\tBibliotheque supprim�e !\n");
			else throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... suppression �chou�e");
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		
	}
	
}
