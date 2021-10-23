//
//  AddShoppingItemScreen.swift
//  iosApp
//
//  Created by 柴田 剛 on 2021/10/23.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct AddShoppingItemScreen: View {
    @ObservedObject private(set) var viewModel: ViewModel
    
    var body: some View {
        AddShoppingItemContentView(uiModel: $viewModel.uiModel)
    }
}

private struct AddShoppingItemContentView: View {
    @Binding var uiModel: AddShoppingItemScreen.UiModel
    
    var body: some View {
        Text("Add")
    }
}
