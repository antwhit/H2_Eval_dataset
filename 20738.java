import java.awt.*;
import java.io.*;
import InstallThreadCallback;

interface WizardFrame {

    public CardLayout getCardLayout();

    public Frame getFrame();

    public Panel getCards();

    public Config getConfig();

    public ServerPanel getServerPanel();

    public SettingsPanel getSettingsPanel();

    public InstallPanel getInstallPanel();

    public ConfigSetManager getConfigSetManager();

    public void InstallFinished();

    public void StartInstall();

    public boolean isChild();
}
