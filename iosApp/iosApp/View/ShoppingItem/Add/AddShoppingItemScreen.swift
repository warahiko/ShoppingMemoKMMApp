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
        AddShoppingItemContentView(uiModel: $viewModel.uiModel)
    }
}

private struct AddShoppingItemContentView: View {
    @Binding var uiModel: AddShoppingItemScreen.UiModel
    @State private var shoppingItemEditable = ShoppingItemEditable.companion.doNewInstanceToAdd()
    
    var body: some View {
        VStack {
            ShoppingItemEditor(
                tagsGroupedByType: uiModel.tagGroupedByType,
                shoppingItem: shoppingItemEditable
            ) { shoppingItemEditable in
                self.shoppingItemEditable = shoppingItemEditable
            }
        }
        .navigationTitle("アイテムを追加")
    }
}
