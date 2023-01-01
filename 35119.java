import java.util.*;
import javax.swing.tree.*;

public class Favor extends Element {

    private String address;

    private String comment;

    /** Constructeur d'une feuille
     * @param name - nom du favoris
     * @param add - adresse internet du favoris
     * @param com - commentaires associes
     * @param model - noeud principal de l'arbre
     **/
    public Favor(String name, String add, String com, FavorTreeModel model) {
        this.name = name;
        this.address = add;
        this.comment = com;
        this.model = model;
    }

    public void accept(Visitor v) {
        v.visitFavor(this);
    }

    /** Affectation de l'adresse
     * @param add adresse du favoris
     **/
    public void setAddress(String add) {
        this.address = add;
    }

    /** Obtenir l'adresse
     * @return l'adresse du favoris
     **/
    public String getAddress() {
        return address;
    }

    /** Affectation du commentaire
     * @param com commentaires du le favoris
     **/
    public void setComment(String com) {
        this.comment = com;
    }

    public void add(TreeNode node) {
    }

    public void remove(TreeNode node) {
    }

    /** Obtenir les commentaires
     * @return les commentaires rattach�es � un favoris
     **/
    public String getComment() {
        return comment;
    }

    /** Retourne les fils du receveur sous forme d'une enumeration
     * @return l'ensemble des fils sous forme d'enumeration
     **/
    public Enumeration children() {
        return null;
    }

    /** Rtourne vraie si le receveur allows children
     **/
    public boolean getAllowsChildren() {
        return false;
    }

    /** Retourne le fils � l'index
     * @param childIndex - position du fils
     **/
    public TreeNode getChildAt(int childIndex) {
        return null;
    }

    /** Retourne le nombre de fils contenu
     * @return le nombre de fils du receveur
     **/
    public int getChildCount() {
        return 0;
    }

    /** Retourne l'index du fils correspondant
     * @param node - noeud fils
     **/
    public int getIndex(TreeNode node) {
        return -1;
    }

    /** Renvoit une repr�sentation texte du favoris
     * @return la repr�sentation texte
     **/
    public String toString() {
        return this.getName();
    }
}
