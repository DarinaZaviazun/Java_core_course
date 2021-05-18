package main.part8.db;

import main.part8.db.entity.Team;
import main.part8.db.entity.User;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBManager {

    private static DBManager dbManager;
    private Connection connection = null;
    private static final Properties properties = new Properties();

    private DBManager() {
        try {
            properties.load(new FileReader("app.properties"));
            String conn = (String) properties.get("connection.url");
            connection = getConnection(conn);
        } catch (IOException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static DBManager getInstance() {
        if (dbManager == null)
            dbManager = new DBManager();
        return dbManager;
    }

    private Connection getConnection(String connectionUrl) throws SQLException {
        return DriverManager.getConnection(connectionUrl);
    }

    private ResultSet getResultSet(String query) throws SQLException {
        return connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(query);
    }

    private void closeCon(AutoCloseable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void setTeamForUser(User user, Team team) {
        ResultSet resultSet = null;
        try {
            resultSet = getResultSet("SELECT * FROM users where users.login = '" + user.getLogin() + "';");
            resultSet.first();
            int id = resultSet.getInt("id");
            resultSet = getResultSet("select * from users_teams");
            resultSet.moveToInsertRow(); // insert row
            resultSet.updateInt(1, id);
            resultSet.updateInt(2, team.getId());
            resultSet.insertRow();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeCon(resultSet);
        }
    }

    public void setTeamsForUser(User user, Team ... teams) {
        try {
            connection.setAutoCommit(false);
            for (Team team : teams)
                setTeamForUser(user, team);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        try (ResultSet resultSet = getResultSet("SELECT * FROM users;")) {
            while (resultSet.next())
                users.add(User.createUser(resultSet.getString("login")));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    public List<Team> getUserTeams(User user) {
        List<Team> teams = new ArrayList<>();
        try (ResultSet resultSet = getResultSet("SELECT * FROM teams where id IN (select team_id from users_teams where user_id = (select id from users where login = '" + user.getLogin() + "'));")) {
            while (resultSet.next())
                teams.add(Team.createTeam(resultSet.getString("name")));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return teams;
    }

    public Team getTeam(String name) {
        Team team = null;
        try (ResultSet resultSet = getResultSet("SELECT * FROM teams where teams.name = '" + name + "';")) {
            resultSet.first();
            team = Team.createTeam(resultSet.getString("name"));
            team.setId(resultSet.getInt("id"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return team;
    }

    public User getUser(String login) {
        User user = null;
        try (ResultSet resultSet = getResultSet("SELECT * FROM users where users.login = '" + login + "';")) {
            resultSet.first();
            user = User.createUser(resultSet.getString("login"));
            user.setId(resultSet.getInt("id"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public List<Team> findAllTeams() {
        List<Team> teams = new ArrayList<>();
        try (ResultSet resultSet = getResultSet("SELECT * FROM teams;")) {
            while (resultSet.next())
                teams.add(Team.createTeam(resultSet.getString("name")));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return teams;
    }

    public void insertTeam(Team team) {
        ResultSet generatedKeys = null;
        try (PreparedStatement prepStatement = connection.prepareStatement("insert into teams(name) values(?);",
                Statement.RETURN_GENERATED_KEYS)) {
            prepStatement.setString(1, team.getName());
            prepStatement.executeUpdate();
            generatedKeys = prepStatement.getGeneratedKeys();
            int id = 0;
            if (generatedKeys.next())
                id = generatedKeys.getInt(1);
            team.setId(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeCon(generatedKeys);
        }
    }

    public void insertUser(User user) {
        ResultSet generatedKeys = null;
        try (PreparedStatement prepStatement = connection.prepareStatement("insert into users(login) values(?);",
                Statement.RETURN_GENERATED_KEYS)) {
            prepStatement.setString(1, user.getLogin());
            prepStatement.executeUpdate();
            generatedKeys = prepStatement.getGeneratedKeys();
            int id = 0;
            if (generatedKeys.next())
                id = generatedKeys.getInt(1);
            user.setId(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeCon(generatedKeys);
        }
    }

    public void updateTeam(Team team) {
        try (PreparedStatement prepStatement = connection.prepareStatement("update teams set name = (?) where id = (?)",
                Statement.RETURN_GENERATED_KEYS)) {
            prepStatement.setString(1, team.getName());
            prepStatement.setInt(2, team.getId());
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTeam(Team team) {
        try (PreparedStatement prepStatement = connection.prepareStatement("delete from teams where id =  (?)",
                Statement.RETURN_GENERATED_KEYS)) {
            prepStatement.setInt(1, team.getId());
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
