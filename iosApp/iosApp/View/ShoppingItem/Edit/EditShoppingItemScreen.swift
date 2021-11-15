//
//  EditShoppingItemScreen.swift
//  iosApp
//
//  Created by 柴田 剛 on 2021/10/31.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct EditShoppingItemScreen: View {
    @ObservedObject private(set) var viewModel: ViewModel
    let shoppingItem: ShoppingItem
    @Environment(\.presentationMode) var presentationMode: Binding<PresentationMode>

    var body: some View {
        NavigationView {
            EditShoppingItemContentView(
                shoppingItem: shoppingItem,
                uiModel: $viewModel.uiModel,
                onEdit: { shoppingItem in
                    viewModel.editShoppingItem(shoppingItem) {
                        presentationMode.wrappedValue.dismiss()
                    }
                }
            )
                .loadingDialog(isLoading: viewModel.showProgress)
                .navigationTitle("アイテムを編集")
                .navigationBarTitleDisplayMode(NavigationBarItem.TitleDisplayMode.inline)
        }
    }
}

private struct EditShoppingItemContentView: View {
    @Binding var uiModel: EditShoppingItemScreen.UiModel
    let onEdit: (ShoppingItem) -> Void
    @State private var shoppingItemEditable: ShoppingItemEditable

    init(
        shoppingItem: ShoppingItem,
        uiModel: Binding<EditShoppingItemScreen.UiModel>,
        onEdit: @escaping (ShoppingItem) -> Void
    ) {
        self._uiModel = uiModel
        self.onEdit = onEdit
        self.shoppingItemEditable = shoppingItem.toEditable()
    }

    var body: some View {
        VStack(spacing: 0) {
            ShoppingItemEditor(
                tagsGroupedByType: uiModel.tagGroupedByType,
                shoppingItem: shoppingItemEditable
            ) { shoppingItemEditable in
                self.shoppingItemEditable = shoppingItemEditable
            }
            .padding()
            Button {
                onEdit(shoppingItemEditable.fix())
            } label: {
                Text("編集")
                    .foregroundColor(ShoppingMemoColor.white.color)
                    .padding(.vertical, 8)
                    .frame(maxWidth: .infinity, alignment: .center)
                    .background(
                        RoundedRectangle(cornerRadius: 8)
                            .foregroundColor(Color.blue)
                    )
            }
            .padding()
            .frame(maxWidth: .infinity, alignment: .trailing)
        }
        .frame(maxHeight: .infinity, alignment: .top)
        .background(ShoppingMemoColor.lightGray.color)
    }
}
