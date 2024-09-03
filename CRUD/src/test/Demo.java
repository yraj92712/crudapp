package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

public class Demo {
    private static final String URL = "jdbc:mysql://localhost:3306/batch1pm";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "@Yash7417";
    private static final String getALL = "SELECT * FROM advancejava";
    private static final String getById = "SELECT * FROM advancejava WHERE DUCATID = ?";
    private static final String insert = "INSERT INTO advancejava VALUES (?, ?, ?)";
    private static final String update = "UPDATE advancejava SET NAME = ?, COURSE = ? WHERE DUCATID = ?";
    private static final String delete = "DELETE FROM advancejava WHERE DUCATID = ?";

    public static void main(String[] args) throws Exception {

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            PreparedStatement ps1 = con.prepareStatement(getALL);
            PreparedStatement ps2 = con.prepareStatement(getById);
            PreparedStatement ps3 = con.prepareStatement(insert);
            PreparedStatement ps4 = con.prepareStatement(update);
            PreparedStatement ps5 = con.prepareStatement(delete);

            while (true) {
                System.out.println("\n\n1. See All Records");
                System.out.println("2. See Record By Id");
                System.out.println("3. Add New Record");
                System.out.println("4. Update Name and Course By Id");
                System.out.println("5. Delete Record By Id");
                System.out.println("6. Exit\n\n");
                System.out.println("Enter Your Choice: ");
                String opt = br.readLine();
                switch (opt) {
                    case "1": {
                        ResultSet rs = ps1.executeQuery();
                        while (rs.next()) {
                            System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));
                        }
                        break;
                    }
                    case "2": {
                        System.out.println("Enter Ducat Id: ");
                        int id = Integer.parseInt(br.readLine());
                        ps2.setInt(1, id);
                        ResultSet rs = ps2.executeQuery();
                        if (rs.next()) {
                            System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));
                        } else {
                            System.err.println("Record Not Found");
                        }
                        break;
                    }
                    case "3": {
                        System.out.println("Enter Your Id: ");
                        int id = Integer.parseInt(br.readLine());
                        System.out.println("Enter Your Name: ");
                        String name = br.readLine();
                        System.out.println("Enter Your Course: ");
                        String course = br.readLine();
                        ps3.setInt(1, id);
                        ps3.setString(2, name);
                        ps3.setString(3, course);
                        int k = ps3.executeUpdate();
                        if (k > 0) {
                            System.out.println("Record Inserted");
                        } else {
                            System.err.println("Failed to Insert");
                        }
                        break;
                    }
                    case "4": {
                        System.out.println("Enter Ducat Id: ");
                        int id = Integer.parseInt(br.readLine());
                        ps2.setInt(1, id);
                        ResultSet rs = ps2.executeQuery();
                        if (rs.next()) {
                            System.out.println("Old Name: " + rs.getString(2));
                            System.out.println("Old Course Name: " + rs.getString(3));
                            System.out.println("Enter New Name: ");
                            ps4.setString(1, br.readLine());
                            System.out.println("Enter New Course: ");
                            ps4.setString(2, br.readLine());
                            ps4.setInt(3, id);
                            int k = ps4.executeUpdate();
                            if (k > 0) {
                                System.out.println("Record Updated");
                            } else {
                                System.err.println("Failed to Update");
                            }
                        } else {
                            System.err.println("Invalid Ducat Id");
                        }
                        break;
                    }
                    case "5": {
                        System.out.println("Enter Ducat Id: ");
                        int id = Integer.parseInt(br.readLine());
                        ps2.setInt(1, id);
                        ResultSet rs = ps2.executeQuery();
                        if (rs.next()) {
                            ps5.setInt(1, id);
                            int k = ps5.executeUpdate();
                            if (k > 0) {
                                System.out.println("Record Deleted");
                            } else {
                                System.err.println("Failed to Delete");
                            }
                        } else {
                            System.err.println("Record Not Found");
                        }
                        break;
                    }
                    case "6": {
                        System.out.println("Good Bye");
                        System.exit(0);
                    }
                    default: {
                        System.err.println("Invalid Option Selected");
                    }
                }
            }
        }
    }
}
