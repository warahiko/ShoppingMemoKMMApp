//
//  MainShoppingItemList.swift
//  iosApp
//
//  Created by 柴田 剛 on 2021/10/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct MainShoppingItemList: View {
    let shoppingItems: [ShoppingItem]

    var body: some View {
        List(shoppingItems) { shoppingItem in
            ShoppingItemRow(shoppingItem: shoppingItem)
        }
    }
}

struct MainShoppingItemList_Previews: PreviewProvider {
    static var previews: some View {
        MainShoppingItemList(shoppingItems: ShoppingItemPreview.shared.samples)
            .previewLayout(PreviewLayout.sizeThatFits)
            .padding()
            .previewDisplayName("Light")
        
        
        MainShoppingItemList(shoppingItems: ShoppingItemPreview.shared.samples)
            .previewLayout(PreviewLayout.sizeThatFits)
            .padding()
            .background(Color(.systemBackground))
            .environment(\.colorScheme, .dark)
            .previewDisplayName("Dark")
    }
}
