package DevTSK.Units;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;

public class Viewer extends JPanel {
	private static final int w = 16, h = 16;

	private Image tileset;

	private static final long serialVersionUID = 1L;

	private Map map;

	public Viewer(Map map) {
		this.map = map;
		tileset = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("assets/tiles/tileset.png"));
	}

	@Override
    protected void paintComponent(Graphics g) {
        g.setColor(new Color(0,0,0));
        g.fillRect(0, 0, getWidth(), getHeight());
 
        for(int i=0; i<map.getTiles().length;i ++)
            for(int j=0;j&lt;10;j++)
                drawTile(g, map[j][i], i*tW,j*tH);
    }

	protected void drawTile(Graphics g, TileName t, int x, int y) {
		// map Tile from the tileset
		int mx = t.ordinal() % 10;
		int my = t.ordinal() / 10;
		g.drawImage(tileset, x, y, x + tW, y + tH,
				mx * tW, my * tH, mx * tW + tW, my * tH + tH, this);
	}
}
