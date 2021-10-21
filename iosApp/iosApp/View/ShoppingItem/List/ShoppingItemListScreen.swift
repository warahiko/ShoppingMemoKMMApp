//
//  ShoppingItemListView.swift
//  iosApp
//
//  Created by 柴田 剛 on 2021/10/20.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ShoppingItemListScreen: View {
    @ObservedObject private(set) var viewModel: ViewModel
    
    var body: some View {
        ShoppingItemListContentView(shoppingItems: viewModel.shoppingItems)
    }
}

private struct ShoppingItemListContentView: View {
    let shoppingItems: [ShoppingItem]
    
    var body: some View {
        List(shoppingItems) { shoppingItem in
            ShoppingItemRow(shoppingItem: shoppingItem)
        }
    }
}

struct ShoppingItemListView_Previews: PreviewProvider {
    static var previews: some View {
        ShoppingItemListContentView(shoppingItems: ShoppingItemPreview.shared.samples)
            .previewLayout(PreviewLayout.sizeThatFits)
            .padding()
            .previewDisplayName("Light")
        
        ShoppingItemListContentView(shoppingItems: ShoppingItemPreview.shared.samples)
            .previewLayout(PreviewLayout.sizeThatFits)
            .padding()
            .background(Color(.systemBackground))
            .environment(\.colorScheme, .dark)
            .previewDisplayName("Dark")
    }
}
