package  com.kotlin.walletservice.model


class PostFailure {
    class ListNotAvailable : Failure.FeatureFailure()
    class NonExistentMovie : Failure.FeatureFailure()
}