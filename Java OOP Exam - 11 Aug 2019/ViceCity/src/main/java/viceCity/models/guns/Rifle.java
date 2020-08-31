package viceCity.models.guns;

public class Rifle extends BaseGun {
    private static final int SHOOTING = 5;
    private static final int BULLETS_PER_BARREL = 50;
    private static final int TOTAL_BULLETS = 500;

    public Rifle(String name) {
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
        super.setBulletsPerBarrel(super.getBulletsPerBarrel() - SHOOTING);
    }
}
