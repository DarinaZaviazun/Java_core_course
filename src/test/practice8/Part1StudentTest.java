import main.part8.Demo;
import main.part8.db.DBManager;
import main.part8.db.entity.Team;
import main.part8.db.entity.User;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class Part1StudentTest {
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/test";
    private static final String URL_CONNECTION = "jdbc:h2:~/test;user=root;password=root;";
    private static final String USER = "root";
    private static final String PASS = "root";
    private static DBManager dbManager = DBManager.getInstance();;

//    @BeforeClass
//    public static void beforeTest() throws ClassNotFoundException, SQLException {
//       Class.forName(JDBC_DRIVER);
//
//        try (OutputStream out = new FileOutputStream("app.properties")) {
//            Properties props = new Properties();
//            props.setProperty("connection.url", URL_CONNECTION);
//            props.store(out, null);
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//
//        dbManager = DBManager.getInstance();
//
//        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
//            Statement statement = con.createStatement()) {
//            String sql = "create table if not exists users (id integer(11) not null auto_increment, login varchar(20) not null, primary key (id));";
//            statement.executeUpdate(sql);
//            sql = "create table if not exists teams (id integer(11) not null auto_increment, name varchar(20) not null, primary key (id));";
//            statement.executeUpdate(sql);
//        }
//    }

    @Test
    public void firstTestUser() {
        User pashka = User.createUser("Pashka");
        assertEquals("Pashka", pashka.getLogin());
        assertEquals("Pashka", pashka.toString());
    }

    @Test
    public void secTestTeam() {
        Team team = Team.createTeam("Comanda");
        Team team2 = Team.createTeam("Comanda");
        assertEquals("Comanda", team.getName());
        assertEquals("Comanda", team.toString());
        assertEquals(team, team2);
    }

    @Test
    public void testPart1() {
        User user1 = User.createUser("Dasha");
        User user2 = User.createUser("Pasha");
        User user3 = User.createUser("Pasha");
        dbManager.insertUser(user1);
        dbManager.insertUser(user2);
        assertEquals(user2, user3);
        List<User> allUsers = dbManager.findAllUsers();
        assertEquals("[Dasha, Pasha]", allUsers.toString());
        user1.setId(20);
        assertEquals(20, user1.getId());
    }

    @Test
    public void testDemo() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Demo.main(null);
        String ls = System.lineSeparator();
        String expected = "[ivanov, obama, petrov]" + ls +
                "[teamA, teamB, teamC]" + ls +
                "[teamA]" + ls +
                "[teamA, teamB, teamC]" + ls +
                "[teamA, teamB]" + ls +
                "[teamB, teamC]" + ls +
                "[teamB, teamX]" + ls;
        assertEquals(expected, outContent.toString());
    }

//    @AfterClass
//    public static void afterClass() throws SQLException {
//        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
//             Statement statement = con.createStatement()) {
//            String sql = "drop table users;";
//            statement.executeUpdate(sql);
//            sql = "drop table teams;";
//            statement.executeUpdate(sql);
//        }
//    }
}
