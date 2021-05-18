package main.part8;

import main.part8.db.DBManager;
import main.part8.db.entity.Team;
import main.part8.db.entity.User;

import java.util.List;


public class Demo {

    private static void printList(List<?> list) {
        System.out.println(list);
    }

    public static void main(String[] args) {
        DBManager dbManager = DBManager.getInstance();

        dbManager.insertUser(User.createUser("petrov"));
        dbManager.insertUser(User.createUser("obama"));
        printList(dbManager.findAllUsers());
        dbManager.insertTeam(Team.createTeam("teamB"));
        dbManager.insertTeam(Team.createTeam("teamC"));
        printList(dbManager.findAllTeams());
        User userPetrov = dbManager.getUser("petrov");
        User userIvanov = dbManager.getUser("ivanov");
        User userObama = dbManager.getUser("obama");
        Team teamA = dbManager.getTeam("teamA");
        Team teamB = dbManager.getTeam("teamB");
        Team teamC = dbManager.getTeam("teamC");
        dbManager.setTeamsForUser(userIvanov, teamA);
        dbManager.setTeamsForUser(userPetrov, teamA, teamB);
        dbManager.setTeamsForUser(userObama, teamA, teamB, teamC);
        for (User user : dbManager.findAllUsers()) {
            printList(dbManager.getUserTeams(user));
        }
        dbManager.deleteTeam(teamA);
        printList(dbManager.findAllTeams());
        teamC.setName("teamX");
        dbManager.updateTeam(teamC);
        printList(dbManager.findAllTeams());





    }
}
