package TaskForSecondTeam.firstTask.myThreads;

import TaskForSecondTeam.firstTask.Scene;

public class Ross implements Runnable {
    Scene scene;

    public Ross(Scene scene) {
        this.scene = scene;
    }

    @Override
    public void run() {
        while(scene.i < scene.getSizeList()) {
            System.out.println("Ross: " + scene.nextPhrase());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
