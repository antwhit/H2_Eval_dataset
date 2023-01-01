import java.util.ArrayList;

/**
 * @author Dimby ANDRIANASOLO
 *
 * La classe abstraite Liaison masque le support "physique" utilis� qui relie
 * les �l�ments du r�seau. Lorsqu'un noeud du r�seau veut acc�der au
 * support, l'algorithme CSMA/CD est mis en oeuvre: on �coute le support
 * avant de commencer l'�mission.
 */
public abstract class Liaison {

    /**
	 * liste des interfaces r�seau utilisant la liaison
	 */
    private ArrayList<InterfaceReseaux> listeIface;

    /** Accesseur : liste des interfaces r�seau
	 * @return ArrayList d'interfaces r�seau
	 */
    public ArrayList<InterfaceReseaux> getListeIface() {
        return this.listeIface;
    }

    private Simulateur simulateur;

    /**
	 * @param r�initialiser la liste des interfaces
	 */
    public void setListeIface(ArrayList<InterfaceReseaux> listeInterface) {
        this.listeIface = listeInterface;
    }

    public Simulateur getSimulateur() {
        return simulateur;
    }

    /** Modifier le Simulateur
	 * @param simulateur � utiliser
	 */
    public void setSimulateur(Simulateur S) {
        simulateur = S;
    }

    /**
	 * le support est-il occup� par un autre ?
	 * @return occupation du support
	 */
    public boolean supportEstOccupe() {
        return false;
    }

    /**
	 * Une collision est-elle d�tect�e ?
	 * @return VRAI si collision d�tect�e
	 */
    public boolean collisionDetectee() {
        return false;
    }

    /** Signaler une collision
	 * ie executer l'operation traiterCollision pour chaque interface concern�e
	 */
    public void signalerCollision() {
        for (int i = 0; i < getListeIface().size(); i++) {
            InterfaceReseaux iface = (InterfaceReseaux) getListeIface().get(i);
            if (iface.estActive()) {
                System.out.println("Signalisation de collision pour " + iface.getElementReseauxParent().getNom() + "/" + iface.getNom());
                getSimulateur().enregistrer(new Evenement(getSimulateur().getDateCourante(), new OperationTraiterCollision(iface)));
            }
        }
    }

    /**
	 * Transmettre une trame
	 * 	= d'un bout � l'autre pour une liaison PointAPoint;
	 * 	= r�p�ter sur tous les ports pour un HUB;
	 * 	= commuter vers la destination pour un SWITCH;
	 * 	etc
	 * @param T trame � transmettre
	 * @param ifaceSource Interface r�seau �mettrice de la trame
	 * Mise en oeuvre : Enregistrer un �v�nement (imm�diat) pour traiter la Trame sur
	 * chacune des interfaces concern�es
	 */
    public abstract void transmettreTrame(Trame T, InterfaceReseaux ifaceSource);

    /**
	 * Brancher une nouvelle Interface r�seau sur la liaison.
	 * Attention, une exception sera provoqu�e
	 * si on assigne trois IR � une liaison PointAPoint...
	 */
    public void ajouterInterfaceReseau(InterfaceReseaux iface) {
        getListeIface().add(iface);
    }

    /**
	 * D�connecter une interface.
	 */
    public void retirerInterfaceReseau(InterfaceReseaux iface) {
        listeIface.remove(iface);
    }

    /**
	 * Le nombre d'interfaces en train d'�mettre simultan�ment sur la liaison
	 * @return nombre d'interfaces actives
	 */
    public int nbIfacesActives() {
        int nb = 0;
        for (int i = 0; i < getListeIface().size(); i++) {
            if (getListeIface().get(i).estActive()) {
                nb++;
            }
        }
        return nb;
    }
}
