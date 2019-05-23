import UIKit
import Flutter

@UIApplicationMain
@objc class AppDelegate: FlutterAppDelegate {
  override func application(
    _ application: UIApplication,
    didFinishLaunchingWithOptions launchOptions: [UIApplicationLaunchOptionsKey: Any]?
  ) -> Bool {
    GeneratedPluginRegistrant.register(with: self)
    
    let pluginRegistrar = registrar(forPlugin: "tv.mta.jwplayer/JWPlayerPlugin")
    
    let jwPlayerViewFactory = PlayerViewFactory(messenger: pluginRegistrar.messenger())
    
    /* register JWPlayer view */
    pluginRegistrar.register(jwPlayerViewFactory, withId: "tv.mta.jwplayer/JWPlayerView")
    
    /* setup audio session */
    let audioSession = AVAudioSession.sharedInstance()
    do {
        try audioSession.setCategory(AVAudioSessionCategoryPlayback, with: [])
        try audioSession.setActive(true)
    } catch {
        print("Setting category to AVAudioSessionCategoryPlayback failed.")
    }
    
    return super.application(application, didFinishLaunchingWithOptions: launchOptions)
  }
}
