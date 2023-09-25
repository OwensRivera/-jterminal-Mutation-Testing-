package owens.id.vt100;

class AnsiControlSequence {

	/**
	 * The command character.
	 */
	private final char command;

	/**
	 * The parameters.
	 */
	private final String[] parameters;

	/**
	 * Creates an ANSI control sequence with the specified command and
	 * parameters.
	 * @param command The command character.
	 * @param parameters The parameters array.
	 * @throws NullPointerException if the parameters array is {@code null}.
	 */
	public AnsiControlSequence(char command, String[] parameters) {
		if (parameters == null) {
			throw new NullPointerException("parameters");
		}
		this.command = command;
		if (parameters.length == 1 && parameters[0].equals("")) {
			this.parameters = new String[0];
		} else {
			this.parameters = parameters.clone();
		}
	}

	/**
	 * Gets the command character.
	 * @return The command character.
	 */
	public char getCommand() {
		return command;
	}

	/**
	 * Gets the parameters array.
	 * @return The parameters array.
	 */
	public String[] getParameters() {
		return parameters;
	}

}