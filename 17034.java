import com.jme.math.Vector3f;
import static com.jme.math.FastMath.cos;
import static com.jme.math.FastMath.sin;
import static com.jme.math.FastMath.PI;
import static com.jme.math.FastMath.TWO_PI;

public class Player implements Animated, EventListener {

    private static final float SPEED = 4f;

    private static final float TURN_SPEED = PI * 2f;

    private static final float FALL_SPEED = -10f;

    private static final float JUMP_SPEED = 30f;

    private static final float JUMP_TIME = 0.2f;

    private static final float RADIUS = 0.5f;

    private static final Vector3f KNEES = new Vector3f(0f, 0.4f, 0f);

    private static final Vector3f DOWN_DISP = new Vector3f(0f, FALL_SPEED, 0f);

    private String id;

    private EventManager eventManager;

    private Grid grid;

    private boolean forward;

    private boolean back;

    private boolean left;

    private boolean right;

    private boolean jump;

    private Vector3f position;

    private float orientation;

    private float red;

    private float green;

    private float blue;

    private boolean onFloor;

    private float jumping;

    public Player(String id, EventManager eventManager, Grid grid, float red, float green, float blue) {
        this.id = id;
        this.eventManager = eventManager;
        this.grid = grid;
        this.red = red;
        this.green = green;
        this.blue = blue;
        position = new Vector3f(0, 0, 0);
        onFloor = true;
    }

    public void receiveEvent(Event event) {
        switch(event.type()) {
            case Event.COMMAND_FORWARD:
                forward = true;
                break;
            case Event.COMMAND_BACK:
                back = true;
                break;
            case Event.COMMAND_LEFT:
                left = true;
                break;
            case Event.COMMAND_RIGHT:
                right = true;
                break;
            case Event.COMMAND_JUMP:
                jump = true;
                break;
        }
    }

    public void update(float tpf) {
        Vector3f oldPosition = new Vector3f(position);
        float oldOrientation = orientation;
        float speed = 0f;
        if (forward) speed = SPEED * tpf;
        if (back) speed = -SPEED * tpf / 2f;
        if (left) orientation += TURN_SPEED * tpf;
        if (right) orientation -= TURN_SPEED * tpf;
        Vector3f velocity = new Vector3f(sin(orientation), 0f, cos(orientation));
        velocity.multLocal(speed);
        position.addLocal(velocity);
        if (jump && onFloor) jumping = JUMP_TIME;
        if (jumping > 0) {
            Vector3f jumpVelocity = new Vector3f(0, 1, 0);
            jumpVelocity.multLocal(JUMP_SPEED * tpf);
            position.addLocal(jumpVelocity);
            jumping -= tpf;
        }
        Vector3f origin = position.add(KNEES);
        for (float angle = 0; angle < TWO_PI; angle += TWO_PI / 12f) {
            Vector3f disp = new Vector3f(sin(angle + orientation) * RADIUS, 0f, cos(angle + orientation) * RADIUS);
            RaySegment ray = new RaySegment(origin, disp);
            float d = grid.getDistance(ray);
            if (d != Float.MAX_VALUE) {
                disp.multLocal(d - RADIUS);
                origin.addLocal(disp);
                position.addLocal(disp);
            }
        }
        RaySegment ray = new RaySegment(origin, DOWN_DISP);
        float d = KNEES.y - grid.getDistance(ray);
        onFloor = true;
        if (d > 0) position.y += d; else if (d < 0) {
            float fall = FALL_SPEED * tpf;
            if (fall < d) fall = d; else onFloor = false;
            position.y += fall;
        }
        if (oldPosition.x != position.x || oldPosition.y != position.y || oldPosition.z != position.z || oldOrientation != orientation) eventManager.send(Event.PLAYER_MOVED, "id", id, "x", position.x, "y", position.y, "z", position.z, "orientation", orientation);
        forward = back = right = left = jump = false;
    }

    public String id() {
        return id;
    }

    public float x() {
        return position.x;
    }

    public float y() {
        return position.y;
    }

    public float z() {
        return position.z;
    }

    public float orientation() {
        return orientation;
    }

    public float red() {
        return red;
    }

    public float green() {
        return green;
    }

    public float blue() {
        return blue;
    }
}
