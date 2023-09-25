package owens.id.bell;

import java.awt.Toolkit;

/**
 * A {@link BellStrategy} which calls {@link Toolkit#beep()}.
 * @author Graham Edgecombe
 */
public class BeepBellStrategy implements BellStrategy {

	@Override
	public void soundBell() {
		Toolkit.getDefaultToolkit().beep();
	}

}