package education.patterns.facade;

public class HomeTheaterTestDrive {
    public static void main(String[] args) {
        Amplifier amplifier = new Amplifier();
        CdPlayer cdPlayer = new CdPlayer();
        DvdPlayer dvdPlayer = new DvdPlayer();
        Tuner tuner = new Tuner();
        Screen screen = new Screen();

        HomeTheaterFacade homeTheater = new HomeTheaterFacade(amplifier, cdPlayer, dvdPlayer, tuner, screen);

        homeTheater.watchMovie();
        homeTheater.endMovie();
    }
}
