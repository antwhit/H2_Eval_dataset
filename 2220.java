/**
 * This example is a narrowing example 
 *
 */

/*
 * Sean!
 * Is extending an object a mandatory thing in your grammar? or not? 
 */
class Fig extends Object{
	int fixed;
}

Fig event Changed{
	Fig fe;
	provides fe!=null
	requires {
		/*
		 * Sean!
		 * Can we check here that body of provides and requires access only variables
		 * that are inside event type specification?
		 * 
		 * For example here the only variable contracts can use is "fe", and nothing else
		 * */
		if(fe.fixed==0){
			invoke(next);
			preserves fe ==old(fe);
		}
		else{
			preserves fe == old(fe);
		}
	}
}


class Enforce extends Object{

	Fig check(thunk Fig rest, Fig fe){
		if(fe.fixed==0){
			invoke(rest);
		}
		else{
			refining preserves fe == old (fe){
				fe;
			}
		}
	}
	/*
	 * When expression intentionally moved to the end of the
	 * handler, to check that its position in handler class is not important
	 */
	when Changed do check; 
}