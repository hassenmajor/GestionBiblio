package projet.liu.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import projet.liu.metier.Livre;
import projet.liu.jdbc.DBConnection;

public class LivreDao implements Dao<Livre> {
	private Connection conn;
	public LivreDao() {
		try {
			conn = DBConnection.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public Livre get(long id) {
		Livre livre = null;
		String requet = "SELECT * FROM Livre WHERE ISBN = " + (int) id;
		System.out.println(requet);
		try {
			Statement pstm = conn.createStatement();
			ResultSet rs = pstm.executeQuery(requet);
			if (rs.next()) {
				int ISBN = rs.getInt("ISBN");
				String titre = rs.getString("titre");
				int idAuteur = rs.getInt("idAuteur");
				livre = new Livre(ISBN, titre, idAuteur);
				System.out.println(livre.toString());
				System.out.println();
			}
			else throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... �l�ment introuvable");
			e.printStackTrace();
		}
		return livre;
	}
	@Override
	public List<Livre> getAll() {
		Livre livre = null;
		ArrayList<Livre> arrayList = new ArrayList<>();
		System.out.println();
		String requet = "SELECT * FROM Livre";
		System.out.println(requet);
		try {
			Statement pstm = conn.createStatement();
			ResultSet rs = pstm.executeQuery(requet);
			if (rs.next())
				do {
					int ISBN = rs.getInt("ISBN");
					String titre = rs.getString("titre");
					int idAuteur = rs.getInt("idAuteur");
					livre = new Livre(ISBN, titre, idAuteur);
					arrayList.add(livre);
					System.out.println(livre.toString());
				} while (rs.next());
			else throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... �l�ments introuvables");
			e.printStackTrace();
		}
		return arrayList;
	}
	@Override
	public void save(Livre t) {
		String requet = "INSERT INTO Livre(ISBN, titre, idAuteur) VALUES("+t.getISBN()+", '"+t.getTitre()+"', '"+t.getIdAuteur()+"')";
		System.out.println(requet);
		try {
			Statement pstm = conn.createStatement();
			int rs = pstm.executeUpdate(requet);
			if (rs>0)
				System.out.println("\tLivre enregistr� !\n");
			else
				throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... enregistrement �chou�");
			e.printStackTrace();
		}
	}
	@Override
	public void update(Livre t, String[] params) {
		String requet = "UPDATE Livre SET ISBN="+params[0]+", "
						+ "titre='"+params[1]+"' "
						+ "WHERE ISBN = " + (int) t.getISBN();
		System.out.println(requet);
		try {
			Statement pstm = conn.createStatement();
			int rs = pstm.executeUpdate(requet);
			if (rs>0)
				System.out.println("\tLivre modifi� !\n");
			else throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... modification �chou�e");
			e.printStackTrace();
		}
	}
	@Override
	public void delete(Livre t) {
		String requet = "DELETE FROM Livre WHERE ISBN = " + (int) t.getISBN();
		System.out.println(requet);
		try {
			Statement pstm = conn.createStatement();
			int rs = pstm.executeUpdate(requet);
			if (rs>0)
				System.out.println("\tLivre supprim� !\n");
			else throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... suppression �chou�e");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new LivreDao().getAll();
	}
	
}
