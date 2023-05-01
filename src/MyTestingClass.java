public class MyTestingClass {
    private int value;

    public MyTestingClass(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
// result mus be prime number
    @Override
    public int hashCode() {
        int result = 17;
        result = 23 * result + value;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (!(obj instanceof MyTestingClass)) return false;

        MyTestingClass other = (MyTestingClass) obj;
        return this.value == other.value;
    }

}