import checkers.igj.quals.*;
import java.awt.Point;

public class UnknownGenericHack<T extends @ReadOnly Object>
{
	private T val;
	public UnknownGenericHack(T val) @AssignsFields
	{
		this.val = val;
	}
	public static @Mutable Point hack(@Immutable Point p)
	{
		UnknownGenericHack<?> wrapper = new UnknownGenericHack<@Immutable Point>(p);
		return (Point) wrapper.val;
	}
	
	public static class Foo 
	{
		private static Foo ref;
		public Foo() @Mutable
		{
			ref = this;
		}
	}
	public static class InnerClassHack extends Foo
	{
		private static @ReadOnly InnerClassHack lastRef;
		private @Immutable Point p;
		private Runnable r;
		@AssignsFields
		public InnerClassHack(@Immutable Point point) @AssignsFields
		{
			super();
			lastRef = this;
			this.p = point;
			r = new Runnable()
			{
				public void run()
				{
					p = new @Immutable Point();
				}
			};
		}
		
		public void resetPoint() @Immutable
		{
			r.run();
		}
	}
	
	public static @Immutable Foo makeFoo()
	{
		return new @Immutable Foo();
	}
}