import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import sun.audio.*;
import java.io.*;
import javax.sound.sampled.*;

public class Controls extends UIInterface {

    private static Sprite controlsBackground;

    private static ArrayList<BackgroundLayer> sfxLayers;

    public static UIButton menuButton;

    public static Clip menuSound;

    private static UIListener buttonListener;

    /**
	 * Initialize main menu
	 */
    public static void init() {
        UIInterface.init();
        buttonListener = new ControlsButtonListener();
        sfxLayers = new ArrayList<BackgroundLayer>();
        controlsBackground = new Sprite(0, 0);
        controlsBackground.loadImage("Control", Sprite.ANIM_TYPE_MISC, "Backgrounds/controls.png");
        controlsBackground.runAnimation("Control");
        BackgroundLayer fog1 = new BackgroundLayer(1, BackgroundLayer.DIRECTION_LEFT, Config.BOARD_HEIGHT - 120 - 451);
        fog1.addSingleBackgroundPanel("main_menu_fog_effect.png");
        sfxLayers.add(fog1);
        BackgroundLayer fog2 = new BackgroundLayer(2, BackgroundLayer.DIRECTION_RIGHT, Config.BOARD_HEIGHT - 120 - 500);
        fog2.addSingleBackgroundPanel("main_menu_fog_effect.png");
        sfxLayers.add(fog2);
        BackgroundLayer fog3 = new BackgroundLayer(2, BackgroundLayer.DIRECTION_RIGHT, Config.BOARD_HEIGHT - 120 - 470);
        fog3.addSingleBackgroundPanel("main_menu_fog_effect_dark.png");
        sfxLayers.add(fog3);
        Color transparent = new Color(0, 0, 0, 0);
        menuButton = new UIButton("MENU", 800, 10, transparent, transparent, transparent);
        menuButton.setFont(new Font("Agency FB", Font.PLAIN, 36));
        menuButton.setTextColorHovered(Color.RED);
        menuButton.setUIListener(new ControlsButtonListener());
        addButton(menuButton);
        transparent = null;
    }

    /**
	 * Paint the Controls screen.
	 */
    public static void paint(Graphics g, Component c) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        controlsBackground.draw(g2d, c);
        for (int i = 0; i < getButtons().size(); i++) {
            UIButton btn = getButtons().get(i);
            btn.draw(g2d);
        }
        for (int i = 0; i < sfxLayers.size(); i++) {
            BackgroundLayer layer = sfxLayers.get(i);
            layer.drawBackground(g2d, c);
        }
    }

    /**
	 * Garbage collection.
	 */
    public static void destroy() {
        controlsBackground = null;
        if (sfxLayers != null) {
            sfxLayers.clear();
        }
        sfxLayers = null;
        menuButton = null;
        menuSound = null;
    }
}

class ControlsButtonListener implements UIListener {

    public void actionPerformed(UIButton btn, int action) {
        if (action == UIButton.ACTION_MOUSEOVER) {
            if (Controls.menuSound == null) {
                Controls.menuSound = Audio.loadSound("menu_button_sound.wav");
            }
            if (Controls.menuSound.isRunning() == false) {
                Audio.playSound(Controls.menuSound);
            }
        }
        if (action == UIButton.ACTION_PRESSED) {
            if (btn == Controls.menuButton) {
                Controls.destroy();
                MainMenu.init();
                FighterGame.board.setGameState(Board.STATE_MENU);
            }
        }
    }
}
