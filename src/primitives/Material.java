package primitives;

public class Material {

    public Double3 kD = Double3.ZERO, kS = Double3.ZERO;
    public int nShininess = 0;

    public Material setKd(double kd) {
        this.kD = new Double3(kd);
        return this;
    }

    public Material setKd(Double3 kd) {
        this.kD = kd;
        return this;
    }

    public Material setKs(double ks) {
        this.kS = new Double3(ks);
        return this;
    }

    public Material setKs(Double3 ks) {
        this.kS = ks;
        return this;
    }

    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

}
