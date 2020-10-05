/*
 * Virtual Sleep Sensor with Switch
 */

metadata {
    definition (name: "Virtual Sleep Sensor with Switch", namespace: "bfara83", author: "bfara83") {
        capability "Sensor"
        capability "SleepSensor"
        capability "Switch"
		
        command "asleep"
        command "awake"
		
    }   
}

def asleep() {
	on()
}

def awake() {
	off()
}

def on() {
    sendEvent(name: "sleeping", value: "sleeping")
    sendEvent(name: "switch", value: "on")
}

def off() {
    sendEvent(name: "sleeping", value: "not sleeping")
    sendEvent(name: "switch", value: "off")
}

def installed() {
}
