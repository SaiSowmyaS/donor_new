package com.cg.donar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.cg.donar.bean.DonorBean;
import com.cg.donar.exception.DonorException;
import com.cg.donar.util.DBConnection;

public class DonorDaoImpl implements IDonorDao {

	@Override
	public String addDonor(DonorBean donor) throws DonorException {
		Connection connection=DBConnection.getConnection();
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		String donorId=null;
		int queryResult=0;
		
		try {
			preparedStatement=connection.prepareStatement("insert into Donor_Details values(donorId_sequence.nextVal,?,?,?,SYSDATE,?)");
			preparedStatement.setString(1,donor.getDonorName());
			//System.out.println(donor.getAddress());
			preparedStatement.setString(2,donor.getAddress());
			preparedStatement.setString(3,donor.getPhoneNumber());
			preparedStatement.setDouble(4, donor.getDonationAmount());
			preparedStatement.executeUpdate();
			Statement st=null;
			st=connection.createStatement();
			
			
			resultSet=st.executeQuery("select max(donor_Id) from  Donor_Details");
		while(resultSet.next())
			{
			
				donorId=resultSet.getString(1);
			}
				/*
				System.out.println(resultSet.getString(2));
				System.out.println(resultSet.getString(3));
				System.out.println(resultSet.getString(4));
				
				System.out.println(resultSet.getLong(5));
				
			}*/
		}
		catch(Exception sql)
		{
			System.out.println(sql);
			sql.printStackTrace();
		}
		
		try {
			
			 resultSet.close();
			 preparedStatement.close();
			
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return donorId;
	}

	@Override
	public DonorBean viewDonorDetails(String donorId) throws DonorException, SQLException {
		// TODO Auto-generated method stub
		Connection connection=DBConnection.getConnection();
		ResultSet resultSet=null;
		Statement st=null;
		//System.out.println("i sm here");
		DonorBean donorBean=new DonorBean();
		st=connection.createStatement();
		resultSet=st.executeQuery("select * from donor_details where donor_id="+donorId+"");
		while(resultSet.next())
		{
			
		donorBean.setDonorId(resultSet.getString(1));
		donorBean.setDonorName(resultSet.getString(2));
		donorBean.setAddress(resultSet.getString(3));
		donorBean.setPhoneNumber(resultSet.getString(4));
		donorBean.setDonationDate(resultSet.getDate(5));
		donorBean.setDonationAmount(resultSet.getInt(6));
		//System.out.println(donorbean);
		}
		
		return donorBean;
	}

	@Override
	public List retrieveAll() throws DonorException {
		// TODO Auto-generated method stub
		return null;
	}

}
