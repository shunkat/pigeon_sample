import 'package:pigeon/pigeon.dart';

// Pigeonの設定を行う: 例どのHostと通信するか、どこのファイルを生成するかなど
@ConfigurePigeon(PigeonOptions(
  dartOut: 'lib/messages.g.dart',
  dartOptions: DartOptions(),
  kotlinOut:
      'android/app/src/main/kotlin/dev/flutter/pigeon_example_app/Messages.g.kt',
  kotlinOptions: KotlinOptions(),
  swiftOut: 'ios/Runner/Messages.g.swift',
  swiftOptions: SwiftOptions(),
))


// Flutter側からHost側を呼び出すときは、@HostApi()をつける
@HostApi()
abstract class BatteryHostApi {
  int getBatteryLevel();
}