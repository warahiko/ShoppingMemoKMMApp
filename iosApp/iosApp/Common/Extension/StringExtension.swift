//
//  StringExtension.swift
//  iosApp
//
//  Created by 柴田 剛 on 2021/10/23.
//  Copyright © 2021 orgName. All rights reserved.
//

extension Optional where Wrapped == String {
    func orEmpty() -> String {
        if let unwrapped = self {
            return unwrapped
        } else {
            return ""
        }
    }
}
