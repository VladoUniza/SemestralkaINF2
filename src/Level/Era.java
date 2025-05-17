package Level;

public enum Era {
    STONE_AGE(1),
    ROMAN_EMPIRE(2);

    private final int number;

    Era(int n){
        this.number = n;
    }

    public int getNumber(){
        return this.number;
    }
}