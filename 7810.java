import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import de.fau.cs.dosis.drug.ActiveIngredientFilter;
import de.fau.cs.dosis.drug.ActiveIngredientFilterColumn;
import de.fau.cs.dosis.drug.ActiveIngredientNotFoundException;
import de.fau.cs.dosis.drug.ActiveIngredientQueryFilter;
import de.fau.cs.dosis.drug.DrugVersion;
import de.fau.cs.dosis.model.ActiveIngredient;
import de.fau.cs.dosis.model.DrugColumnType;
import de.fau.cs.dosis.model.SubstanceClass;

public class ActiveIngredientManagerDeleted {

    @Override
    public void deleteDrugById(int activeIngredientId) throws ActiveIngredientNotFoundException {
        ActiveIngredient drug = entityManager.find(ActiveIngredient.class, activeIngredientId);
        if (drug != null) {
            entityManager.remove(drug);
        } else {
            throw new ActiveIngredientNotFoundException(activeIngredientId);
        }
    }

    @Override
    public ActiveIngredient storeDrug(ActiveIngredient d) {
        Set<SubstanceClass> new_classes = new HashSet<SubstanceClass>();
        for (SubstanceClass sc : d.getSubstanceClasses()) {
            Query n = entityManager.createNativeQuery("SELECT id FROM substanceclasses WHERE name = '" + sc.getName() + "';");
            if (n.getResultList().size() == 0) entityManager.persist(sc); else {
                sc = entityManager.find(SubstanceClass.class, (Integer) n.getResultList().get(0));
            }
            new_classes.add(sc);
        }
        d.setSubstanceClasses(new_classes);
        if (d.getId() != null && d.getId().intValue() != 0) {
            Query n = entityManager.createNativeQuery("SELECT id FROM drugs WHERE id = '" + d.getId() + "';");
            if (n.getResultList().size() == 0) entityManager.persist(d); else entityManager.merge(d);
        } else {
            entityManager.persist(d);
        }
        return d;
    }

    private List<ActiveIngredientFilter> getAllDrugFitlersNative(ActiveIngredientQueryFilter filter, int from, int limit) {
        List<ActiveIngredientFilter> r = new ArrayList<ActiveIngredientFilter>(limit);
        entityManager.createNativeQuery("" + "CREATE OR REPLACE TEMP VIEW drugList AS " + "SELECT id, name, type FROM (" + "(SELECT id, brandname AS name, '" + DrugColumnType.BRAND_NAME + "' AS type FROM drugs) " + "UNION " + "(SELECT id, substanceclass AS name, '" + DrugColumnType.ACTIVE_INGREDIENT + "' AS type FROM drugs)" + ") AS list ORDER BY list.name ASC").executeUpdate();
        entityManager.flush();
        Query n = entityManager.createNativeQuery("SELECT name FROM drugList");
        n.setFirstResult(from);
        n.setMaxResults(limit);
        Query t = entityManager.createNativeQuery("SELECT type FROM drugList");
        t.setFirstResult(from);
        t.setMaxResults(limit);
        List<String> names = castListUnchecked(n);
        List<String> types = castListUnchecked(t);
        Iterator<String> iterNames = names.iterator();
        Iterator<String> iterTypes = types.iterator();
        while (iterNames.hasNext() && iterTypes.hasNext()) {
            r.add(new ActiveIngredientFilterColumn(iterNames.next(), DrugColumnType.valueOf(iterTypes.next())));
        }
        return r;
    }

    public List<DrugVersion> getDrugVersions(ActiveIngredientQueryFilter filter) {
        List<ActiveIngredient> drugs = this.getDrugs(filter);
        List<DrugVersion> dv = new LinkedList<DrugVersion>();
        for (ActiveIngredient drug : drugs) {
        }
        return dv;
    }

    public void reviewAll() {
        logger.info("Set all drugs to reviewed");
        int x = this.entityManager.createQuery("UPDATE ActiveIngredientRevision d " + "SET d.reviewed=:reviewed " + "WHERE d.reviewed IS NULL").setParameter("reviewed", new Date(), TemporalType.DATE).executeUpdate();
        logger.info("Affected rows " + x);
    }
}
