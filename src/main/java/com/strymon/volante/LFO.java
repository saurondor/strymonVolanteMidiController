/**
 * 
 */
package com.strymon.volante;

/**
 * @author gtasi
 *
 */
public class LFO {

	int minValue = 0;
	int maxValue = 127;
	int amplitude = 30;
	int zeroValue = 64;
	Double frequency;
	Double period;

	public int getAmplitude() {
		return amplitude;
	}

	public void setAmplitude(int amplitude) {
		this.amplitude = amplitude;
	}

	public int getZeroValue() {
		return zeroValue;
	}

	public void setZeroValue(int zeroValue) {
		this.zeroValue = zeroValue;
	}

	public Double getFrequency() {
		return frequency;
	}

	public void setFrequency(Double frequency) {
		this.frequency = frequency;
		setPeriod(1000d / frequency);
	}

	public Double getPeriod() {
		return period;
	}

	public void setPeriod(Double period) {
		this.period = period;
	}

	public int getValue(long milliseconds) {
		Double time = Double.valueOf(milliseconds);
		Double cycle = (time % period) / period;
		Double angle = Math.PI * 2 * cycle;
		Double sine = Math.sin(angle);
		int signal = Long.valueOf(Math.round(sine * amplitude)).intValue();
		signal = zeroValue + signal;
		// handle clipping
		if (signal < minValue) {
			return 0;
		} else if (signal > maxValue) {
			return maxValue;
		}
		return signal;
	}
}
