package TaskForSecondTeam.firstTask;

import TaskForSecondTeam.firstTask.myThreads.*;

public class FriendsScene {
    public static void main(String[] args) {
        Scene scene = new Scene();
        Thread chandler = new Thread(new Chandler(scene));
        Thread joey = new Thread(new Joey(scene));
        Thread monica = new Thread(new Monica(scene));
        Thread phoebe = new Thread(new Phoebe(scene));
        Thread rachel = new Thread(new Rachel(scene));
        Thread ross = new Thread(new Ross(scene));

        chandler.start();
        joey.start();
        monica.start();
        phoebe.start();
        rachel.start();
        ross.start();
    }

}



