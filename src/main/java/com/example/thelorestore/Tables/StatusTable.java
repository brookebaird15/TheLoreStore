package com.example.thelorestore.Tables;

import com.example.thelorestore.DAOs.StatusDAO;
import com.example.thelorestore.Database.DBTableValues;
import com.example.thelorestore.Database.Database;
import com.example.thelorestore.Pojo.Status;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StatusTable implements StatusDAO {
    Database db = Database.getInstance();
    ArrayList<Status> statusList;

    /**
     * getAllStatus() returns all statuses in the status table
     * @return Arraylist of statuses
     */
    @Override
    public ArrayList<Status> getAllStatus() {
        String query = "SELECT * FROM " + DBTableValues.STATUS_TABLE;
        statusList = new ArrayList<>();
        try {
            Statement getStatus = db.getConnection().createStatement();
            ResultSet data = getStatus.executeQuery(query);
            while (data.next()) {
                statusList.add(new Status(data.getInt(DBTableValues.STATUS_ID_COLUMN),
                        data.getString(DBTableValues.STATUS_NAME_COLUMN)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statusList;
    }

    /**
     * getStatus() returns the status at the ID provided or null if no match
     * @param statusID is the column id
     * @return status | null
     */
    @Override
    public Status getStatus(int statusID) {
        String query = "SELECT * FROM " + DBTableValues.STATUS_TABLE + " WHERE " + DBTableValues.STATUS_ID_COLUMN + " = " + statusID;
        try {
            Statement getStatus = db.getConnection().createStatement();
            ResultSet data = getStatus.executeQuery(query);
            if(data.next()) {
                Status status = new Status(data.getInt(DBTableValues.STATUS_ID_COLUMN),
                        data.getString(DBTableValues.STATUS_NAME_COLUMN));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
