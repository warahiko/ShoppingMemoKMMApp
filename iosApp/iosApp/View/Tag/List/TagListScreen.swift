//
//  TagListScreen.swift
//  iosApp
//
//  Created by 柴田 剛 on 2021/10/31.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct TagListScreen: View {
    @ObservedObject private(set) var viewModel: ViewModel
    
    var body: some View {
        NavigationView {
            TagListScreenContentView(
                uiModel: $viewModel.uiModel
            )
                .navigationTitle("タグ")
                .navigationBarTitleDisplayMode(NavigationBarItem.TitleDisplayMode.inline)
        }
    }
}

private struct TagListScreenContentView: View {
    @Binding var uiModel: TagListScreen.UiModel
    
    var body: some View {
        Text(uiModel.tagsGroupedByType.first?.value.first?.description() ?? "null")
    }
}
