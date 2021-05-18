package practice8;

import main.part8.db.DBManager;
import main.part8.db.entity.Team;
import main.part8.db.entity.User;
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
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class Part3StudentTest {
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
//            String sql = "create table if not exists users (id integer(11) not null auto_increment, login varchar(20) not null, primary key (id));";
//            statement.executeUpdate(sql);
//            sql = "create table if not exists teams (id integer(11) not null auto_increment, name varchar(20) not null, primary key (id));";
//            statement.executeUpdate(sql);
//            sql = "create table if not exists users_teams (" +
//                    "user_id integer(11) not null, " +
//                    "team_id integer(11) not null, " +
//                    "constraint users_teams_pk " +
//                    "primary key (team_id, user_id), " +
//                    "constraint users_teams_teams_id_fk " +
//                    "foreign key (team_id) references teams (id) " +
//                    "on delete cascade, " +
//                    "constraint users_teams_users_id_fk " +
//                    "foreign key (user_id) references users (id) " +
//                    "on delete cascade " +
//                    ");";
//            statement.executeUpdate(sql);
//        }
//    }

    @Test
    public void testPart3() {
        dbManager = DBManager.getInstance();
        dbManager.insertUser(User.createUser("sidorov"));
        User userSidorov = dbManager.getUser("sidorov");
        dbManager.insertTeam(Team.createTeam("teamA"));
        dbManager.insertTeam(Team.createTeam("teamB"));
        dbManager.insertTeam(Team.createTeam("teamC"));
        Team teamA = dbManager.getTeam("teamA");
        Team teamB = dbManager.getTeam("teamB");
        Team teamC = dbManager.getTeam("teamC");
        dbManager.setTeamsForUser(userSidorov, teamA, teamB, teamC);
        assertEquals("[teamA, teamB, teamC]", dbManager.getUserTeams(userSidorov).toString());
    }

//    @AfterClass
//    public static void afterClass() throws SQLException {
//        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
//             Statement statement = con.createStatement()) {
//            String sql = "drop table users;";
//            statement.executeUpdate(sql);
//            sql = "drop table teams;";
//            statement.executeUpdate(sql);
//            sql = "drop table users_teams;";
//            statement.executeUpdate(sql);
//        }
//    }
}
