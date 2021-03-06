//
//  ShoppingItemEditor.swift
//  iosApp
//
//  Created by 柴田 剛 on 2021/10/23.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import Combine
import shared

struct ShoppingItemEditor: View {
    let tagsGroupedByType: Dictionary<String, [Tag]>
    let shoppingItem: ShoppingItemEditable
    let onChangeItem: (ShoppingItemEditable) -> Void
    @State private var itemCount: String
    @State private var itemMemo: String
    
    init(
        tagsGroupedByType: Dictionary<String, [Tag]>,
        shoppingItem: ShoppingItemEditable,
        onChangeItem: @escaping (ShoppingItemEditable) -> Void
    ) {
        self.tagsGroupedByType = tagsGroupedByType
        self.shoppingItem = shoppingItem
        self.onChangeItem = onChangeItem
        self.itemCount = self.shoppingItem.count
        self.itemMemo = self.shoppingItem.memo
    }
    
    var body: some View {
        VStack(spacing: 0) {
            TagSelector(selectedTag: shoppingItem.tag, tagsGroupedByType: tagsGroupedByType) { tag in
                onChangeItem(shoppingItem.copy(name: tag.name, tag: tag))
            }
            .frame(height: 36)
            Divider()
            TextField("数量", text: $itemCount)
                .keyboardType(UIKeyboardType.numberPad)
                .onChange(of: Just(itemCount)) { _ in
                    onChangeItem(shoppingItem.copy(count: itemCount))
                }
                .frame(height: 36)
            Divider()
            TextField("メモ", text: $itemMemo)
                .onChange(of: Just(itemMemo)) { _ in
                    onChangeItem(shoppingItem.copy(memo: itemMemo))
                }
                .frame(height: 36)
        }
        .padding(.leading, 8)
        .padding(.vertical, 4)
        .background(
            RoundedRectangle(cornerRadius: 8)
                .foregroundColor(ShoppingMemoColor.white.color)
        )
    }
}

private struct TagSelector: View {
    @State private(set) var selectedTag: Tag?
    let tagsGroupedByType: Dictionary<String, [Tag]>
    let onChangeTag: (Tag) -> Void
    
    var body: some View {
        Menu {
            ForEach(Array(tagsGroupedByType.keys), id: \.self) { type in
                Menu(type) {
                    ForEach(tagsGroupedByType[type].orEmpty()) { tag in
                        Button {
                            selectedTag = tag
                            onChangeTag(tag)
                        } label: {
                            Text(tag.name)
                        }
                    }
                }
            }
        } label: {
            Text(selectedTag?.description() ?? "タグを選択")
                .padding(EdgeInsets(top: 8, leading: 4, bottom: 8, trailing: 4))
                .frame(maxWidth: .infinity, alignment: .leading)
        }
    }
}

struct ShoppingItemEditor_Previews: PreviewProvider {
    static var previews: some View {
        ShoppingItemEditor(
            tagsGroupedByType: [:],
            shoppingItem: ShoppingItemEditable.companion.doNewInstanceToAdd(),
            onChangeItem: { _ in }
        )
            .previewLayout(.device)
            .previewDisplayName("Light")
        
        ShoppingItemEditor(
            tagsGroupedByType: [:],
            shoppingItem: ShoppingItemEditable.companion.doNewInstanceToAdd(),
            onChangeItem: { _ in }
        )
            .previewLayout(.device)
            .background(Color(.systemBackground))
            .environment(\.colorScheme, .dark)
            .previewDisplayName("Dark")
    }
}
