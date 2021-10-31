//
//  TagListScreen.swift
//  iosApp
//
//  Created by 柴田 剛 on 2021/10/31.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct TagListScreen: View {
    var body: some View {
        NavigationView {
            TagListScreenContentView()
                .navigationTitle("タグ")
                .navigationBarTitleDisplayMode(NavigationBarItem.TitleDisplayMode.inline)
        }
    }
}

private struct TagListScreenContentView: View {
    var body: some View {
        Text(/*@START_MENU_TOKEN@*/"Hello, World!"/*@END_MENU_TOKEN@*/)
    }
}
