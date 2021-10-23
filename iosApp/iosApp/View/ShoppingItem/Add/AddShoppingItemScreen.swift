//
//  AddShoppingItemScreen.swift
//  iosApp
//
//  Created by 柴田 剛 on 2021/10/23.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct AddShoppingItemScreen: View {
    @ObservedObject private(set) var viewModel: ViewModel

    var body: some View {
        AddShoppingItemContentView(uiModel: $viewModel.uiModel, onAdd: viewModel.addShoppingItem(_:))
    }
}

private struct AddShoppingItemContentView: View {
    @Binding var uiModel: AddShoppingItemScreen.UiModel
    let onAdd: (ShoppingItem) -> Void
    @State private var shoppingItemEditable = ShoppingItemEditable.companion.doNewInstanceToAdd()

    var body: some View {
        VStack {
            ShoppingItemEditor(
                tagsGroupedByType: uiModel.tagGroupedByType,
                shoppingItem: shoppingItemEditable
            ) { shoppingItemEditable in
                self.shoppingItemEditable = shoppingItemEditable
            }
            Button {
                onAdd(shoppingItemEditable.fix())
            } label: {
                Text("追加")
            }
        }
        .navigationTitle("アイテムを追加")
    }
}
