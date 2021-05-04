/**
 * 
 */
package com.strymon.volante;

import java.util.Date;
import java.util.Vector;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.MidiDevice.Info;

/**
 * @author gtasi
 *
 */
public class Volante implements Runnable {

	public static final int CC_BANK_SELECT = 0;// 0-2
	public static final int CC_TYPE = 11;// 1-3 (1=studio, 2=drum, 3=tape)
	public static final int CC_ECHO_LEVEL = 12; // 0-127
	public static final int CC_REC_LEVEL = 13; // 0-127
	public static final int CC_MECHANICS = 14; // 0-127
	public static final int CC_WEAR = 15; // 0-127
	public static final int CC_LOW_CUT = 16; // 0-127
	public static final int CC_TIME = 17; // 0-127
	public static final int CC_SPACING = 18; // 0-127 (EVEN=0, TRIPLET=34, GOLDEN=94, SILVER=127)
	public static final int CC_SPEED = 19; // 1-3 (1=DOUBLE, 2=HALF, 3=NORMAL)
	public static final int CC_REPEATS = 20; // 0-127
	public static final int CC_HEAD_1_PLAYBACK = 21; // 0, 127 (0=OFF, 1-127=ON)
	public static final int CC_HEAD_2_PLAYBACK = 22; // 0, 127 (0=OFF, 1-127=ON)
	public static final int CC_HEAD_3_PLAYBACK = 23; // 0, 127 (0=OFF, 1-127=ON)
	public static final int CC_HEAD_4_PLAYBACK = 24; // 0, 127 (0=OFF, 1-127=ON)
	public static final int CC_HEAD_1_LEVEL = 25; // 0-127
	public static final int CC_HEAD_2_LEVEL = 26; // 0-127
	public static final int CC_HEAD_3_LEVEL = 27; // 0-127
	public static final int CC_HEAD_4_LEVEL = 28; // 0-127
	public static final int CC_HEAD_1_PAN = 29; // 0-127
	public static final int CC_HEAD_2_PAN = 30; // 0-127
	public static final int CC_HEAD_3_PAN = 31; // 0-127
	public static final int CC_HEAD_4_PAN = 32; // 0-127
	public static final int CC_HEAD_1_FEEDBACK = 34; // 0, 127 (0=OFF, 1-127=ON)
	public static final int CC_HEAD_2_FEEDBACK = 35; // 0, 127 (0=OFF, 1-127=ON)
	public static final int CC_HEAD_3_FEEDBACK = 36; // 0, 127 (0=OFF, 1-127=ON)
	public static final int CC_HEAD_4_FEEDBACK = 37; // 0, 127 (0=OFF, 1-127=ON)
	public static final int CC_PAUSE_RAMP_SPEED = 38; // 0-127
	public static final int CC_SPRING_LEVEL = 39; // 0-127
	public static final int CC_SPRING_DECAY = 40; // 0-127

	public static final int CC_SOS_MODE = 41; // 0, 127 (0=NORMAL, 1-127=SOS)
	public static final int CC_PAUSE_NO_RAMP = 42; // 0, 127 (0=UNPAUSE, 1-127=PAUSE)
	public static final int CC_PAUSE_RAMP = 43; // 0, 127 (0=UNPAUSE, 1-127=PAUSE)
	public static final int CC_REVERSE = 44; // 0, 127 (0=NORMAL, 1-127=REVERSE)
	public static final int CC_INFINITE_HOLD_W_OSCILLATION = 45; // 0, 127 (0=RELEASE, 1-127=HOLD)
	public static final int CC_INFINITE_HOLD_WO_OSCILLATION = 46; // 0-127 (0=RELEASE, 1-127=HOLD)
	public static final int CC_SOS_LOOP_LEVEL = 47; // 0-127
	public static final int CC_SOS_REPEATS_LEVEL = 48; // 0-127

