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

    @StateObject private var page: Page = .first()

    var body: some View {
        NavigationView {
            ShoppingItemListContentView(
                uiModel: $viewModel.uiModel,
                page: page
            )
                .navigationTitle("K買い物メモ[DEBUG]")
                .navigationBarTitleDisplayMode(NavigationBarItem.TitleDisplayMode.inline)
                .toolbar {
                    ToolbarItem(placement: .navigationBarTrailing) {
                        NavigationLink(destination: AddShoppingItemScreen(viewModel: .init())) {
                            Text("Add")
                        }
                    }
                }
        }
    }
}

private struct ShoppingItemListContentView: View {
    @Binding var uiModel: ShoppingItemListScreen.UiModel
    @ObservedObject var page: Page

    var body: some View {
        VStack {
            HStack {
                ForEach(Array(ShoppingItemListTab.allCases.enumerated()), id: \.element.id) { (index, tab) in
                    Tab(parent: self, isSelected: page.index == index, tab: tab)
                        // こうするとなぜか等分になる
                        .frame(minWidth: 0, maxWidth: .infinity)
                        .onTapGesture {
                            page.update(Page.Update.new(index: index))
                        }
                }
            }
            Pager(page: page, data: ShoppingItemListTab.allCases) { tab in
                switch tab {
                case .main:
                    MainShoppingItemList(shoppingItems: uiModel.mainShoppingItems)
                case .archived:
                    ArchivedShoppingItemList(shoppingItems: uiModel.archivedShoppingItems)
                case .deleted:
                    DeletedShoppingItemList(shoppingItems: uiModel.deletedShoppingItems)
                }
            }
            .loopPages()
        }
    }
    
    struct Tab: View {
        let parent: ShoppingItemListContentView
        let isSelected: Bool
        let tab: ShoppingItemListTab
        
        var body: some View {
            VStack {
                Text(tab.title)
                    .font(.headline)
                Rectangle()
                    .frame(height: isSelected ? 4 : 1)
            }
        }
    }
}
