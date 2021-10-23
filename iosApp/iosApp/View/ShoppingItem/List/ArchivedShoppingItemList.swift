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
    let shoppingItems: Dictionary<String, [ShoppingItem]>
    
    var body: some View {
        List {
            // RandomAccessCollection にするためList にする
            ForEach(shoppingItems.keys.map { $0 }, id: \.self) { date in
                Section(header: Text(date)) {
                    ForEach(shoppingItems[date].orEmpty()) { item in
                        ShoppingItemRow(shoppingItem: item, showsCheckBox: false)
                    }
                }
            }
        }
    }
}

struct ArchivedShoppingItemList_Previews: PreviewProvider {
    static var previews: some View {
        ArchivedShoppingItemList(shoppingItems: ShoppingItemPreview.shared.sampleMap)
            .previewLayout(PreviewLayout.sizeThatFits)
            .padding()
            .previewDisplayName("Light")
        
        
        ArchivedShoppingItemList(shoppingItems: ShoppingItemPreview.shared.sampleMap)
            .previewLayout(PreviewLayout.sizeThatFits)
            .padding()
            .background(Color(.systemBackground))
            .environment(\.colorScheme, .dark)
            .previewDisplayName("Dark")
    }
}
