/**
 * Ein Set kappselt ein Nodeobjekt und bietet einfachere Zugriffe
 * auf dieses.
 * 
 * @author Christian
 *
 */
public abstract class Set {

    protected Node node = null;

    /**
	 * Fuegt ein neues Objekt dem Set hinzu.
	 * @throws AppException wenn Objekt schon gespeichert wurde
	 * @param object neues Objekt
	 */
    protected void insert(Object object) throws AppException {
        if (node == null) {
            node = new Node(object);
        } else {
            Node.NodeIterator ni = this.node.new NodeIterator(this.node);
            Object elem;
            while (ni.hasNext()) {
                elem = ni.next();
                if (elem == object) {
                    throw new AppException("Objekt bereits vorhanden");
                }
            }
            Node oldnode = this.node;
            this.node = new Node(object);
            this.node.setNext(oldnode);
        }
    }

    /**
	 * Liefert null wenn keine elemente gespeichert sind
	 * Liefert den Iterator von node wenn ein Element vorhanden ist
	 * @return
	 * @throws AppException wenn kein Element sich im Iterator befindet
	 */
    Node.NodeIterator iterator() throws AppException {
        if (this.node == null) {
            throw new AppException("Keine Daten gefunden");
        } else {
            return this.node.new NodeIterator(this.node);
        }
    }
}
