/*
 *	Copyright 2019-2020 Steve White, Retail Media Concepts LLC.
 *
 *	Licensed under the Apache License, Version 2.0 (the "License"); you may not
 *	use this file except in compliance with the License. You may obtain a copy
 *	of the License at:
 *
 *		http://www.apache.org/licenses/LICENSE-2.0
 *
 *	Unless required by applicable law or agreed to in writing, software
 *	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *	License for the specific language governing permissions and limitations
 *	under the License.
 *
 *
 */
def getDriverVersion() {[platform: "Universal", major: 1, minor: 0, build: 0]}

metadata
{
	definition(name: "HubConnect Ring Range Extender", namespace: "shackrat", author: "Steve White")
	{
        capability "Sensor"
        capability "Battery"
        capability "Configuration"
        capability "Refresh"
        capability "Health Check"
        capability "PowerSource"

        attribute "acStatus", "string"
        attribute "batteryStatus", "string"
        attribute "version", "string"

        command "sync"
    
	}
}

///
// Platform Commands
//
def installed()
{
	initialize()
}

def updated()
{
	initialize()
}

def initialize()
{
	refresh()
}

def uninstalled()
{
	parent?.sendDeviceEvent(device.deviceNetworkId, "uninstalled")
}

def parse(String description)
{
	log.trace "Msg: Description is $description"
}

def refresh()
{
	// The server will update status
	parent.sendDeviceEvent(device.deviceNetworkId, "refresh")
}

def sync()
{
	// The server will respond with updated status and details
	parent.syncDevice(device.deviceNetworkId, "ringrangeextender")
	sendEvent([name: "version", value: "v${driverVersion.major}.${driverVersion.minor}.${driverVersion.build}"])
}
