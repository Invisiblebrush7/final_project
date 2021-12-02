package api;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Database {
    private ArrayList<User> users;
    private static Database instance = null;

    private Database(){
        this.users = new ArrayList<User>();
    }

    public static Database getInstance(){
        if (Database.instance == null) {
            Database.instance = new Database();
            return Database.instance;
        } else {
            return Database.instance;
        }
    }

    public int addNewUser(User newUser){
        for (User user : users) {
            if (user.email.equals(newUser.email)) {
                JOptionPane.showMessageDialog(null, "Este correo ya está en uso", "Error", JOptionPane.INFORMATION_MESSAGE);
                return -1;
            }
        }
        this.users.add(newUser);
        JOptionPane.showMessageDialog(null, " Usuario agregado ", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        return 1;
    }
    public int getInfoFromUser(String email){
        for (User user : users) {
            System.out.println("Email -> " + user.getEmail());
            if (user.getEmail().equals(email)) {
                String msg = String.format("Email: %s\nPassword: %s\nFecha de Nacimiento: %s", user.getEmail(), user.getPassword(), user.getDateOfBirth());
                JOptionPane.showMessageDialog(null, msg, "Usuario encontrado", JOptionPane.INFORMATION_MESSAGE);
                return 1;
            }
        }
        JOptionPane.showMessageDialog(null, " Usuario no encontrado ", "Error", JOptionPane.INFORMATION_MESSAGE);
        return -1;
    }
}
