package projet.liu.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import projet.liu.metier.Telephone;
import projet.liu.jdbc.DBConnection;

public class TelephoneDao implements Dao<Telephone> {
	private Connection conn;
	public TelephoneDao() {
		try {
			conn = DBConnection.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public Telephone get(long id) {
		Telephone telephone = null;
		String requet = "SELECT * FROM Telephone WHERE numero = " + (int) id;
		System.out.println(requet);
		try {
			Statement pstm = conn.createStatement();
			ResultSet rs = pstm.executeQuery(requet);
			if (rs.next()) {
				int numero = rs.getInt("numero");
				String type = rs.getString("type");
				int idAuteur = rs.getInt("idAuteur");
				telephone = new Telephone(numero, type, idAuteur);
				System.out.println(telephone.toString());
				System.out.println();
			}
			else throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... �l�ment introuvable");
			e.printStackTrace();
		}
		return telephone;
	}
	@Override
	public List<Telephone> getAll() {
		Telephone telephone = null;
		ArrayList<Telephone> arrayList = new ArrayList<>();
		String requet = "SELECT * FROM Telephone";
		System.out.println();
		System.out.println(requet);
		try {
			Statement pstm = conn.createStatement();
			ResultSet rs = pstm.executeQuery(requet);
			if (rs.next())
				do {
					int numero = rs.getInt("numero");
					String type = rs.getString("type");
					int idAuteur = rs.getInt("idAuteur");
					telephone = new Telephone(numero, type, idAuteur);
					arrayList.add(telephone);
					System.out.println(telephone.toString());
				} while (rs.next());
			else throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... �l�ments introuvables");
			e.printStackTrace();
		}
		return arrayList;
	}
	@Override
	public void save(Telephone t) {
		String requet = "INSERT INTO Telephone(numero, type, idAuteur) VALUES("+t.getNumero()+", '"+t.getType()+"', '"+t.getIdAuteur()+"')";
		System.out.println(requet);
		try {
			Statement pstm = conn.createStatement();
			int rs = pstm.executeUpdate(requet);
			if (rs>0)
				System.out.println("\tTelephone enregistr� !\n");
			else
				throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... enregistrement �chou�");
			e.printStackTrace();
		}
	}
	@Override
	public void update(Telephone t, String[] params) {
		String requet = "UPDATE Telephone SET numero="+params[0]+", "
						+ "type='"+params[1]+"' "
						+ "WHERE numero = " + (int) t.getNumero();
		System.out.println(requet);
		try {
			Statement pstm = conn.createStatement();
			int rs = pstm.executeUpdate(requet);
			if (rs>0)
				System.out.println("\tTelephone modifi� !\n");
			else throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... modification �chou�e");
			e.printStackTrace();
		}
	}
	@Override
	public void delete(Telephone t) {
		String requet = "DELETE FROM Telephone WHERE numero = " + (int) t.getNumero();
		System.out.println(requet);
		try {
			Statement pstm = conn.createStatement();
			int rs = pstm.executeUpdate(requet);
			if (rs>0)
				System.out.println("\tTelephone supprim� !\n");
			else throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Erreur SQL... suppression �chou�e");
			e.printStackTrace();
		}
	}
	
}
