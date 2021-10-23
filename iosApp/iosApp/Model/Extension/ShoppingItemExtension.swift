//
//  ShoppingItemExtension.swift
//  iosApp
//
//  Created by 柴田 剛 on 2021/10/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import shared

// List に渡すアイテムはIdentifiable が必要
extension ShoppingItem : Identifiable {}

extension ShoppingItemEditable {
    func copy(
        id: UuidUuid? = nil,
        name: String? = nil,
        count: String? = nil,
        status: Status? = nil,
        doneDate: Kotlinx_datetimeLocalDate? = nil,
        memo: String? = nil,
        tag: Tag? = nil
    ) -> ShoppingItemEditable {
        return self.doCopy(
            id: id ?? self.id,
            name: name ?? self.name,
            count: count ?? self.count,
            status: status ?? self.status,
            doneDate: doneDate ?? self.doneDate,
            memo: memo ?? self.memo,
            tag: tag ?? self.tag
        )
    }
}
