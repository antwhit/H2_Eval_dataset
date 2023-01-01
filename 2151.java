public class ValidationManagerOAWImpl {

    public void createProject(String name) throws DeftNullArgumentException, DeftProjectAlreadyExistsException {
        if (isNull(name)) throw new DeftNullArgumentException(name);
        if (projectExists(name)) throw new DeftProjectAlreadyExistsException(name);
    }

    public void createChapter(Project project, String name) throws DeftNullArgumentException, DeftInvalidArgumentException, DeftFragmentAlreadyExistsException {
        if (isNull(project)) throw new DeftNullArgumentException(project);
        if (isNull(name)) throw new DeftNullArgumentException(name);
        if (!projectExists(project)) throw new DeftInvalidArgumentException(project);
        if (chapterExists(project, name)) throw new DeftFragmentAlreadyExistsException(project, "chapter", name);
    }
}
