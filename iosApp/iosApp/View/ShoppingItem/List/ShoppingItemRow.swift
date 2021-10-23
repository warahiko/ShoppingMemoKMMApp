//
//  ShoppingItemRowView.swift
//  iosApp
//
//  Created by 柴田 剛 on 2021/10/20.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ShoppingItemRow: View {
    let shoppingItem: ShoppingItem
    let showsCheckBox: Bool

    init(shoppingItem: ShoppingItem, showsCheckBox: Bool = true) {
        self.shoppingItem = shoppingItem
        self.showsCheckBox = showsCheckBox
    }
    
    var body: some View {
        let iconColor = shoppingItem.memo.isEmpty
            ? ShoppingMemoColor.black.color
            : ShoppingMemoColor.lightRed.color
        HStack {
            if showsCheckBox {
                CheckBox(isChecked: shoppingItem.isDone)
            }
            Text(shoppingItem.name)
            Spacer()
            Text(shoppingItem.count.description)
            Image(systemName: "info.circle.fill")
                .foregroundColor(iconColor)
        }
    }
}

struct ShoppingItemRowView_Previews: PreviewProvider {
    static var previews: some View {
        ShoppingItemRow(shoppingItem: ShoppingItemPreview.shared.sample)
            .previewLayout(PreviewLayout.sizeThatFits)
            .padding()
            .previewDisplayName("Light")
        
        ShoppingItemRow(shoppingItem: ShoppingItemPreview.shared.sample, showsCheckBox: false)
            .previewLayout(PreviewLayout.sizeThatFits)
            .padding()
            .previewDisplayName("Light: checkBoxInvisible")
        
        ShoppingItemRow(shoppingItem: ShoppingItemPreview.shared.sample)
            .previewLayout(PreviewLayout.sizeThatFits)
            .padding()
            .background(Color(.systemBackground))
            .environment(\.colorScheme, .dark)
            .previewDisplayName("Dark")
    }
}
