/**
 * 
 */
package com.strymon.volante;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

/**
 * @author gtasi
 *
 */
public class TestLFO {

	@Test
	public void testLFO() {
		LFO lfo = new LFO();

		lfo.setFrequency(1d);
		int value = lfo.getValue(0);
		System.out.println(value);
		value = lfo.getValue(250);
		System.out.println(value);
		value = lfo.getValue(500);
		System.out.println(value);
		value = lfo.getValue(750);
		System.out.println(value);
		fail("Not yet implemented");
	}

	@Test
	public void testLFOcycle() {
		int steps = 0;
		long startTime = (new Date()).getTime();
		LFO lfo = new LFO();
		lfo.setAmplitude(30);
		lfo.setFrequency(0.1d);
		do {
			steps++;
			try {
				Thread.sleep(10);
				System.out.println(lfo.getValue((new Date()).getTime() - startTime));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (steps < 1000);
	}
}
