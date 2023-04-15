package TaskForSecondTeam.firstTask;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    public volatile int i = 0;
    private final List<String> scripts = new ArrayList<>();

    public Scene() {
        scripts.add("Hey, hey.");
        scripts.add("Hey.");
        scripts.add("Hey.");
        scripts.add("And this from the cry-for-help department. Are you wearing makeup?");
        scripts.add("Yes, I am. As of today, I am officially Joey Tribbiani, actor slash model.");
        scripts.add("That's so funny, cause I was thinking you look more like Joey Tribbiani, man slash woman.");
        scripts.add("What were you modeling for?");
        scripts.add("You know those posters for the City Free Clinic?");
        scripts.add("Oh, wow, so you're gonna be one of those \"healthy, healthy, healthy guys\"?");
        scripts.add("You know, the asthma guy was really cute.");
        scripts.add("Do you know which one you're gonna be?");
        scripts.add("No, but I hear lyme disease is open, so... (crosses fingers)");
        scripts.add("Good luck, man. I hope you get it.");
        scripts.add("Thanks.");
    }

    public synchronized String nextPhrase() {
        return scripts.get(i++);
    }

    public int getSizeList() {
        return scripts.size();
    }
}
