/**
 * @(#)DataSimples.java
 *
 *
 * @author 
 * @version 1.00 2009/9/1
 */

public class DataSimples {
class DataSimples
    /**
     * Creates a new instance of <code>DataSimples</code>.
     */
    public DataSimples() {
    		byte dia; 
	byte mes;
	short ano;
	
	void inicializaDataSimples(byte dia, byte mes, short ano)
	{	
		if (dataEValida(dia, mes, ano))
			{
				this.dia= dia; this.mes = mes; this.ano = ano;
			}
		else
			{				
				this.dia = 0; this.mes = 0; this.ano = 0; 
			}
	}
	
	boolean dataEValida(byte dia,  byte mes, short ano)
	{
		if ((dia>=1) && (dia<=31) &&(mes>=1) &&(mes<12))
			return true;
		else 
			return false;
	}
	
	boolean eIgual(DataSimples outraDataSimples)
	{
		if ((this.dia == outraDataSimples.dia) &&
		    (this.mes == outraDataSimples.mes) &&
		    (this.ano == outraDataSimples.ano))
				return true;
		else
				return false;
 	}

	void mostraDataSimples()
	{
		System.out.print (dia);
		System.out.print ("/");
		System.out.print(mes);
		System.out.print ("/");
		System.out.println (ano);
	}
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
}
