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
        VStack {
            TagSelector(selectedTag: shoppingItem.tag, tagsGroupedByType: tagsGroupedByType) { tag in
                onChangeItem(shoppingItem.copy(name: tag.name, tag: tag))
            }
            TextField("数量", text: $itemCount)
                .keyboardType(UIKeyboardType.numberPad)
                .onChange(of: Just(itemCount)) { _ in
                    onChangeItem(shoppingItem.copy(count: itemCount))
                }
            TextField("メモ", text: $itemMemo)
                .textFieldStyle(RoundedBorderTextFieldStyle())
                .onChange(of: Just(itemMemo)) { _ in
                    onChangeItem(shoppingItem.copy(memo: itemMemo))
                }
        }
    }
}

private struct TagSelector: View {
    @State private(set) var selectedTag: Tag?
    let tagsGroupedByType: Dictionary<String, [Tag]>
    let onChangeTag: (Tag) -> Void
    
    var body: some View {
        Menu(selectedTag?.description() ?? "タグ") {
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
        }
    }
}
