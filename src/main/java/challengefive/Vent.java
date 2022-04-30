package challengefive;


public class Vent {
    private int x1;
    private int x2;
    private int y1;
    private int y2;

    public Vent(String[] split, String[] split1) {
        this.x1 = Integer.parseInt(split[0]);
        this.y1 = Integer.parseInt(split[1]);
        this.x2 = Integer.parseInt(split1[0]);
        this.y2 = Integer.parseInt(split1[1]);
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getY1() {
        return y1;
    }

    public int getY2() {
        return y2;
    }
}