	public static final int CC_MIDI_EXPRESSION = 60; // 0, 127 (0=OFF, 1-127=ON)
	public static final int CC_MIDI_CLOCK = 63; // 0, 127 (0=OFF, 1-127=ON)
	public static final int CC_ECHO_ON_OFF = 78; // 0, 127 (0=OFF, 127=ON)
	public static final int CC_REVERB_ON_OFF = 79; // 0, 127 (0=OFF, 127=ON)
	public static final int CC_FOOTSWITCH_ON = 80; // 0, 127 (0=RELEASE, 1-127=PRESS)
	public static final int CC_FOOTSWITCH_FAVORITE = 81; // 0, 127 (0=RELEASE, 1-127=PRESS)
	public static final int CC_FOOTSWITCH_TAP = 82; // 0, 127 (0=RELEASE, 1-127=PRESS)
	public static final int CC_PERSIST = 83; // 0, 127 (0=PERSIST OFF, 1-127=PERSIST ON)
	public static final int CC_KILL_DRY = 84; // 0, 127 (0=DRY OFF, 1-127=DRY ON)
	public static final int CC_OUTPUT_SUM = 85; // 0, 127 (0=STEREO, 1-127=SUM)
	public static final int CC_REMOTE_TAP = 93; // ANY
	public static final int CC_EXPRESSION_PEDAL = 100; // 0-127
	public static final int CC_BYPASS_ON = 102; // 0, 127 (0=BYPASS, 1-127=ON

	MidiDevice device;
	boolean runMe = true;
	LFO lfo;
	Receiver rcvr;
	boolean connected = false;

	public void stop() {
		runMe = false;
	}

	public void setLFO(Double frequency, int amplitude, int zeroValue) {
		lfo = new LFO();
		lfo.setAmplitude(amplitude);
		lfo.setFrequency(frequency);
		lfo.setZeroValue(zeroValue);
	}

	public void openMidi(MidiDevice device) {
		this.device = device;
		if (!(this.device.isOpen())) {
			try {
				this.device.open();
				this.rcvr = device.getReceiver();
			} catch (MidiUnavailableException e) {
				// Handle or throw exception...
				e.printStackTrace();
			}
		}
	}

	public void connect() {
		// Obtain information about all the installed synthesizers.
		MidiDevice device = null;
		MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
		for (int i = 0; i < infos.length; i++) {
			try {
				device = MidiSystem.getMidiDevice(infos[i]);
				if (device.getDeviceInfo().getName().equals("Volante")
						&& !(device.getDeviceInfo().getDescription().equals("No details available"))) {
					System.out.println("Opening device " + device.getClass() + " - '" + device.getDeviceInfo() + "',"
							+ device.getDeviceInfo().getDescription() + "," + device.getDeviceInfo().getClass());
					openMidi(device);
					connected = true;
				}
			} catch (MidiUnavailableException e) {
				e.printStackTrace();
			}
		}
	}

	public void run() {

		long startTime = (new Date()).getTime();
		do {

			ShortMessage speedMsg = new ShortMessage();
			try {
				long timeStamp = -1;
				int amplitude = lfo.getValue((new Date()).getTime() - startTime);
//				speedMsg.setMessage(ShortMessage.CONTROL_CHANGE, 0, CC_TIME, amplitude);
//				System.out.println("Sending message " + amplitude);
//				rcvr.send(speedMsg, timeStamp);
				amplitude = lfo.getValue((new Date()).getTime() - startTime);
				speedMsg.setMessage(ShortMessage.CONTROL_CHANGE, 0, CC_REC_LEVEL, amplitude);
				System.out.println("Sending message HEAD 3: " + amplitude);
				rcvr.send(speedMsg, timeStamp);
//				amplitude = lfo.getValue((new Date()).getTime() - startTime - 1800);
//				speedMsg.setMessage(ShortMessage.CONTROL_CHANGE, 0, CC_HEAD_2_LEVEL, amplitude);
//				System.out.println("Sending message HEAD 2: " + amplitude);
//				rcvr.send(speedMsg, timeStamp);
//				amplitude = lfo.getValue((new Date()).getTime() - startTime + 1800);
//				speedMsg.setMessage(ShortMessage.CONTROL_CHANGE, 0, CC_HEAD_4_LEVEL, amplitude);
//				System.out.println("Sending message HEAD 4: " + amplitude);
//				rcvr.send(speedMsg, timeStamp);
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidMidiDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (runMe);
	}

	public static void main(String[] args) {
		Volante volante = new Volante();
		volante.setLFO(3.24d, 40, 44);
		volante.connect();
		Thread thread = new Thread(volante);
		thread.start();
	}

	public void sendMsg(ShortMessage msg) {
		long timeStamp = -1;
		if (connected) {
			rcvr.send(msg, timeStamp);
		}
	}
}
