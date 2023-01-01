public class TestProjectLoad {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("need to specify repositoryPath");
            System.out.println("with ant use -DrepositoryPath=...");
            System.out.println("with java just specify argument on command line");
            System.exit(1);
        }
        String repositoryPath = args[0];
        try {
            String projectName = memops.universal.Io.basename(repositoryPath);
            memops.api.Implementation.MemopsRoot project = memops.format.xml.Io.loadProject(repositoryPath, projectName);
            System.out.println("project name = " + project.getName());
            for (memops.api.Implementation.Repository repository : project.getRepositories()) {
                System.out.println("repository = " + repository);
                System.out.println("url = " + repository.getUrl());
            }
        } catch (memops.general.ApiException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
