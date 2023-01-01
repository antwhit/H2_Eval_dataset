/**
 * @author Mauricio J. Serrano
 * @author John Whaley
 */
class OPT_SpaceEffGraphEdgeListHeader {

    OPT_SpaceEffGraphEdgeList _first;

    OPT_SpaceEffGraphEdgeList _last;

    OPT_SpaceEffGraphEdgeList first() {
        return _first;
    }

    OPT_SpaceEffGraphEdgeList last() {
        return _last;
    }

    public void append(OPT_SpaceEffGraphEdge edge) {
        OPT_SpaceEffGraphEdgeList p = new OPT_SpaceEffGraphEdgeList();
        p._edge = edge;
        OPT_SpaceEffGraphEdgeList last = _last;
        if (last == null) {
            _first = p;
            _last = p;
        } else {
            last._next = p;
            p._prev = last;
            _last = p;
        }
    }

    public void add(OPT_SpaceEffGraphEdge edge) {
        OPT_SpaceEffGraphEdgeList p = first();
        OPT_SpaceEffGraphEdgeList prev = first();
        if (p == null) {
            p = new OPT_SpaceEffGraphEdgeList();
            p._edge = edge;
            _first = p;
            _last = p;
            return;
        }
        while (p != null) {
            prev = p;
            p = p._next;
        }
        prev._next = new OPT_SpaceEffGraphEdgeList();
        prev._next._edge = edge;
        prev._next._prev = prev;
        _last = prev._next;
        return;
    }
}
