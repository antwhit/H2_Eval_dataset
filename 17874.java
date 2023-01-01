// test the lexer on a variety of tokens

import java.io.*;
import java.util.*;
import jbf.*;
import YYlex;

<MAINA+>public class Maina {
<MAINB+>public class Mainb {

public static Reader stdin = new BufferedReader(new InputStreamReader(System.in));
public static PrintWriter stdout = new PrintWriter(System.out,true);

public static Reader in1 = null;
public static PrintWriter out1 = null;

public static Reader in2 = null;
public static PrintWriter out2 = null;

public static Parseargs pa = null;

// First parser/lexer pair
public static YYlex yyl1 = null;
public static YYparse yyp1 = null;

// Second parser/lexer pair
<MAINA+>public static YYlex yyl2 = null;
<MAINA+>public static YYparse yyp2 = null;
<MAINB+>public static YYlex2 yyl2 = null;
<MAINB+>public static YYparse2 yyp2 = null;

// Argument flags
static boolean debuglex = false;
static boolean debugparse = false;
static int llevel = 0;
static int plevel = 0;

public static void main(String argv[])
{
    Object yylval;
    YYtoken yytoken;

    pa = new Parseargs();

    preprocessflags();
    String msg = pa.parseargs(argv);
    if(msg != null) {
	System.err.println(msg);
	return;
    }
    postprocessflags();

    // Construct both pairs of parser/lexers

    yyl1 = new YYlex(in1,out1);
<MAINA+>    yyl2 = new YYlex(in2,out2);
<MAINB+>    yyl2 = new YYlex2(in2,out2);

//    yylval = yyl1.yylval();
//    yylval = yyl2.yylval();

    yyp1 = new YYparse(yyl1);
<MAINA+>	yyp2 = new YYparse(yyl2);
<MAINB+>	yyp2 = new YYparse2(yyl2);

    if(debuglex) {
	stdout.println("lex debug turned on");
	if(llevel > 0) yyl1.setdebug(llevel); else yyl1.setdebug(true);
	if(llevel > 0) yyl2.setdebug(llevel); else yyl2.setdebug(true);
    }
    if(yyp1 != null && yyp2 != null && debugparse) {
	stdout.println("parse debug turned on");
	if(plevel > 0) yyp1.setdebug(plevel); else yyp1.setdebug(true);
	if(plevel > 0) yyp2.setdebug(plevel); else yyp2.setdebug(true);
    }
    try {        
	stdout.println("Starting parse 1.");
	yyp1.yyparse();
	stdout.println("Parse 1 succeeded.");
	stdout.println("Starting parse 2.");
	yyp2.yyparse();
	stdout.println("Parse 2 succeeded.");
    } catch (Exception e) {
        stdout.println("exception detected:" + e.getMessage());
        e.printStackTrace();
        stdout.println("parse failed");
    }
}

static
void
preprocessflags()
{
    pa.addflag("-debug",null);
    pa.addflag("-debuglex",null);
    pa.addflag("-debugparse",null);
    pa.addflag("-l");
    pa.addflag("-p");
    pa.addflag("-in1");
    pa.addflag("-out1");
    pa.addflag("-in2");
    pa.addflag("-out2");
}

static
void
postprocessflags()
{
    Vector flagargs = pa.flaglist();
    for(int i=0;i<flagargs.size();i++) {
	String fl = (String)flagargs.elementAt(i);
	String value = pa.value(fl);
	if(fl.equals("-debug")) {debuglex = debugparse = true;}
	else if(fl.equals("-debuglex")) {debuglex = true;}
	else if(fl.equals("-debugparse")) {debugparse = true;}
	else if(fl.equals("-l")) {
	    try {
		llevel = Integer.valueOf(value).intValue();
	    } catch (Exception e) {
		System.err.println("-l requires an integer argument");
	 	return;
	    }
	} else if(fl.equals("-p")) {
	    try {
		plevel = Integer.valueOf(value).intValue();
	    } catch (Exception e) {
		System.err.println("-p requires an integer argument");
	 	return;
	    }
	} else if(fl.equals("-in1")) {
	    try {
		in1 = new BufferedReader(new FileReader(value));
	    } catch (Exception e) {
		System.err.println("-stdin1 cannot open:"+value);
	 	return;
	    }
	} else if(fl.equals("-out1")) {
	    try {
		out1 = new PrintWriter(new FileWriter(value),true);
	    } catch (Exception e) {
		System.err.println("-stdout1 cannot open:"+value);
	 	return;
	    }
	} else if(fl.equals("-in2")) {
	    try {
		in2 = new BufferedReader(new FileReader(value));
	    } catch (Exception e) {
		System.err.println("-stdin2 cannot open:"+value);
	 	return;
	    }
	} else if(fl.equals("-out2")) {
	    try {
		out2 = new PrintWriter(new FileWriter(value),true);
	    } catch (Exception e) {
		System.err.println("-stdout2 cannot open:"+value);
	 	return;
	    }
	}
    }
}

}; /*class Main*/
