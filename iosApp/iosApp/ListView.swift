//
//  ListView.swift
//  iosApp
//
//  Created by Prasad Mhapankar on 20/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//
import Foundation
import SwiftUI
import KMPObservableViewModelSwiftUI
import KMPNativeCoroutinesAsync
import Shared
import KMPNativeCoroutinesCombine


struct ListView: View {
    
    var viewModel = SharedModuleDependencies.shared.listViewModel
        
    @State private var isLoading: Bool = true
    @State private var list: [MuseumObject] = []
    
    let columns = [
        GridItem(.adaptive(minimum: 120), alignment: .top)
    ]

    var body: some View {
        ZStack {
            if isLoading {
                ProgressView("Loading...")
            } else {
                
                NavigationStack {
                    ScrollView {
                        LazyVGrid(columns: columns, alignment: .leading, spacing: 20) {
                            ForEach(list, id: \.self) { item in
                                NavigationLink(destination: DetailView(objectId: item.objectID)) {
                                    ObjectFrame(obj: item, onClick: {})
                                }
                                .buttonStyle(PlainButtonStyle())
                            }
                        }
                        .padding(.horizontal)
                    }
                }
                .navigationTitle("iOS")
            }
        }
        .task {
            for await state in viewModel.featureListState {
                isLoading = false
                list = state.list ?? []
            }
        }
    }
}

//extension List {
//    @MainActor
//    class VewModel: ObservableObject {
//        @Published  var list = [MuseumObject]()
//        
//        func startObserving() {
//            Task {
//                list = SharedModuleDependencies.shared.listViewModel.featureListState ?? []
//            }
//        }
//        
//    }
//}
//
//func startObserving() {
//    Task {
//        for await phrase in SharedModuleDependencies.shared.museumRepository.getData() {
//           // self.greetings.append(phrase)
//        }
//    }
//}

struct ObjectFrame: View {
    let obj: MuseumObject
    let onClick: () -> Void

    var body: some View {
        VStack(alignment: .leading, spacing: 4) {
            GeometryReader { geometry in
                AsyncImage(url: URL(string: obj.primaryImageSmall)) { phase in
                    switch phase {
                    case .empty:
                        ProgressView()
                            .frame(width: geometry.size.width, height: geometry.size.width)
                    case .success(let image):
                        image
                            .resizable()
                            .scaledToFill()
                            .frame(width: geometry.size.width, height: geometry.size.width)
                            .clipped()
                            .aspectRatio(1, contentMode: .fill)
                    default:
                        EmptyView()
                            .frame(width: geometry.size.width, height: geometry.size.width)
                    }
                }
            }
            .aspectRatio(1, contentMode: .fit)

            Text(obj.title)
                .font(.headline)

            Text(obj.artistDisplayName)
                .font(.subheadline)

            Text(obj.objectDate)
                .font(.caption)
        }
    }
}
