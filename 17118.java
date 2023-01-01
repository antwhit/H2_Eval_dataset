import org.agilercp.ui.IPresenter;
import org.agilercp.ui.IView;
import org.springframework.context.ApplicationContext;

/**
 * @author Benedikt Arnold
 */
public class MockSpringUtilFacadeWithoutApplicationContext extends MockSpringUtilFacade {

    /**
     * @see MockSpringUtilFacade#createApplicationContext(org.agilercp.ui.workbench.IWorkbenchPartPresenter)
     */
    @Override
    protected ApplicationContext createApplicationContext(final IPresenter<IView> workbenchPartPresenter) {
        return null;
    }
}
