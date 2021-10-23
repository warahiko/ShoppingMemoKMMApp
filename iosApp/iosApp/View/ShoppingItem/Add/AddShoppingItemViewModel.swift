//
//  AddShoppingItemViewModel.swift
//  iosApp
//
//  Created by 柴田 剛 on 2021/10/23.
//  Copyright © 2021 orgName. All rights reserved.
//

import shared

extension AddShoppingItemScreen {
    class ViewModel: ObservableObject {
        let tagRepository = InjectorIos().tagRepository
        let tagIosRepository = InjectorIos().tagIosRepository
        
        @Published var uiModel: UiModel = .EMPTY
        
        init() {
            tagIosRepository.tags.collect { tags in
                self.uiModel = UiModel.from(tags: tags as? [Tag])
            }
        }
    }
    
    struct UiModel {
        let tagGroupedByType: Dictionary<String, [Tag]>
        
        static let EMPTY: UiModel = .init(tagGroupedByType: [:])
        
        static func from(tags: [Tag]?) -> UiModel {
            let tagGroupedByType = tags.orEmpty()
                .groupBy { tag in tag.type }
            
            return UiModel(tagGroupedByType: tagGroupedByType)
        }
    }
}
