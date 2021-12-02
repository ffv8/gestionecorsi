package com.gestionecorsi.architecture.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gestionecorsi.businesscomponent.model.Amministratore;

public class AmministratoreDAO implements DAOConstants {

	public static AmministratoreDAO getFactory() throws DAOException {
		return new AmministratoreDAO();
	}

	public Amministratore getById(Connection conn, long id) throws DAOException {
		Amministratore admin = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(SELECT_ADMIN_BYID);
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				admin = new Amministratore();
				admin.setNomeAdmin(rs.getString(1));
				admin.setCognomeAdmin(rs.getString(2));
				admin.setCodAdmin(rs.getLong(3));
				admin.setUsername(rs.getString(4));
				admin.setPassword(rs.getString(5));
			}
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
		return admin;
	}
}
