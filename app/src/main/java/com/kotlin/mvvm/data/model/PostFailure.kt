package com.kotlin.mvvm.data.model

import com.kotlin.mvvm.data.model.Failure.FeatureFailure

class PostFailure {
    class ListNotAvailable : FeatureFailure()
    class NonExistentMovie : FeatureFailure()
}