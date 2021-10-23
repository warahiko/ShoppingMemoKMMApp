//
//  ArchivedShoppingItemList.swift
//  iosApp
//
//  Created by 柴田 剛 on 2021/10/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ArchivedShoppingItemList: View {
    let shoppingItems: [ShoppingItem]
    
    var body: some View {
        List(shoppingItems) { shoppingItem in
            ShoppingItemRow(shoppingItem: shoppingItem, showsCheckBox: false)
        }
    }
}

struct ArchivedShoppingItemList_Previews: PreviewProvider {
    static var previews: some View {
        ArchivedShoppingItemList(shoppingItems: ShoppingItemPreview.shared.samples)
            .previewLayout(PreviewLayout.sizeThatFits)
            .padding()
            .previewDisplayName("Light")
        
        
        ArchivedShoppingItemList(shoppingItems: ShoppingItemPreview.shared.samples)
            .previewLayout(PreviewLayout.sizeThatFits)
            .padding()
            .background(Color(.systemBackground))
            .environment(\.colorScheme, .dark)
            .previewDisplayName("Dark")
    }
}
