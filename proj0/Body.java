public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    static final double G = 6.67e-11;

    public Body(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b){
        this(b.xxPos, b.yyPos, b.xxVel, b.yyVel, b.mass, b.imgFileName);
    }

    public double calcDistance(Body b){
        return Math.sqrt(Math.pow((b.xxPos - this.xxPos), 2)
                                  + Math.pow((b.yyPos - this.yyPos), 2));
    }

    public double calcForceExertedBy(Body b){
        double distance = calcDistance(b);
        return (G * this.mass * b.mass)/Math.pow(distance, 2);
    }

    public double calcForceExertedByX(Body b){
        double dx = b.xxPos - this.xxPos;
        double F = calcForceExertedBy(b);
        double r = calcDistance(b);
        return (F * dx) / r;
    }

    public double calcForceExertedByY(Body b){
        double dy = b.yyPos - this.yyPos;
        double F = calcForceExertedBy(b);
        double r = calcDistance(b);
        return (F * dy) / r;
    }

    public double calcNetForceExertedByX(Body[] b){
        double netForceX = 0.0;
        for (Body planet : b) {
            if (!this.equals(planet)) {
                netForceX += calcForceExertedByX(planet);
            }
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Body[] b){
        double netForceY = 0.0;
        for (Body planet : b) {
            if (!this.equals(planet)) {
                netForceY += calcForceExertedByY(planet);
            }
        }
        return netForceY;
    }

    public void update(double dt, double fX, double fY){
        double accX = fX / mass;
        double accY = fY / mass;
        xxVel += dt * accX;
        yyVel += dt * accY;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
