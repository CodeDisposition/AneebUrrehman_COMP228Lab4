import java.sql.*;


public class Main {

    public static void main(String[] args) {
        Connection conn = null;

        try {
            //Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:students.db");
            System.out.println("Database connected!");

            try {
                deleteTable(conn);
                System.out.println("sad");
            }
            catch (Exception ignored) {

            }
            createTable(conn);

            System.out.println();
            addStudent(conn, Integer.parseInt("781245"), "Goku Son", "Kinesiology");
            addStudent(conn, Integer.parseInt("369802"), "Vegeta Brief", "Aerospace Engineering");
            addStudent(conn, Integer.parseInt("502187"), "Bulma Brief", "Industrial Design");
            addStudent(conn, Integer.parseInt("924563"), "Gohan Son", "Education");
            addStudent(conn, Integer.parseInt("135790"), "Piccolo Namek", "Philosophy");
            addStudent(conn, Integer.parseInt("647821"), "Trunks Brief", "Fashion Design");
            addStudent(conn, Integer.parseInt("710293"), "Chi-Chi Ox", "Psychology");
            addStudent(conn, Integer.parseInt("478906"), "Krillin Orin", "Criminology");
            addStudent(conn, Integer.parseInt("246801"), "Android 18", "Computer Engineering");
            addStudent(conn, Integer.parseInt("803467"), "Yamcha Wolfang", "Sports Medicine");
            System.out.println();
            System.out.println("Displaying Database");
            System.out.println();
            displayDatabase(conn, "students");
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        //} catch (ClassNotFoundException e) {
            //throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }
        }

    }

    private static void displayDatabase(Connection conn, String tableName) throws SQLException {
        String selectSQL = "SELECT * from " + tableName;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(selectSQL);

        System.out.println(tableName);

        if (rs == null) return;

        ResultSetMetaData rsmd = rs.getMetaData();

        int NumOfCol = rsmd.getColumnCount();

        while (rs.next())
        {
            String str = rs.getString(1);
            String str1 = rs.getString(2);
            String str2 = rs.getString(3);

            System.out.print(str);
            System.out.print(", " + str1);
            System.out.println(", " + str2);
        }



        System.out.println();
        System.out.println("____________________________");

    }

    private static void addStudent(Connection conn, int id, String name, String major) throws SQLException {
        String insertSQL = "INSERT INTO Students (id, name, major) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(insertSQL);
        stmt.setInt(1, id);
        stmt.setString(2, name);
        stmt.setString(3, major);
        stmt.executeUpdate();
    }

    private static void createTable(Connection conn) throws SQLException {
        String createTableSql = "" +
                "CREATE TABLE Students (" +
                "id INTEGER PRIMARY KEY," +
                "name TEXT," +
                "major TEXT" +
                ")";
        Statement stmt = conn.createStatement();
        stmt.execute(createTableSql);
    }

    private static void deleteTable(Connection conn) throws SQLException {
        String deleteTableSQL = "DROP TABLE IF EXISTS Students";
        Statement stmt = conn.createStatement();
        stmt.execute(deleteTableSQL);
    }
}