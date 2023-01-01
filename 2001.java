import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

/**
 * A class with some basic rendering methods to draw objects.
 */
public class Render {

    public static void renderTexturedQuad(Vector2f position, Vector2f size, Texture texture) {
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        texture.bind();
        GL11.glTranslatef(position.x, position.y, 0);
        GL11.glColor3f(1, 1, 1);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex2f(0, 0);
        GL11.glTexCoord2f(1, 0);
        GL11.glVertex2f(size.x, 0);
        GL11.glTexCoord2f(1, 1);
        GL11.glVertex2f(size.x, size.y);
        GL11.glTexCoord2f(0, 1);
        GL11.glVertex2f(0, size.y);
        GL11.glEnd();
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glPopMatrix();
    }

    public static void renderBlendedQuad(Vector2f position, Vector2f size, Color color) {
        GL11.glPushMatrix();
        GL11.glTranslatef(position.x, position.y, 0);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_DST_COLOR, GL11.GL_ZERO);
        GL11.glColor3f(color.r, color.g, color.b);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(0, 0);
        GL11.glVertex2f(size.x, 0);
        GL11.glVertex2f(size.x, size.y);
        GL11.glVertex2f(0, size.y);
        GL11.glEnd();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }
}
