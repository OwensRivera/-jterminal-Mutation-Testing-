package owens.id;

import java.awt.Color;

/**
 * Represents a single terminal cell which contains a character, background
 * color and foreground color.
 * @author Graham Edgecombe
 */
public class TerminalCell {

	/**
	 * The character.
	 */
	private final char character;

	/**
	 * The background color.
	 */
	private final Color backgroundColor;

	/**
	 * The foreground color.
	 */
	private final Color foregroundColor;

	/**
	 * Creates a terminal cell with the specified character, background color
	 * and foreground color.
	 * @param character The character.
	 * @param backgroundColor The background color.
	 * @param foregroundColor The foreground color.
	 * @throws NullPointerException if the background or foreground color(s)
	 * are {@code null}.
	 */
	public TerminalCell(char character, Color backgroundColor, Color foregroundColor) {
		if (backgroundColor == null) {
			throw new NullPointerException("backgroundColor");
		}
		if (foregroundColor == null) {
			throw new NullPointerException("foregroundColor");
		}

		this.character = character;
		this.backgroundColor = backgroundColor;
		this.foregroundColor = foregroundColor;
	}

	/**
	 * Gets the character.
	 * @return The character.
	 */
	public char getCharacter() {
		return character;
	}

	/**
	 * Gets the background color.
	 * @return The background color.
	 */
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * Gets the foreground color.
	 * @return The foreground color.
	 */
	public Color getForegroundColor() {
		return foregroundColor;
	}

}