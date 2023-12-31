package owens.id;
import owens.id.vt100;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import owens.id.vt100.AnsiControlSequence;
import owens.id.vt100.AnsiControlSequenceListener;
import owens.id.vt100.AnsiControlSequenceParser;

public class TestAnsiControlSequenceParser implements AnsiControlSequenceListener 
{

	/**
	 * The current parser.
	 */
	private AnsiControlSequenceParser parser;

	/**
	 * The list of objects returned through the
	 * {@link AnsiControlSequenceListener} interface.
	 */
	private List<Object> objects = new ArrayList<Object>();

	/**
	 * Sets up the parser and object list.
	 */
	@Before
	public void setUp() {
		objects.clear();
		parser = new AnsiControlSequenceParser(this);
	}

	/**
	 * Tests a broken sequence with the single byte CSI.
	 */
	@Test
	public void testBrokenSingleSequence() {
		parser.parse(new String(new char[] { 155 }));
		parser.parse(new String(new char[] { 'u' }));

		assertEquals(1, objects.size());

		Object obj = objects.get(0);
		assertEquals(AnsiControlSequence.class, obj.getClass());

		AnsiControlSequence seq = (AnsiControlSequence) obj;
		String[] params = seq.getParameters();

		assertEquals('u', seq.getCommand());
		assertEquals(0, params.length);
	}

	/**
	 * Tests a broken sequence with the double byte CSI.
	 */
	@Test
	public void testBrokenDoubleSequence() {
		char[] ch1 = { 27 };
		char[] ch2 = { '[' };
		char[] ch3 = { '3', '0', ';' };
		char[] ch4 = { '4', '0', 'm' };

		parser.parse(new String(ch1));
		parser.parse(new String(ch2));
		parser.parse(new String(ch3));
		parser.parse(new String(ch4));

		assertEquals(1, objects.size());
		Object obj = objects.get(0);
		assertEquals(AnsiControlSequence.class, obj.getClass());

		AnsiControlSequence seq = (AnsiControlSequence) obj;
		String[] params = seq.getParameters();

		assertEquals('m', seq.getCommand());
		assertEquals(2, params.length);
		assertEquals("30", params[0]);
		assertEquals("40", params[1]);
	}

	/**
	 * Tests an empty string.
	 */
	@Test
	public void testEmpty() {
		parser.parse("");
		assertEquals(0, objects.size());
	}

	/**
	 * Tests a sequence embedded within some text.
	 */
	@Test
	public void testTextAndSequence() {
		char[] ch = { 'h', 'i', 155, 'u', 'b', 'y', 'e' };
		parser.parse(new String(ch));

		assertEquals(3, objects.size());

		Object o1 = objects.get(0);
		Object o2 = objects.get(1);
		Object o3 = objects.get(2);

		assertEquals(String.class, o1.getClass());
		assertEquals(AnsiControlSequence.class, o2.getClass());
		assertEquals(String.class, o3.getClass());

		assertEquals("hi", o1);
		assertEquals("bye", o3);

		AnsiControlSequence seq = (AnsiControlSequence) o2;
		String[] params = seq.getParameters();

		assertEquals(0, params.length);
		assertEquals('u', seq.getCommand());
	}

	/**
	 * Tests parameters within a sequence.
	 */
	@Test
	public void testParameters() {
		char[] ch = { 155, '3', '0', ';', '4', '0', 'm' };
		parser.parse(new String(ch));

		assertEquals(1, objects.size());
		Object obj = objects.get(0);
		assertEquals(AnsiControlSequence.class, obj.getClass());

		AnsiControlSequence seq = (AnsiControlSequence) obj;
		String[] params = seq.getParameters();

		assertEquals('m', seq.getCommand());
		assertEquals(2, params.length);
		assertEquals("30", params[0]);
		assertEquals("40", params[1]);
	}

	/**
	 * Tests with plain text.
	 */
	@Test
	public void testText() {
		parser.parse("Hello, World!");

		assertEquals(1, objects.size());
		Object obj = objects.get(0);
		assertEquals(String.class, obj.getClass());
		assertEquals(obj, "Hello, World!");
	}

	/**
	 * Tests with a single byte CSI.
	 */
	@Test
	public void testSingleCsi() {
		char[] ch = { 155, '6', 'n' };
		parser.parse(new String(ch));

		assertEquals(1, objects.size());
		Object obj = objects.get(0);
		assertEquals(AnsiControlSequence.class, obj.getClass());

		AnsiControlSequence seq = (AnsiControlSequence) obj;
		String[] params = seq.getParameters();
		assertEquals('n', seq.getCommand());
		assertEquals(1, params.length);
		assertEquals("6", params[0]);
	}

	/**
	 * Tests with a double byte CSI.
	 */
	@Test
	public void testDoubleCsi() {
		char[] ch = { 27, '[', 's' };
		parser.parse(new String(ch));

		assertEquals(1, objects.size());
		Object obj = objects.get(0);
		assertEquals(AnsiControlSequence.class, obj.getClass());

		AnsiControlSequence seq = (AnsiControlSequence) obj;
		String[] params = seq.getParameters();
		assertEquals('s', seq.getCommand());
		assertEquals(0, params.length);
	}

	@Override
	public void parsedControlSequence(AnsiControlSequence seq) {
		objects.add(seq);
	}

	@Override
	public void parsedString(String str) {
		objects.add(str);
	}

}