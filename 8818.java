public class OperationEnvoyerSegmentSuivant extends OperationCouche4 {

    private int identifiantCom;

    /**
	 * Construire une OperationEnvoyerSegmentSuivant a partir d'un protocole et d'un identifiant de communication
	 * @param protocole le protocole qui effectue l'operation
	 * @param identifiantCom identifiant de communication
	**/
    public OperationEnvoyerSegmentSuivant(Protocole protocole, int identifiantCom) {
        super(protocole);
        this.identifiantCom = identifiantCom;
    }

    /**
	 * Executer l'OperationEnvoyerSegmentSuivant
	 * @param simulateur simulateur realisant l'evenement
	 * @param dateCourante date courante
	**/
    public void executer(Simulateur simulateur, int dateCourante) throws ReseauException {
        protocole.envoyerDATAProtocole(simulateur, dateCourante, this.identifiantCom);
    }
}
