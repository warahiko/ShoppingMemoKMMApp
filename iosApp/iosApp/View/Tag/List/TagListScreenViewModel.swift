//
//  TagListScreenViewModel.swift
//  iosApp
//
//  Created by 柴田 剛 on 2021/10/31.
//  Copyright © 2021 orgName. All rights reserved.
//

import shared

extension TagListScreen {
    class ViewModel: ObservableObject {
        let tagIosRepository = InjectorIos().tagIosRepository
        
        @Published var uiModel: UiModel = .EMPTY
        
        init() {
            tagIosRepository.tags.collect { tags in
                self.uiModel = .from(tags: tags as? [Tag])
            }
        }
    }
    
    struct UiModel {
        let tagsGroupedByType: Dictionary<String, [Tag]>
        
        static let EMPTY: UiModel = .init(tagsGroupedByType: [:])
        
        static func from(tags: [Tag]?) -> UiModel {
            let tagsGroupedByType = tags.orEmpty()
                .groupBy { $0.type }
            
            return .init(tagsGroupedByType: tagsGroupedByType)
        }
    }
}
