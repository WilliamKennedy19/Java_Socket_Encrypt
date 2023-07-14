package DLP;
public interface pollardsRho {

    public double pollard(int x, int p, int g, int h);    
    public String[] find();
}


class pollardDLP implements pollardsRho {

    public double pollard(int x, int p, int g, int h) {

        if (0 <= x  && x <= (p/3)) {
            return g*x;
        }

        else if ((p/3) <= x && x <= (2*p/3)) {
            return Math.pow(x, 2);
        }

        else if ((2*p/3) <= x && x <= p) {
            return h*x;
        }


        return 0;
    }

    public String[] find() {
        String[] array = {};

        return array;
    }
}
