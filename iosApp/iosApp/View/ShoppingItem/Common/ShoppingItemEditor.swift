//
//  ShoppingItemEditor.swift
//  iosApp
//
//  Created by 柴田 剛 on 2021/10/23.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ShoppingItemEditor: View {
    let tagsGroupedByType: Dictionary<String, [Tag]>
    let shoppingItem: ShoppingItemEditable
    let onChangeItem: (ShoppingItemEditable) -> Void
    
    var body: some View {
        VStack {
            TagSelector(tagsGroupedByType: tagsGroupedByType) { tag in
                onChangeItem(shoppingItem.copy(tag: tag))
            }
        }
    }
}

private struct TagSelector: View {
    let tagsGroupedByType: Dictionary<String, [Tag]>
    let onChangeTag: (Tag) -> Void
    @State private(set) var selectedTag: Tag? = nil
    
    var body: some View {
        Menu(selectedTag?.description() ?? "タグ") {
            ForEach(tagsGroupedByType.keys.map { $0 }, id: \.self) { type in
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
