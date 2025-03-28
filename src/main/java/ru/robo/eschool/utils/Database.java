package ru.robo.eschool.utils;

//Creator: Robo_Start
//Creation date & time: 3/28/2025 10:43 PM
public class Database {
    public static Database instance = new Database();
    public static Database getInstance() throws NullPointerException {
        if (instance == null) throw new NullPointerException();
        return instance;
    }
    public Database() {

    }
}
