package com.kotlin.mvvm.model

import com.kotlin.mvvm.exception.Failure.FeatureFailure

class PostFailure {
    class ListNotAvailable : FeatureFailure()
    class NonExistentMovie : FeatureFailure()
}