package education.patterns.facade;

public class HomeTheaterFacade {
    private Amplifier amplifier;
    private CdPlayer cdPlayer;
    private DvdPlayer dvdPlayer;
    private Tuner tuner;
    private Screen screen;

    public HomeTheaterFacade(Amplifier amplifier, CdPlayer cdPlayer, DvdPlayer dvdPlayer, Tuner tuner, Screen screen) {
        this.amplifier = amplifier;
        this.cdPlayer = cdPlayer;
        this.dvdPlayer = dvdPlayer;
        this.tuner = tuner;
        this.screen = screen;
    }

    public void watchMovie() {
        System.out.println("Getting ready to watch movie");
        screen.down();
        amplifier.on();
        dvdPlayer.on();
        dvdPlayer.play();
    }

    public void endMovie() {
        dvdPlayer.off();
        amplifier.off();
        screen.up();
    }
}
