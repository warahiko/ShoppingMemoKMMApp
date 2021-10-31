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
    let shoppingItems: Dictionary<String, [ShoppingItem]>

    var body: some View {
        List {
            // RandomAccessCollection にするためArray にする
            ForEach(Array(shoppingItems.keys), id: \.self) { tag in
                Section(header: Text(tag)) {
                    ForEach(shoppingItems[tag].orEmpty()) { item in
                        ShoppingItemRow(shoppingItem: item)
                    }
                }
            }
        }
    }
}

struct MainShoppingItemList_Previews: PreviewProvider {
    static var previews: some View {
        MainShoppingItemList(shoppingItems: ShoppingItemPreview.shared.sampleMap)
            .previewLayout(PreviewLayout.sizeThatFits)
            .padding()
            .previewDisplayName("Light")
        
        
        MainShoppingItemList(shoppingItems: ShoppingItemPreview.shared.sampleMap)
            .previewLayout(PreviewLayout.sizeThatFits)
            .padding()
            .background(Color(.systemBackground))
            .environment(\.colorScheme, .dark)
            .previewDisplayName("Dark")
    }
}
