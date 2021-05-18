package practice8;

import main.part8.db.DBManager;
import main.part8.db.entity.Team;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class Part2StudentTest {
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/test";
    private static final String URL_CONNECTION = "jdbc:h2:~/test;user=root;password=root;";
    private static final String USER = "root";
    private static final String PASS = "root";
    private static DBManager dbManager;

//    @BeforeClass
//    public static void beforeTest() throws ClassNotFoundException, SQLException {
//        Class.forName(JDBC_DRIVER);
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
//             Statement statement = con.createStatement()) {
//            String sql = "create table if not exists teams (id integer(11) not null auto_increment, name varchar(20) not null, primary key (id));";
//            statement.executeUpdate(sql);
//        }
//    }

    @Test
    public void testPart2() {
        dbManager = DBManager.getInstance();
        Team team1 = Team.createTeam("commanda");
        Team team2 = Team.createTeam("commanda2");
        dbManager.insertTeam(team1);
        dbManager.insertTeam(team2);
        List<Team> teams = dbManager.findAllTeams();
        assertEquals("[commanda, commanda2]", teams.toString());
    }

//    @AfterClass
//    public static void afterClass() throws SQLException {
//        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
//             Statement statement = con.createStatement()) {
//            String sql = "drop table teams;";
//            statement.executeUpdate(sql);
//
//        }
//    }
}
