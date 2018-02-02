package eu.tinoba.androidarcitecturetemplate.ui.base.presenters;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter {

    private CompositeDisposable compositeDisposable;

    public void addDisposable(final Disposable disposable) {
        if (compositeDisposable == null || compositeDisposable.isDisposed()) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    public void dispose() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }
}
