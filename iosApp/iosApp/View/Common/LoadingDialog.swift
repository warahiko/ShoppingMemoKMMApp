//
//  LoadingDialog.swift
//  iosApp
//
//  Created by 柴田 剛 on 2021/10/24.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

extension View {
    func loadingDialog(isLoading: Bool) -> some View {
        self.modifier(CustomDialog(isShowing: isLoading, dialogContent: {
            LoadingDialogContentView()
        }))
    }
}

private struct LoadingDialogContentView: View {
    var body: some View {
        ProgressView()
            .frame(width: 100, height: 100)
    }
}

struct LoadingDialogContentView_Previews: PreviewProvider {
    static var previews: some View {
        LoadingDialogContentView()
            .previewLayout(PreviewLayout.device)
            .padding()
            .previewDisplayName("Light")
        
        LoadingDialogContentView()
            .previewLayout(PreviewLayout.device)
            .padding()
            .background(Color(.systemBackground))
            .environment(\.colorScheme, .dark)
            .previewDisplayName("Dark")
    }
}
