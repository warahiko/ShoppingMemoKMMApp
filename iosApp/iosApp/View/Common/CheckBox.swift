//
//  CheckBox.swift
//  iosApp
//
//  Created by 柴田 剛 on 2021/10/20.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct CheckBox: View {
    let isChecked: Bool
    let toggle: () -> Void

    init(isChecked: Bool) {
        self.isChecked = isChecked
        self.toggle = {}
    }

    init(isChecked: Bool, toggle: @escaping () -> Void) {
        self.isChecked = isChecked
        self.toggle = toggle
    }

    var body: some View {
        Button(action: toggle) {
            if (isChecked) {
                Image(systemName: "checkmark.square.fill")
                    .foregroundColor(ShoppingMemoColor.lightRed.color)
            } else {
                Image(systemName: "square")
                    .foregroundColor(ShoppingMemoColor.lightRed.color)
            }
        }
    }
}

struct CheckBox_Previews: PreviewProvider {
    static var previews: some View {
        CheckBox(isChecked: false)
            .previewLayout(PreviewLayout.sizeThatFits)
            .padding()
            .previewDisplayName("Light: Not checked")

        CheckBox(isChecked: true)
            .previewLayout(PreviewLayout.sizeThatFits)
            .padding()
            .previewDisplayName("Light: Checked")

        CheckBox(isChecked: false)
            .previewLayout(PreviewLayout.sizeThatFits)
            .padding()
            .background(Color(.systemBackground))
            .environment(\.colorScheme, .dark)
            .previewDisplayName("Dark: Not checked")

        CheckBox(isChecked: true)
            .previewLayout(PreviewLayout.sizeThatFits)
            .padding()
            .background(Color(.systemBackground))
            .environment(\.colorScheme, .dark)
            .previewDisplayName("Dark: Checked")
    }
}
