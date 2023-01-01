import org.lwjgl.opengl.GL11;

public class Pyramid extends Model3D {

    public void render3D() {
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glPushMatrix();
        GL11.glTranslatef(m_nX, m_nY, m_nZ);
        GL11.glTranslatef(0, 0, -3.5f);
        if (m_rZ != 0) GL11.glRotatef(m_rZ, 0, 0, 1);
        if (m_rY != 0) GL11.glRotatef(m_rY, 0, 1, 0);
        if (m_rX != 0) GL11.glRotatef(m_rX, 1, 0, 0);
        if (m_sX != 1 || m_sY != 1 || m_sZ != 1) GL11.glScalef(m_sX, m_sY, m_sZ);
        GL11.glTranslatef(0, 0, 3.5f);
        renderModel();
        GL11.glPopMatrix();
    }

    protected void calcNorm() {
    }

    private void renderModel() {
        GL11.glBegin(GL11.GL_TRIANGLES);
        GL11.glColor3f(0, 0, 1);
        GL11.glVertex3f(-1.0f, -0.5f, -4.0f);
        GL11.glVertex3f(0.0f, 0.5f, -4.0f);
        GL11.glVertex3f(1.0f, -0.5f, -4.0f);
        GL11.glColor3f(1, 0, 0);
        GL11.glVertex3f(-1.0f, -0.5f, -4.0f);
        GL11.glColor3f(0, 1, 0);
        GL11.glVertex3f(1.0f, -0.5f, -4.0f);
        GL11.glColor3f(0, 0, 1);
        GL11.glVertex3f(0.0f, -0.5f, -3.0f);
        GL11.glColor3f(1, 1, 0);
        GL11.glVertex3f(-1.0f, -0.5f, -4.0f);
        GL11.glVertex3f(0.0f, -0.5f, -3.0f);
        GL11.glVertex3f(0.0f, 0.5f, -4.0f);
        GL11.glColor3f(0, 1, 1);
        GL11.glVertex3f(1.0f, -0.5f, -4.0f);
        GL11.glVertex3f(0.0f, 0.5f, -4.0f);
        GL11.glVertex3f(0.0f, -0.5f, -3.0f);
        GL11.glEnd();
    }
}
