//
//  Color.swift
//  iosApp
//
//  Created by 柴田 剛 on 2021/10/20.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

enum ShoppingMemoColor {
    case lightGreen
    case green
    case lightRed
    case black
    case white
    case lightGray
    
    var color: Color {
        switch self {
        case .lightGreen:
            return Color(value: 0xFF9CCC65)
        case .green:
            return Color(value: 0xFF8BC34A)
        case .lightRed:
            return Color(value: 0xFFFF8A65)
        case .black:
            return Color(value: 0xFF000000)
        case .white:
            return Color(value: 0xFFFFFFFF)
        case .lightGray:
            return Color(value: 0xFFEEEEEE)
        }
    }
}

extension Color {
    init(value: UInt32) {
        let opacity = (value & 0xFF000000) >> 24
        let red = (value & 0x00FF0000) >> 16
        let green = (value & 0x0000FF00) >> 8
        let blue = value & 0x000000FF
        self.init(
            red: Double(red) / 256,
            green: Double(green) / 256,
            blue: Double(blue) / 256,
            opacity: Double(opacity) / 256
        )
    }
}
