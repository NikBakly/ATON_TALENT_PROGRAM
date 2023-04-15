package TaskForSecondTeam.firstTask.myThreads;

import TaskForSecondTeam.firstTask.Scene;

public class Phoebe implements Runnable {
    Scene scene;

    public Phoebe(Scene scene) {
        this.scene = scene;
    }

    @Override
    public void run() {
        while(scene.i < scene.getSizeList()) {
            System.out.println("Phoebe: " + scene.nextPhrase());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}