package org.example;

import org.example.configDatabase.ConfigDatabase;
import org.example.ui.LoginFrame;

import java.sql.*;


public class Main {
    public static void main(String[] args) throws SQLException {

        ConfigDatabase.Precarga();

        LoginFrame form = new LoginFrame();
        form.setVisible(true);

    }

}

