package TaskForSecondTeam.firstTask.myThreads;

import TaskForSecondTeam.firstTask.Scene;

public class Chandler implements Runnable {
    Scene scene;

    public Chandler(Scene scene) {
        this.scene = scene;
    }

    @Override
    public void run() {
        while(scene.i < scene.getSizeList()) {
            System.out.println("Chandler: " + scene.nextPhrase());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}