//
//  ListExtension.swift
//  iosApp
//
//  Created by 柴田 剛 on 2021/10/23.
//  Copyright © 2021 orgName. All rights reserved.
//

extension Optional where Wrapped: Collection {
    func orEmpty() -> Wrapped {
        if self == nil {
            return Array<Wrapped.Element>() as! Wrapped
        } else {
            return self!
        }
    }
}
