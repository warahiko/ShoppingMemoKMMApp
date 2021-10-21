//
//  ShoppingItemListView.swift
//  iosApp
//
//  Created by 柴田 剛 on 2021/10/20.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import SwiftUIPager
import shared

struct ShoppingItemListScreen: View {
    @ObservedObject private(set) var viewModel: ViewModel

    @ObservedObject private(set) var page: Page = .first()

    var body: some View {
        ShoppingItemListContentView(
            shoppingItems: viewModel.shoppingItems,
            page: page
        )
    }
}

private struct ShoppingItemListContentView: View {
    let shoppingItems: [ShoppingItem]
    let page: Page

    var body: some View {
        Pager(page: page, data: ShoppingItemListTab.allCases) { tab in
            switch tab {
            case .main:
                MainShoppingItemList(shoppingItems: shoppingItems)
            case .archived:
                ArchivedShoppingItemList()
            case .deleted:
                DeletedShoppingItemList()
            }
        }
        .loopPages()
    }
}
