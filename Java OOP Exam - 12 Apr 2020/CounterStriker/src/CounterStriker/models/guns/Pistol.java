package models.guns;

public class Pistol extends GunImpl {
    private static final int BULLETS_TO_FIRE = 1;

    public Pistol(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {
        this.setBulletsCount(Math.max(0, super.getBulletsCount() - BULLETS_TO_FIRE));
        if (super.getBulletsCount() == 0) {
            return 0;
        }
        return BULLETS_TO_FIRE;
    }
}
