/**
 * Prismatic joint definition. This requires defining a line of
 * motion using an axis and an anchor point. The definition uses local
 * anchor points and a local axis so that the initial configuration
 * can violate the constraint slightly. The joint translation is zero
 * when the local anchor points coincide in world space. Using local
 * anchors and a local axis helps when saving and loading a game.
**/
public class b2PrismaticJointDef extends b2JointDef {

	public b2PrismaticJointDef()
	{
		type = b2JointType.e_prismaticJoint;

		localAnchorA = new b2Vec2(0.0f, 0.0f);
		localAnchorB = new b2Vec2(0.0f, 0.0f);
		localAxisA = new b2Vec2(1.0f, 0.0f);

		referenceAngle = 0.0f;
		enableLimit = false;
		lowerTranslation = 0.0f;
		upperTranslation = 0.0f;
		enableMotor = false;
		maxMotorForce = 0.0f;
		motorSpeed = 0.0f;
	}

	public void release() {
		localAnchorA.release();
		localAnchorB.release();
		localAxisA.release();
	}

	/// Initialize the bodies, anchors, axis, and reference angle using the world
	/// anchor and unit world axis.
	public void Initialize(b2Body bodyA, b2Body bodyB, b2Vec2 anchor, b2Vec2 axis) {

		this.bodyA = bodyA;
		this.bodyB = bodyB;
		referenceAngle = jni_b2PrismaticJointDef_Initialize(
								localAnchorA.address, localAnchorB.address, localAxisA.address,
								bodyA.address, bodyB.address,
								anchor.address, axis.address
							);
	}

	private static native float jni_b2PrismaticJointDef_Initialize(
		int localAnchorA, int localAnchorB, int localAxisA
		int bodyA, int bodyB,
		int anchor, int axis
	);

	/// The local anchor point relative to bodyA's origin.
	public b2Vec2 localAnchorA;

	/// The local anchor point relative to bodyB's origin.
	public b2Vec2 localAnchorB;

	/// The local translation unit axis in bodyA.
	public b2Vec2 localAxisA;

	/// The constrained angle between the bodies: bodyB_angle - bodyA_angle.
	public float referenceAngle;

	/// Enable/disable the joint limit.
	public boolean enableLimit;

	/// The lower translation limit, usually in meters.
	public float lowerTranslation;

	/// The upper translation limit, usually in meters.
	public float upperTranslation;

	/// Enable/disable the joint motor.
	public boolean enableMotor;

	/// The maximum motor torque, usually in N-m.
	public float maxMotorForce;

	/// The desired motor speed in radians per second.
	public float motorSpeed;
}
