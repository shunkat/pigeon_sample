import Foundation

enum BatteryError: Error {
    case unavailable
}

class BatteryLevelApi: BatteryHostApi {
    func getBatteryLevel() throws -> Int64 {
        let device = UIDevice.current
        device.isBatteryMonitoringEnabled = true
        if device.batteryState == UIDevice.BatteryState.unknown {
            throw BatteryError.unavailable
        } else {
            return Int64(device.batteryLevel * 100)
        }
    }
}
