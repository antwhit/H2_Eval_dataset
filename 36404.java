/**
 * OperationLancerPing4 modelise l'operation realisant l'action 
 * correspondante a lancer un ping4
**/
public class OperationLancerPing4 extends OperationCouche4 {

    private AdresseIP destination;

    private int nbSegmentAemettre;

    private String message;

    /**
	 * Construire une OperationLancerPing4 a partir d'une station
	 * d'une adresse ip de destination et d'un nombre de segment a emettre
	 * @param station station emetteur
	 * @param destination adresse ip de destination du ping4
	 * @param nbSegmentAemettre nombre de ping a emettre
	 * @param message le message utilisateur
	**/
    public OperationLancerPing4(Station station, AdresseIP destination, int nbSegmentAemettre, String message) {
        super(station.getPing4());
        this.destination = destination;
        this.nbSegmentAemettre = nbSegmentAemettre;
        this.message = message;
    }

    /**
	 * Executer l'OperationLancerPing4
	 * @param simulateur simulateur realisant l'evenement
	 * @param dateCourante date courante
	**/
    public void executer(Simulateur simulateur, int dateCourante) throws ReseauException {
        System.out.println(dateCourante + ": Operation lancer " + nbSegmentAemettre + " Ping4 de " + protocole.getStation().getNom() + " vers " + destination.toString() + ", message: \"" + this.message + "\"");
        ((Ping4) protocole).nouveauPing(simulateur, dateCourante, destination, nbSegmentAemettre, message);
    }
}
