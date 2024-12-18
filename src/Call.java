public class Call {
    int id;
    int startFloor;
    int endFloor;

    public Call(int id, int startFloor, int endFloor) {
        this.id = id;
        this.startFloor = startFloor;
        this.endFloor = endFloor;
    }

    @Override
    public String toString() {
        return "id=" + id + ", startFloor=" + startFloor + ", endFloor=" + endFloor;
    }
}
