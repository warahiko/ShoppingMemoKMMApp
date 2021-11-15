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
    @Environment(\.presentationMode) var presentationMode: Binding<PresentationMode>

    var body: some View {
        AddShoppingItemContentView(uiModel: $viewModel.uiModel) { shoppingItem in
            viewModel.addShoppingItem(shoppingItem) {
                self.presentationMode.wrappedValue.dismiss()
            }
        }.loadingDialog(isLoading: viewModel.showProgress)
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
            .padding()
            Button {
                onAdd(shoppingItemEditable.fix())
            } label: {
                Text("追加")
            }
            .padding()
            .frame(maxWidth: .infinity, alignment: .trailing)
        }
        .frame(maxHeight: .infinity, alignment: .top)
        .background(ShoppingMemoColor.lightGray.color)
        .navigationTitle("アイテムを追加")
    }
}
