//
//  ListExtension.swift
//  iosApp
//
//  Created by 柴田 剛 on 2021/10/23.
//  Copyright © 2021 orgName. All rights reserved.
//

extension Optional {
    func orEmpty<T>() -> [T] where Wrapped == [T] {
        if let unwrapped = self {
            return unwrapped
        } else {
            return []
        }
    }
}
