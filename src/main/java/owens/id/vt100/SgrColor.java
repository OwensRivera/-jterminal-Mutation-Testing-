package owens.id.vt100;

import java.awt.Color;

/**
 * Contains colors used by the SGR ANSI escape sequence.
 * @author Graham Edgecombe
 */
final class SgrColor {

	/**
	 * An array of normal intensity colors.
	 */
	public static final Color[] COLOR_NORMAL = new Color[] {
		new Color(0, 0, 0),
		new Color(128, 0, 0),
		new Color(0, 128, 0),
		new Color(128, 128, 0),
		new Color(0, 0, 128),
		new Color(128, 0, 128),
		new Color(0, 128, 128),
		new Color(192, 192, 192)
	};

	/**
	 * An array of bright intensity colors.
	 */
	public static final Color[] COLOR_BRIGHT = new Color[] {
		new Color(128, 128, 128),
		new Color(255, 0, 0),
		new Color(0, 255, 0),
		new Color(255, 255, 0),
		new Color(0, 0, 255),
		new Color(0, 0, 255),
		new Color(255, 0, 255),
		new Color(0, 255, 255),
		new Color(255, 255, 255)
	};

	/**
	 * Default private constructor to prevent instantiation.
	 */
	private SgrColor() {

	}

}