package owens.id.vt100;

interface AnsiControlSequenceListener {

	/**
	 * Called when a control sequence has been parsed.
	 * @param seq The control sequence.
	 */
	public void parsedControlSequence(AnsiControlSequence seq);

	/**
	 * Called when a string has been parsed.
	 * @param str The string.
	 */
	public void parsedString(String str);

}