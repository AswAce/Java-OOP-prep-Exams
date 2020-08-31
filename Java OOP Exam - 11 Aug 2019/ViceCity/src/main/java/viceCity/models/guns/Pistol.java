package viceCity.models.guns;

public class Pistol extends BaseGun {
    private static final int SHOOTING=1;
    private static final int BULLETS_PER_BARREL = 10;
    private static final int TOTAL_BULLETS = 100;

    public Pistol(String name) {
        super(name, BULLETS_PER_BARREL, TOTAL_BULLETS);
    }

    @Override
    public int fire() {
        reload();
        return SHOOTING;
    }

    private void reload() {
        if (super.getBulletsPerBarrel() <= 0) {
            super.setBulletsPerBarrel(BULLETS_PER_BARREL);
            super.setTotalBullets(getTotalBullets() - BULLETS_PER_BARREL);
        }
        super.setBulletsPerBarrel(super.getBulletsPerBarrel() - 1);
    }
}
