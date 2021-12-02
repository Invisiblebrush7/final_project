package test;

import javax.swing.JOptionPane;

import api.Database;
import api.User;
import api.date.Date;

public class Test {
    public static void main(String[] args) {
        Database myDB = Database.getInstance();
        int option = 0;
        while(option != 3){
            printMenu();
            try {
                option = Integer.parseInt(JOptionPane.showInputDialog("Ingresa tu elección"));
            } catch (Exception e) {
                option = 4;
            }
            switch (option) {
                case 1:
                    String email = JOptionPane.showInputDialog("Ingresa el correo del usuario nuevo:");
                    String password = JOptionPane.showInputDialog("Ingresa la contraseña");
                    JOptionPane.showMessageDialog(null, " A continuación ingresa los datos de la fecha de nacimiento ", "Fecha de nacimiento", JOptionPane.INFORMATION_MESSAGE);
                    int day = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el día:"));
                    int month = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el mes:"));
                    int year = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el año:"));
                    User newUser = new User(email, password, new Date(day, month, year));
                    myDB.addNewUser(newUser);
                    break;
                case 2:
                    String emailToSearch = JOptionPane.showInputDialog("Ingresa el email del usuario:");
                    myDB.getInfoFromUser(emailToSearch);
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Has decidido salir :)", "Gracias", JOptionPane.INFORMATION_MESSAGE);
                default:
                    break;
            }
        }
    }

    private static void printMenu(){
        String msg = String.format("1. Crear un nuevo usuario\n2. Leer la información de un usuario\n3. Salir");
        JOptionPane.showMessageDialog(null, msg, "Opciones", JOptionPane.INFORMATION_MESSAGE);
    }
}
