//
//  AppDelegate.swift
//  iosApp
//
//  Created by 柴田 剛 on 2021/10/13.
//  Copyright © 2021 orgName. All rights reserved.
//

import UIKit
import shared

class AppDelegate: UIResponder, UIApplicationDelegate {
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        KoinKt.doInitKoinIos()
        return true
    }
}
