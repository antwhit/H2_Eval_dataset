/**
 * Wheel joint definition. This requires defining a line of
 * motion using an axis and an anchor point. The definition uses local
 * anchor points and a local axis so that the initial configuration
 * can violate the constraint slightly. The joint translation is zero
 * when the local anchor points coincide in world space. Using local
 * anchors and a local axis helps when saving and loading a game.
**/
public class b2WheelJointDef extends b2JointDef {

    public b2WheelJointDef() {
        type = b2JointType.e_wheelJoint;
        localAnchorA = new b2Vec2(0.0f, 0.0f);
        localAnchorB = new b2Vec2(0.0f, 0.0f);
        localAxisA = new b2Vec2(1.0f, 0.0f);
        enableMotor = false;
        maxMotorTorque = 0.0f;
        motorSpeed = 0.0f;
        frequencyHz = 2.0f;
        dampingRatio = 0.7f;
    }

    public void release() {
        localAnchorA.release();
        localAnchorB.release();
    }

    public void Initialize(b2Body bodyA, b2Body bodyB, b2Vec2 anchor, b2Vec2 axis) {
        this.bodyA = bodyA;
        this.bodyB = bodyB;
        jni_b2WheelJointDef_Initialize(localAnchorA.address, localAnchorB.address, localAxisA.address, bodyA.address, bodyB.address, anchor.address, axis.address);
    }

    private static native void jni_b2WheelJointDef_Initialize(int localAnchorA, int localAnchorB, int localAxisA, int bodyA, int bodyB, int anchor, int axis);

    public b2Vec2 localAnchorA;

    public b2Vec2 localAnchorB;

    public b2Vec2 localAxisA;

    public boolean enableMotor;

    public float maxMotorTorque;

    public float motorSpeed;

    public float frequencyHz;

    public float dampingRatio;
}
