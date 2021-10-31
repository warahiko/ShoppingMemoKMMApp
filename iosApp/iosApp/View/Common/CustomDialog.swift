//
//  CustomDialog.swift
//  iosApp
//
//  Created by 柴田 剛 on 2021/10/24.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI


/// see: https://swiftuirecipes.com/blog/custom-view-dialog-in-swiftui
struct CustomDialog<DialogContent: View>: ViewModifier {

    let isShowing: Bool
    let dialogContent: DialogContent
    
    init(
        isShowing: Bool,
        @ViewBuilder dialogContent: () -> DialogContent
    ) {
        self.isShowing = isShowing
        self.dialogContent = dialogContent()
    }
    
    func body(content: Content) -> some View {
        ZStack {
            content
            if isShowing {
                Rectangle().foregroundColor(Color.black.opacity(0.6))
                ZStack {
                    dialogContent
                        .background(
                            RoundedRectangle(cornerRadius: 8).foregroundColor(.white)
                        )
                }.padding(40)
            }
        }
    }
}

extension View {
    func customDialog<DialogContent: View>(
        isShowing: Bool,
        @ViewBuilder dialogContent: @escaping () -> DialogContent
    ) -> some View {
        self.modifier(CustomDialog(isShowing: isShowing, dialogContent: dialogContent))
    }
}
