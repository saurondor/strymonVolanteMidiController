/**
 * 
 */
package com.strymon.volante;

import java.util.Vector;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiDevice.Info;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Transmitter;

/**
 * @author gtasi
 *
 */
public class SimpleMIDI {

	public void listDevices() {
		// Obtain information about all the installed synthesizers.
		Vector<Info> synthInfos = new Vector<MidiDevice.Info>();
		MidiDevice device = null;
		MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
		for (int i = 0; i < infos.length; i++) {
			try {
				device = MidiSystem.getMidiDevice(infos[i]);

				System.out.println("device " + device.getDeviceInfo().getName() + ","
						+ device.getDeviceInfo().getDescription() + " " + device.getTransmitters().size());
//				if ( ) {
//					
//				}
//				if (device instanceof Synthesizer) {
//					synthInfos.add(infos[i]);
//				}
				if (device.getDeviceInfo().getName().equals("Volante")
						&& !(device.getDeviceInfo().getDescription().equals("No details available"))) {
					System.out.println("Opening device " + device.getClass() + " - '" + device.getDeviceInfo() + "',"
							+ device.getDeviceInfo().getDescription() + "," + device.getDeviceInfo().getClass());
					int count = 0;
					if (!(device.isOpen())) {
						try {
							device.open();
							ShortMessage onMsg = new ShortMessage();
							onMsg.setMessage(ShortMessage.CONTROL_CHANGE, 0, 17, 64);
							ShortMessage offMsg = new ShortMessage();
							offMsg.setMessage(ShortMessage.CONTROL_CHANGE, 0, 17, 127);
							ShortMessage speedMsg = new ShortMessage();
							speedMsg.setMessage(ShortMessage.CONTROL_CHANGE, 0, 17, 127);
							long timeStamp = -1;
							int maxSpeed = 127;
							int minSpeed = 60;
							Receiver rcvr = device.getReceiver();
//							Transmitter tsmt = MidiSystem.getTransmitter();
//							tsmt.
//							rcvr = device.getReceiver();
							speedMsg.setMessage(ShortMessage.CONTROL_CHANGE, 0, 84, 0);
							rcvr.send(speedMsg, timeStamp);
							do {

								try {
//									rcvr.send(offMsg, timeStamp);
//									Thread.sleep(500);
									for (int speed = maxSpeed; speed > minSpeed; speed -= 2) {
//										speedMsg.setMessage(ShortMessage.CONTROL_CHANGE, 0, 17, speed);
//										rcvr.send(speedMsg, timeStamp);
										speedMsg.setMessage(ShortMessage.CONTROL_CHANGE, 0, 26, speed);
										rcvr.send(speedMsg, timeStamp);
										Thread.sleep(10);

									}
									speedMsg.setMessage(ShortMessage.CONTROL_CHANGE, 0, 23, 1);
									rcvr.send(speedMsg, timeStamp);
//									Thread.sleep(500);
									for (int speed = minSpeed; speed <= maxSpeed; speed += 2) {
//										speedMsg.setMessage(ShortMessage.CONTROL_CHANGE, 0, 17, speed);
//										rcvr.send(speedMsg, timeStamp);
										speedMsg.setMessage(ShortMessage.CONTROL_CHANGE, 0, 26, speed);
										rcvr.send(speedMsg, timeStamp);
										Thread.sleep(10);
									}
									speedMsg.setMessage(ShortMessage.CONTROL_CHANGE, 0, 26, 127);
									rcvr.send(speedMsg, timeStamp);
									Thread.sleep(300);
									speedMsg.setMessage(ShortMessage.CONTROL_CHANGE, 0, 23, 0);
									rcvr.send(speedMsg, timeStamp);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							} while (count < 1000);
							System.out.println("Sent message @" + rcvr.getClass());
						} catch (MidiUnavailableException e) {
							// Handle or throw exception...
							e.printStackTrace();
						} catch (InvalidMidiDataException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			} catch (MidiUnavailableException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleMIDI simpleMidi = new SimpleMIDI();
		simpleMidi.listDevices();
	}

}
